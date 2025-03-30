package cn.itbeien.payment.api;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.enums.PayForStatusEnum;
import cn.itbeien.common.constant.ChannelConstant;
import cn.itbeien.common.enums.PayStatus;
import cn.itbeien.common.mapper.PlatPayDetailMapper;
import cn.itbeien.common.mapper.trade.TradeBatchInfoMapper;
import cn.itbeien.common.mapper.trade.TradeOrderMapper;
import cn.itbeien.common.entity.trade.TradeBatchInfo;
import cn.itbeien.common.util.SpringUtils;
import cn.itbeien.common.util.StringUtils;
import cn.itbeien.payment.channel.vo.ChnBatchPayForNotifyResponse;
import cn.itbeien.payment.channel.vo.ChnPayForNotifyResponse;
import cn.itbeien.payment.channel.vo.PayInfoBean;
import cn.itbeien.payment.core.vo.TradeRequest;
import cn.itbeien.payment.core.vo.TradeResponse;
import cn.itbeien.payment.core.vo.request.PayQueryRequest;
import cn.itbeien.payment.core.vo.request.PayRequest;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.service.pay.PayChannelService;
import cn.itbeien.payment.service.payfor.impl.PayForChannelService;
import cn.itbeien.payment.service.verify.MerchantVerifyService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 支付渠道上下游支付入口
 * Copyright© 2025 itbeien
 */
@RestController
@Slf4j
public class PayChannelServiceController {

	private final static String pay = "pay";

	private final static String payQuery = "payQuery";

	@Autowired
	private PayChannelService payChannelService;

	@Autowired
	private MerchantVerifyService merchantVerifyService;
	
	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	
	@Autowired
	private PlatPayDetailMapper platPayDetailMapper;
	
	@Autowired
	private PayForChannelService payForChannelService;

	@Autowired
	private TradeBatchInfoMapper tradeBatchInfoMapper;
	
	/**
	 * 下游商户支付API接口 方法用途: <br>
	 * @return
	 */
	@RequestMapping(value = "/gateway/pay.do", method = RequestMethod.POST)
	public String pay(@RequestParam String jsonParam) {
		log.info("下游下单请求参数：{}", jsonParam);
		String jsonRes = "";
		// 接收报文
		JSONObject jsonObject = JSONObject.parseObject(jsonParam);
		TradeResponse tradeResponse = null;
		try {

			// 1、参数封装到bean中
			TradeRequest tradeRequest = merchantVerifyService.getTradeRequest(jsonObject);

			// 2、对商户报文验证，商户验签
			tradeRequest = merchantVerifyService.validateTradeData(jsonObject);

			// 3、支付路由处理(pay,payQuery)
			switch (tradeRequest.getInterfaceCode()) {
			case pay:
				tradeResponse = this.payChannelService.route((PayRequest) tradeRequest);
				break;
			case payQuery:
				tradeResponse = this.payChannelService.payQuery((PayQueryRequest) tradeRequest);
				break;
			}
			// 4、对返回报文加签
			jsonRes = merchantVerifyService.generateTradeResponseAsJson(tradeResponse);
		} catch (TradeException e) {// 业务异常
			jsonRes = merchantVerifyService.generateRuntimeExceptionResponseAsJson(e, jsonObject);

		} catch (Exception e) {// 系统异常
			log.error("下单接口异常：" + e.getMessage() ,e);
			jsonRes = merchantVerifyService.generateRuntimeExceptionResponseAsJson(e, jsonObject);
		}
		return jsonRes;
	}

