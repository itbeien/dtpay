package cn.itbeien.merchant.controller.trade;

import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.pay.PayScenes;
import cn.itbeien.common.entity.pay.Payway;
import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.entity.trade.TradeRefundSeq;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import cn.itbeien.common.vo.trade.TradeOrderSeqReportVO;
import cn.itbeien.merchant.service.common.IPayScenesService;
import cn.itbeien.merchant.service.common.IPaywayService;
import cn.itbeien.merchant.service.trade.IPlatPayDetailService;
import cn.itbeien.merchant.service.trade.ITradeOrderSeqService;
import cn.itbeien.merchant.service.trade.ITradeOrderService;
import cn.itbeien.merchant.service.trade.ITradeRefundSeqService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Controller
@RequestMapping("/trade")
@RequiredArgsConstructor
@Slf4j
public class TradeController extends BaseController {
	
	private final ITradeOrderSeqService tradeOrderSeqServiceImpl;
	private final IPaywayService paywayServiceImpl;
	private final IPayScenesService payScenesServiceImpl;
	private final IPlatPayDetailService platPayDetailServiceImpl;
	private final ITradeRefundSeqService tradeRefundSeqServiceImpl;
	private final ITradeOrderService tradeOrderServiceImpl;

	/**
	 * @author itbeien
	 * 项目网站：https://www.itbeien.cn
	 * 公众号：贝恩聊架构
	 * 全网同名，欢迎小伙伴们关注
	 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
	 * 交易类型：支付（收款）
	 * Copyright© 2025 itbeien
	 */
	@RequestMapping("/payOrder")
	public String payOrder(HttpServletRequest request) {
		try{
			List<Payway>  paywayList = paywayServiceImpl.getList();
			List<PayScenes> payScenesList = payScenesServiceImpl.getList();
		}catch (Exception e){
			log.error("获取支付页面异常",e);
		}
		return "trade/payOrder";
	}
	
	/**
	 * 商户收款订单：商户的平台用户支付给商户的交易记录
	 * @return
	 */
	@RequestMapping("/payOrderList")
	@ResponseBody
	public Object payOrderList() {
		startPage();
		List<TradeOrderSeq> list = tradeOrderSeqServiceImpl.getTradeOrderSeqList(null);
		return getDataTable(list);
	}
	
	/*交易类型：代付（付款）*/
	@RequestMapping("/payOtherOrder")
	public String payOtherOrder(){
		return "trade/payOtherOrder";
	}
	
	/**
	 * @author itbeien
	 * 项目网站：https://www.itbeien.cn
	 * 公众号：贝恩聊架构
	 * 全网同名，欢迎小伙伴们关注
	 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
	 * 商户付款记录：商户代付转账到其他的渠道
	 * Copyright© 2025 itbeien
	 */
	@RequestMapping("/payOtherOrderList")
	@ResponseBody
	public TableDataInfo payOtherOrderList() {
		startPage();
		List<PlatPayDetail> list = platPayDetailServiceImpl.getPlatPayDetailList(null);
		return getDataTable(list);
	}
	
	/**
	 * @author itbeien
	 * 项目网站：https://www.itbeien.cn
	 * 公众号：贝恩聊架构
	 * 全网同名，欢迎小伙伴们关注
	 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
	 * 交易类型：退款
	 * Copyright© 2025 itbeien
	 */
	@RequestMapping("/refundOrder")
	public String refundOrder(){
		return "trade/refundOrder";
	}
	
	/**
	 * 商户退款记录：商户的客户要求退款的订单
	 * @return
	 */
	@RequestMapping("/refundOrderList")
	@ResponseBody
	public TableDataInfo refundOrderList() {
		startPage();
		List<TradeRefundSeq> list = null;
		try{
			list = tradeRefundSeqServiceImpl.getTradeRefundSeqList(null);
		}catch(Exception e){
			log.error("查询商户退款列表异常",e);
		}
		return getDataTable(list);
	}
	
	
	/**
	 * reissueNotice:(补发通知).
	 * @param orderId
	 * @return
	 */
	@PostMapping("/reissueNotice")
	public String reissueNotice(String orderId) {
		return null;
	}
	
	/**
	 * 获取总额及总笔数
	 */
	@RequestMapping("/sumAmtAndCount")
	public Object sumAmtAndCount(){
		TradeOrderSeqReportVO tradeOrderSeqReportVO = null;
		try{
			tradeOrderSeqReportVO = this.tradeOrderSeqServiceImpl.sumAmtAndCount(null);
		}catch(Exception e){
			log.error("获取总额及总笔数异常", e);
		}
		return tradeOrderSeqReportVO;
	}
	
	/**
	 * getSumAmtAndCount:汇总金额、笔数
	 * @return
	 */
	@RequestMapping("/getpayForSumAmtAndCount")
	public AjaxResult getpayForSumAmtAndCount(){
		TradeOrderSeqReportVO tradeOrderSeqReportVO = platPayDetailServiceImpl.getpayForSumAmtAndCount(null);
		return success(tradeOrderSeqReportVO);
	}
	
	/**
	 * export:(导出订单信息).
	 */
	@GetMapping("/export")
	public ResponseEntity<byte[]> export(){
		List<TradeOrderSeq> dataList = tradeOrderSeqServiceImpl.selectTradeOrderSeqList(null);
		
		String fileName = "交易订单-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xls";
		HttpHeaders httpHeaders = new HttpHeaders();  
		httpHeaders.setContentType(MediaType.parseMediaType("application/x-msdownload"));  
		try {
			httpHeaders.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			log.error("导出文件异常", e);
		}
		return null;
	}
	
}