	/**
	 * 上游渠道支付结果通知回调API接口 方法用途: <br>
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/gateway/notifyInfo/{channelType}")
	public String notifyInfo(HttpServletRequest request,@PathVariable("channelType") String channelType) {
		String result = "fail";
		try {
			// 平台通过notify_url通知商户，商户做业务处理后，需要以字符串的形式反馈处理结果
			// success 处理成功，平台收到此结果后不再进行后续通知
			// fail或其它字符 处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
			Object clazz  = SpringUtils.getBean("notifyService");//获取上游异步通知协议处理类
			Method method = clazz.getClass().getMethod("notifyInfo", new Class[] {HttpServletRequest.class,String.class});
			PayInfoBean payInfoBean =(PayInfoBean) method.invoke(clazz, new Object[] {request,channelType });// 上游支付异步通知结果
			TradeOrder tradeOrder = this.tradeOrderMapper.selectByPrimaryKey(payInfoBean.getOutTradeNo());//根据订单号获取支付订单表
			if(tradeOrder == null) {
				log.info("支付回调返回上游结果为:{},平台订单号{}:", result,payInfoBean.getOutTradeNo());
				return result;
			}
			if(!(PayStatus.p0000.getCode().equals(tradeOrder.getPayStatus()))){
				this.payChannelService.notifyInfo(payInfoBean);
			}
			result = payInfoBean.getResult();
			log.info("支付回调返回上游结果为:{},平台订单号:{}",result,tradeOrder.getOrderId());
			return result;
		} catch (Exception e) {
			result = "fail";
			log.error("上游渠道支付结果通知回调API接口:{0}",e);
			return result;
		}
	}

	
	/**
	 * 方法用途: 上游渠道 代付结果通知回调API接口  <br>
	 *	@param request
	 * 	@param channelType
	 * @return
	 */
	@RequestMapping(value = "/gateway/payForNotify/{channelType}")
	public String payForNotify(HttpServletRequest request, @PathVariable("channelType") String channelType) {
		String result = "fail";
		try {
			// 平台通过notify_url通知商户，商户做业务处理后，需要以字符串的形式反馈处理结果
			// success 处理成功，平台收到此结果后不再进行后续通知
			// fail或其它字符 处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
			Object clazz  = SpringUtils.getBean("notifyService");//获取上游异步通知协议处理类
			Method method = clazz.getClass().getMethod("payForNotify", new Class[] {HttpServletRequest.class,String.class});
			ChnPayForNotifyResponse notifyResponse = (ChnPayForNotifyResponse) method.invoke(clazz, new Object[] {request,channelType });// 上游支付异步通知结果
			
			PlatPayDetail payDetail = platPayDetailMapper.selectByPrimaryKey(notifyResponse.getPayId());
			if(!StringUtils.isNotNull(payDetail)) {
				result = "fail";
				log.info("代付回调返回上游结果为:{},平台代付订单号:{} 不存在！",result,notifyResponse.getPayId());
				return result;
			}
			
			// 运营系统 修改
			if(ChannelConstant.PLAT_MANAGER.equals(channelType) && !payDetail.getStatus().equals(notifyResponse.getStatus())) {
				this.payForChannelService.payForNotify(notifyResponse);
			} else {
				if(!PayForStatusEnum.pf0000.getCode().equals(payDetail.getStatus())) {
					this.payForChannelService.payForNotify(notifyResponse);
				}
			}
			result = notifyResponse.getResult();
			log.info("代付回调返回上游结果为:{},平台代付订单号:{}" , result , payDetail.getPayId());
			return result;
		} catch (Exception e) {
			result = "fail";
			log.error("上游渠道代付结果通知回调API接口:"+e.getMessage() ,e);
			return result;
		}
	}
	
	
	/**
	 * 方法用途: 上游渠道 批量代付结果通知回调API接口  <br>
	 * @param request
	 * @param channelType
	 * @return
	 */
	@RequestMapping(value = "/gateway/batchPayForNotify/{channelType}")
	public String batchPayForNotify(HttpServletRequest request, @PathVariable("channelType") String channelType) {
		String result = "fail";
		try {
			// 平台通过notify_url通知商户，商户做业务处理后，需要以字符串的形式反馈处理结果
			// success 处理成功，平台收到此结果后不再进行后续通知
			// fail或其它字符 处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
			Object clazz  = SpringUtils.getBean("notifyService");//获取上游异步通知协议处理类
			Method method = clazz.getClass().getMethod("batchPayForNotify", new Class[] {HttpServletRequest.class,String.class});
			ChnBatchPayForNotifyResponse notifyResponse = (ChnBatchPayForNotifyResponse) method.invoke(clazz, new Object[] {request,channelType });// 上游支付异步通知结果
			
			TradeBatchInfo tradeBatchInfo = tradeBatchInfoMapper.selectByPrimaryKey(notifyResponse.getPlatBatchNo());
			
			if(!StringUtils.isNotNull(tradeBatchInfo)) {
				result = "fail";
				log.info("批量代付回调返回上游结果为:{},平台批次号:{} 不存在！",result,notifyResponse.getPlatBatchNo());
				return result;
			}
			
			if(!PayForStatusEnum.pf0000.getCode().equals(tradeBatchInfo.getBatchStatus())) {
				this.payForChannelService.batchPayForNotify(notifyResponse);
			}
			result = notifyResponse.getResult();
			log.info("批量代付回调返回上游结果为:{},平台批次号:{}" , result , tradeBatchInfo.getPlatBatchNo());
			return result;
		} catch (Exception e) {
			result = "fail";
			log.error("上游渠道代付结果通知回调API接口:{0}",e);
			return result;
		}
	}
}
