package cn.itbeien.payment.service.pay;

import cn.itbeien.common.entity.FreezeDetail;
import cn.itbeien.common.entity.SettlementRatio;
import cn.itbeien.common.entity.merchant.*;
import cn.itbeien.common.entity.pay.PayChannel;
import cn.itbeien.common.entity.pay.PayChannelScenes;
import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.enums.PayStatus;
import cn.itbeien.common.mapper.FreezeDetailMapper;
import cn.itbeien.common.mapper.ICommonMapper;
import cn.itbeien.common.mapper.merchant.MercAdjustDetailMapper;
import cn.itbeien.common.mapper.merchant.MerchantAccRelMapper;
import cn.itbeien.common.mapper.merchant.MerchantChannelBalMapper;
import cn.itbeien.common.mapper.merchant.MerchantPaywayMappingMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.Arith;
import cn.itbeien.common.util.SnowflakeIdFactory;
import cn.itbeien.common.util.SpringUtils;
import cn.itbeien.payment.channel.vo.PayBackBean;
import cn.itbeien.payment.channel.vo.PayInfoBean;
import cn.itbeien.payment.component.cache.RedisLock;
import cn.itbeien.payment.core.vo.mq.MchNotifyMqContent;
import cn.itbeien.payment.core.vo.request.PayQueryRequest;
import cn.itbeien.payment.core.vo.request.PayRequest;
import cn.itbeien.payment.core.vo.response.PayNotifyResponse;
import cn.itbeien.payment.core.vo.response.PayQueryResponse;
import cn.itbeien.payment.core.vo.response.PayResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.mapper.trade.TradeOrderMapper;
import cn.itbeien.payment.mapper.trade.TradeOrderSeqMapper;
import cn.itbeien.payment.service.holiday.HolidayService;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import cn.itbeien.common.util.DateUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


@Component
@Slf4j
public class PayChannelService<T> {
	@Autowired
	private RedisCache redisCache;
	
	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	
	@Autowired
	private TradeOrderSeqMapper tradeOrderSeqMapper;
	
	@Autowired
	private ICommonMapper commonMapper;
	
	@Autowired
	private MercAdjustDetailMapper merchantAdjustDetailMapper;
	
	@Autowired
	private MerchantAccRelMapper merchantAccRelMapper;
	
	@Autowired
	private PayOrderNotifyService payOrderNotifyService;
	
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private FreezeDetailMapper freezeDetailMapper;
	
	@Autowired
	private MerchantChannelBalMapper merchantChannelBalMapper;

	@Autowired
	private MerchantPaywayMappingMapper merchantPaywayMappingMapper;
	
	@Autowired
	private  RedisTemplate redisTemplate;

	private SnowflakeIdFactory idWorker = new SnowflakeIdFactory(1, 2);

	/**
	 * 
	 * 方法用途:根据下游商户号、支付方式、支付场景、支付通道默认标识、状态获取上游商户号。
	 * 然后根据商户支付渠道管理表中的支付渠道编号获取支付渠道表SERVICE_BEAN_ID，
	 * 最后根据SERVICE_BEAN_ID路由到具体支付通道支付类 
	 * @param payRequest 下游商户支付请求值对象
	 * @return String 返回给下游商户预支付数据
	 * <br>
	 */
	public PayResponse route(PayRequest payRequest) throws Exception{
		try{
			//检查下游商户状态
			//boolean exists = this.redisCacheUtils.exists(payRequest.getMercNo());
			MerchantInfo merchantInfo = null;
			//if(exists){
				merchantInfo = (MerchantInfo)this.redisCache.getCacheObject(payRequest.getMercNo());// 根据下游商户号获取商户缓存信息
			//}
			if(merchantInfo == null){
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00032.getCode());
				tradeException.setErrorMsg(RespEnum.E00032.getDesc());
				throw tradeException;
			}else if(!"01".equals(merchantInfo.getStatus()) && !"02".equals(merchantInfo.getStatus())){//商户状态为待审核，待审核状态有可能是商户下的产品已经通过，只是修改了一些不影响业务的参数（如电话号码）导致商户状态不为02（已通过）,这种情况不影响商户业务开展
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00050.getCode());
				tradeException.setErrorMsg(RespEnum.E00050.getDesc());
				throw tradeException;
			}
			
			if(!StringUtils.isNumber(payRequest.getTradeAmt())){
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E50010.getCode());
				tradeException.setErrorMsg(RespEnum.E50010.getDesc());
				throw tradeException;
			}
			
			if(Double.valueOf(payRequest.getTradeAmt())<Double.valueOf(0.01)){//支付金额不能小于0.01元
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E50009.getCode());
				tradeException.setErrorMsg(RespEnum.E50009.getDesc());
				throw tradeException;
			}
			
			//校验下游商户订单号是否已经存在
			TradeOrder tradeOrderdb = this.tradeOrderMapper.selectByMechOrderId(payRequest.getMercOrderNo(), null);
			if(tradeOrderdb!=null){//数据库中商户订单号已经存在
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00014.getCode());
				tradeException.setErrorMsg(RespEnum.E00014.getDesc());
				throw tradeException;
			}
			
			
			//获取商户费率从商户支付方式映射表
			MerchantPaywayMapping merchantPaywayMapping = (MerchantPaywayMapping)redisCache.getCacheObject(payRequest.getMercNo()+payRequest.getPaywayCode()+payRequest.getSceneCode());//商户号+支付方式编号+场景编号 唯一确认一条记录
			if(merchantPaywayMapping==null){//商户支付方式不存在
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00045.getCode());
				tradeException.setErrorMsg(RespEnum.E00045.getDesc());
				throw tradeException;
			}else if(!("03".equals(merchantPaywayMapping.getStatus()))){//03代表状态为已接通,商户支付方式状态不为已接通，提示下游相关异常
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00046.getCode());
				tradeException.setErrorMsg(RespEnum.E00046.getDesc());
				throw tradeException;
			}


			//获取商户支付渠道管理表中缓存(下游上传商户号，支付方式，支付场景)
			MerchantChannelMapping merchantChannelMapping = (MerchantChannelMapping) redisCache.getCacheObject("channelMapping"+payRequest.getMercNo()+payRequest.getPaywayCode()+payRequest.getSceneCode());
			if(merchantChannelMapping==null){//商户支付渠道信息为空
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00049.getCode());
				tradeException.setErrorMsg(RespEnum.E00049.getDesc());
				throw tradeException;
			}else if(!"02".equals(merchantChannelMapping.getStatus())){//02 为通道已经接通
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00051.getCode());
				tradeException.setErrorMsg(RespEnum.E00051.getDesc());
				throw tradeException;
			}

			BigDecimal allowAmt = merchantInfo.getAllowAmt();//商户总额度限额
			BigDecimal paySingleLimitAmt = merchantInfo.getPaySingleLimitAmt();//商户支付单笔限额
			BigDecimal payDailyLimitAmt = merchantInfo.getPayDailyLimitAmt();//商户支付账户日限额
			
			BigDecimal singleLimitAmt = merchantPaywayMapping.getSingleLimitAmt();//下游商户交易单笔限额
			BigDecimal paywayAllowAmt =  merchantPaywayMapping.getAllowAmt();//商户对应支付方式（产品）总额度限额

			TradeOrder param = new TradeOrder();
			param.setMercNo(payRequest.getMercNo());//商户号
			TradeOrder totalAmountObject = this.tradeOrderMapper.getTotalAmount(param);
			BigDecimal totalAmount = new BigDecimal(0);
			if(totalAmountObject!=null){
				BigDecimal totalAmountDB = totalAmountObject.getOrderAmount();//当前商户消费的总金额
				totalAmount =totalAmountDB.add(BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt())));
			}else{
				totalAmount = BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt()));
			}

			if(paywayAllowAmt == null){
				MerchantPaywayMapping record = new MerchantPaywayMapping();
				record.setMercNo(payRequest.getMercNo());
				record.setPaywayCode(payRequest.getPaywayCode());
				record.setSceneCode(payRequest.getSceneCode());
				MerchantPaywayMapping recordDB = this.merchantPaywayMappingMapper.selectByMercNoAndPaywaySceneCode(record);
				paywayAllowAmt = recordDB.getAllowAmt();
				if (paywayAllowAmt == null){
					paywayAllowAmt = new BigDecimal(0);
				}
			}

           if(allowAmt.compareTo(new BigDecimal(0))>0 && paywayAllowAmt.compareTo(new BigDecimal(0))>0){
			   if(totalAmount.compareTo(allowAmt)>0 || totalAmount.compareTo(paywayAllowAmt)>0){//商户总限额及单个支付方式总限额 //-1表示小于,0是等于,1是大于。
				   TradeException tradeException = new TradeException();
				   tradeException.setErrorCode(RespEnum.E50014.getCode());
				   tradeException.setErrorMsg(RespEnum.E50014.getDesc());
				   throw tradeException;
			   }
		   }


			BigDecimal tradeAmt = BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt()));//订单金额
			if(singleLimitAmt.compareTo(new BigDecimal(0))>0 && paySingleLimitAmt.compareTo(new BigDecimal(0))>0){//-1表示小于,0是等于,1是大于。
				if(tradeAmt.compareTo(singleLimitAmt)>0 || tradeAmt.compareTo(paySingleLimitAmt)>0){//如果订单金额大于日交易单笔限额
					TradeException tradeException = new TradeException();
					tradeException.setErrorCode(RespEnum.E00043.getCode());
					tradeException.setErrorMsg(RespEnum.E00043.getDesc());
					throw tradeException;
				}
			}
			BigDecimal todayTotalAmount = new BigDecimal(0);
			TradeOrder todayTotalAmountObject = this.tradeOrderMapper.getTodayTotalAmount(param);//获取当前商户当日交易额
			if(todayTotalAmountObject!=null){
				BigDecimal todayTotalAmountDb = todayTotalAmountObject.getOrderAmount();
				todayTotalAmount = todayTotalAmountDb.add(BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt())));
			}else{
				todayTotalAmount = BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt()));
			}

			if(todayTotalAmount.compareTo(payDailyLimitAmt)>0 && payDailyLimitAmt.compareTo(new BigDecimal(0))>0){//当日交易额已经达到日限额 //-1表示小于,0是等于,1是大于。
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00044.getCode());
				tradeException.setErrorMsg(RespEnum.E00044.getDesc());
				throw tradeException;
			}
			
			BigDecimal dailyLimitAmt = merchantPaywayMapping.getDailyLimitAmt();//账户日限额,单个支付方式
			Object obj = this.redisCache.getCacheObject("dailyLimitAmt"+payRequest.getMercNo());
			BigDecimal orderAmont=null;
			if(obj==null){
				orderAmont =  BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt()));
				//this.redisCacheUtils.set("dailyLimitAmt"+payRequest.getMercNo(),orderAmont);//第一次缓存商户订单金额
			}else {
				BigDecimal cacheAmount = BigDecimal.valueOf(Double.valueOf(String.valueOf(obj)));
				orderAmont =  Arith.add(cacheAmount, BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt())));
				//this.redisCacheUtils.set("dailyLimitAmt"+payRequest.getMercNo(), orderAmont);//缓存累加后的订单金额
			}
			
			if(orderAmont.compareTo(dailyLimitAmt)>0 && dailyLimitAmt.compareTo(new BigDecimal(0))>0){//当日交易额已经达到日限额 //-1表示小于,0是等于,1是大于。
				TradeException tradeException = new TradeException();
				tradeException.setErrorCode(RespEnum.E00044.getCode());
				tradeException.setErrorMsg(RespEnum.E00044.getDesc());
				throw tradeException;
			}
			
			//payQuery 下游商户查询接口payRequest.getInterfaceCode();
			//step1下游调用支付接口前做相关参数校验,调用验签方法

			payRequest.setPayingMercNo(merchantChannelMapping.getPayingMercNo());//上游商户号
			
			PayChannel payChannel = (PayChannel)redisCache.getCacheObject(merchantChannelMapping.getChannelCode());
			//写支付订单表
			TradeOrder tradeOrder = new TradeOrder();
			//String orderId = this.serialNumber("ORDER_NO");//支付订单号

			String orderId = String.valueOf(idWorker.nextId());
			tradeOrder.setOrderId(orderId);
			tradeOrder.setMercNo(payRequest.getMercNo());
			tradeOrder.setMercOrderNo(payRequest.getMercOrderNo());
			tradeOrder.setTradeType(payRequest.getTradeType());
			tradeOrder.setSubject(payRequest.getSubject());//订单名称
			tradeOrder.setBody(payRequest.getBody());//订单说明
			tradeOrder.setOrderAmount(BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt())));//订单金额,单位元
			tradeOrder.setOrderTime(DateUtils.parseDateTime(this.commonMapper.getMysqlDbDate()));//系统下单时间(本系统时间)
			tradeOrder.setOrderIp(payRequest.getIp());
			tradeOrder.setReturnUrl(payRequest.getReturnUrl());//页面回调通知URL
			tradeOrder.setNotifyUrl(payRequest.getNotifyUrl());//后台异步通知URL
			tradeOrder.setRefererUrl(payRequest.getRefererUrl());//客户端重定向地址
			tradeOrder.setOrderPeriod(Integer.valueOf(payRequest.getOrderPeriod()));//订单有效期（单位分钟）
			tradeOrder.setCreateTime(DateUtils.parseTime(payRequest.getTradeTime()));//下游提交支付时间
			tradeOrder.setPayStatus(PayStatus.p0002.getCode());//订单初始状态为p0002 待支付,P0000支付成功，p0004支付失败，p0007超时未支付
			tradeOrder.setIsRefund("n");//n未退款 y代表正在退款
			tradeOrder.setRefundTimes(0);//退款次数 默认值为0
			tradeOrder.setTermType(payRequest.getTermType());//发起类型(终端接入类型)
			tradeOrder.setSignType(payRequest.getSignType());//商户签名类型
			tradeOrder.setCharset(payRequest.getCharset());
			tradeOrder.setVersion(payRequest.getVersion());
			tradeOrder.setLocale(payRequest.getLocale());
			tradeOrder.setCurrency(payRequest.getFeeType());//币种
			tradeOrder.setInterfaceCode(payRequest.getInterfaceCode());//交易码/交易类型
			tradeOrder.setChannelCode(payChannel.getChannelCode());//支付渠道编号
			tradeOrder.setPaywayCode(payRequest.getPaywayCode());//支付方式编号
			tradeOrder.setSceneCode(payRequest.getSceneCode());//场景编号
			
			
			tradeOrder.setIsStatic("0");//是否统计  0，未统计  1.已统计.此状态用于报表统计
			
			
			TradeOrderSeq tradeOrderSeq = new TradeOrderSeq();
			tradeOrderSeq.setOrderId(orderId);//支付订单号
			tradeOrderSeq.setMercNo(payRequest.getMercNo());//商家编号
			tradeOrderSeq.setMercOrderNo(payRequest.getMercOrderNo());//商户订单号
			tradeOrderSeq.setTradeType(payRequest.getTradeType());//交易业务类型
			tradeOrderSeq.setSubject(payRequest.getSubject());
			tradeOrderSeq.setBody(payRequest.getBody());
			//String tradeSeq = this.serialNumber("ORDER_SEQ_NO");//支付流水号
			String tradeSeq = String.valueOf(idWorker.nextId());
			tradeOrderSeq.setTradeSeq(tradeSeq);//支付流水号
			tradeOrderSeq.setOrderIp(payRequest.getIp());
			tradeOrderSeq.setOrderAmount(BigDecimal.valueOf(Double.valueOf(payRequest.getTradeAmt())));
			tradeOrderSeq.setChannelCode(payChannel.getChannelCode());//支付通道编号
			tradeOrderSeq.setCreateTime(DateUtils.parseDateTime(this.commonMapper.getMysqlDbDate()));//创建时间
			tradeOrderSeq.setStatus(PayStatus.p0002.getCode());//订单初始状态为p0002 待支付,P0000支付成功，p0004支付失败，p0007超时未支付
			tradeOrderSeq.setPaywayCode(payRequest.getPaywayCode());//支付方式
			tradeOrderSeq.setPayingMercNo(merchantChannelMapping.getPayingMercNo());//渠道商户号
			tradeOrderSeq.setPayingMercName(merchantChannelMapping.getPayingMercName());//渠道商户名称
			tradeOrderSeq.setConnectSwitch("01");//接入类型 01直连 02
			
			tradeOrderSeq.setSceneCode(payRequest.getSceneCode());//场景编号
			PayChannelScenes payChannelScenes =  (PayChannelScenes)redisCache.getCacheObject(payChannel.getChannelCode()+payRequest.getPaywayCode()+payRequest.getSceneCode());//支付通道编号+支付方式+场景编号
			tradeOrder.setCostRatio(payChannelScenes.getCostRate());//渠道成本比例
			tradeOrder.setFeeRatio(merchantPaywayMapping.getMercFeeValue());//手续费比例

			tradeOrderSeq.setCostRatio(payChannelScenes.getCostRate());//渠道成本比例
			tradeOrderSeq.setFeeRatio(merchantPaywayMapping.getMercFeeValue());//商户手续费比例

			this.tradeOrderMapper.insert(tradeOrder);
			this.tradeOrderSeqMapper.insert(tradeOrderSeq);
			
			//step2根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(payChannel.getServiceBeanId());//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doPay", new Class[] {PayRequest.class});
			
			payRequest.setOrderId(orderId);//设置rxpay订单ID上传给上游渠道(微信，支付宝)
			//step3调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			//String payingMercNo = merchantChannelMapping.getPayingMercNo();//渠道商户号
			PayBackBean payBackBean =(PayBackBean) method.invoke(clazz, new Object[] {payRequest });//同步返回预支付结果
			
			//更新系统支付订单表及支付流水表（rx_trade_order,rx_trade_order_seq）
			//	appid
			String appid  = payBackBean.getAppid();
			if(!cn.itbeien.common.util.StringUtils.isNull(appid)){
				tradeOrder.setAppId(appid);
				tradeOrderMapper.updateByPrimaryKey(tradeOrder);
			}
			
			tradeOrderSeq.setRetCode(payBackBean.getStatus());//返回状态码,上游返回
			tradeOrderSeq.setRetDesc(payBackBean.getMessage());//返回消息,上游返回
			tradeOrderSeq.setRespCode(payBackBean.getErrCode());//错误代码,rxpay平台返回代码.000000代表处理成功
			tradeOrderSeq.setRespDesc(payBackBean.getErrMsg());//错误代码描述 rxpay平台返回描述

			tradeOrderSeqMapper.updateByPrimaryKey(tradeOrderSeq);
			redisCache.setCacheObject(tradeOrderSeq.getMercOrderNo()+"mchNotifyUrl", payRequest.getNotifyUrl());//缓存下游商户异步回调url
			//默认通道停用后根据商户支付渠道管理表创建时间获取最新创建的支付渠道,状态为启用---后续版本实现
			
			//返回下游商户预支付处理结果
			PayResponse payResponse = composeReturnMsg(payBackBean,payRequest,orderId);
			return payResponse;
		}catch(Exception e){
			log.error("支付交易流程异常:"+payRequest.getMercOrderNo()+":"+e.getMessage(),e);
			throw e;
		}
		
	}
	

	
	/**
	 * 
	 * 方法用途:组装返回给下游商户的预支付处理结果 <br>
	 * @param payBackBean
	 * @param payRequest
	 * @param orderId 平台订单号(rxpay订单号)
	 * @return
	 */
	public PayResponse composeReturnMsg(PayBackBean payBackBean,PayRequest payRequest,String orderId) {
		PayResponse payResponse = new PayResponse();
		payResponse.setMercOrderNo(payRequest.getMercOrderNo());//下游商户自己生成的唯一的订单号
		payResponse.setTradeType(payRequest.getTradeType());//交易类型
		payResponse.setInterfaceCode(payRequest.getInterfaceCode());
		payResponse.setTradeAmt(payBackBean.getAmount());//上游返回交易金额
		payResponse.setTradeTime(payRequest.getTradeTime());
		payResponse.setFeeType(payRequest.getFeeType());//币种
		payResponse.setTermType(payRequest.getTermType());
		payResponse.setOrderId(orderId);//平台订单号(我方系统)
		payResponse.setCodeUrl(payBackBean.getCodeUrl());
		payResponse.setImgUrl(payBackBean.getCodeImgUrl());
		payResponse.setMwebUrl(payBackBean.getPayInfo());
		if(cn.itbeien.common.util.StringUtils.isEmpty(payBackBean.getErrCode())){
			payResponse.setRespCode(RespEnum.S00000.getCode());//000000 表示当前交易成功
			payResponse.setRespDesc(RespEnum.S00000.getDesc());//状态码描述
		}else{
			payResponse.setRespCode(payBackBean.getErrCode());//000000 表示当前交易成功
			payResponse.setRespDesc(payBackBean.getErrMsg());//状态码描述
		}

		payResponse.setMercNo(payRequest.getMercNo());//下游商户号
		if(!cn.itbeien.common.util.StringUtils.isNull(payBackBean.getResultHtml())){//返回的html不为空，则做页面跳转(银联同步返回html)
			payResponse.setResultHtml(payBackBean.getResultHtml());//网银支付返回html
		}
		
		return  payResponse;
	}
	
	
	
	
	
	/**
	 * 接收上游渠道支付结果通过，上游异步通知支付结果
	 */
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public void notifyInfo(PayInfoBean payInfoBean){
		try{
			//平台通过notify_url通知商户，商户做业务处理后，需要以字符串的形式反馈处理结果，内容如下
			//返回结果	结果说明
			//success	处理成功，平台收到此结果后不再进行后续通知
			//fail或其它字符	处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
			//接收到渠道支付结果之后去操作商户虚拟账户表、商户资金调整明细表
			//支付成功后更新商户虚拟账户表及商户资金调整明细表RX_MERCHANT_ACC_REL,RX_MERC_ADJUST_DETAIL  //商户编号及账号类型
			//当商户结算方式为T0，T1，支付当天为节假日。把当天支付金额写入商户虚拟账户表冻结金额字段freeze_bal
			Date callBackTime = new Date();
			TradeOrder tradeOrder = this.tradeOrderMapper.selectByPrimaryKey(payInfoBean.getOutTradeNo());//根据订单号获取支付订单表
			tradeOrder.setCallBackTime(callBackTime);//上游回调时间
			Date orderTime = tradeOrder.getOrderTime();//系统下单时间(我方系统)
			long timeDifference = DateUtils.diffDateTime(orderTime,callBackTime);//下单时间和上游回调时间差(秒),TIME_DIFFERENCE
			tradeOrder.setTimeDifference(timeDifference);//回调时间差(秒)
			//MercAdjustDetail mercAdjustDetail = new MercAdjustDetail();//商户资金调整明细表
			TradeOrderSeq tradeOrderSeq = this.tradeOrderSeqMapper.selectOrderByOrderId(tradeOrder.getOrderId());
			
			tradeOrderSeq.setBankTradeSeq(payInfoBean.getTransactionId());//上游订单号
			tradeOrderSeq.setCompleteTime(DateUtils.parseTime(payInfoBean.getTimeEnd()));//支付完成时间,上游返回
			//支付成功
			if("0".equals(payInfoBean.getPayResult())){

				tradeOrderSeq.setStatus(PayStatus.p0000.getCode());//支付成功
				tradeOrder.setPayStatus(PayStatus.p0000.getCode());//支付成功

				//获取商户费率从商户支付方式映射表
				MerchantPaywayMapping merchantPaywayMapping = (MerchantPaywayMapping)redisCache.getCacheObject(tradeOrder.getMercNo()+tradeOrder.getPaywayCode()+tradeOrder.getSceneCode());//商户号+支付方式编号+场景编号 唯一确认一条记录
				//获取商户支付渠道管理表中缓存(下游上传商户号，支付方式，支付场景)
				MerchantChannelMapping merchantChannelMapping = (MerchantChannelMapping) redisCache.getCacheObject("channelMapping"+tradeOrder.getMercNo()+tradeOrder.getPaywayCode()+tradeOrder.getSceneCode());
				PayChannel payChannel = (PayChannel)redisCache.getCacheObject(merchantChannelMapping.getChannelCode());


				RedisLock lock = new RedisLock(redisTemplate, tradeOrder.getMercNo(), 50000, 60000);
				try {
					if (lock.lock()) {
						Object obj = this.redisCache.getCacheObject("dailyLimitAmt"+tradeOrder.getMercNo());
						BigDecimal orderAmont=null;
						if(obj==null){//缓存日消费金额
							orderAmont =  tradeOrder.getOrderAmount();
							this.redisCache.setCacheObject("dailyLimitAmt"+tradeOrder.getMercNo(),orderAmont);//第一次缓存商户订单金额
						}else {
							BigDecimal cacheAmount = BigDecimal.valueOf(Double.valueOf(String.valueOf(obj)));
							orderAmont =  Arith.add(cacheAmount, tradeOrder.getOrderAmount());
							this.redisCache.setCacheObject("dailyLimitAmt"+tradeOrder.getMercNo(), orderAmont);//缓存累加后的订单金额
						}


						BigDecimal mercFeeValue =merchantPaywayMapping.getMercFeeValue();//统计商户手续费的比例(千分比)，已经按照千分比计算
						//MathContext mc = new MathContext(3,RoundingMode.HALF_UP);//精度两位，四舍五入
						BigDecimal feeValue = mercFeeValue.multiply(tradeOrder.getOrderAmount());//商户手续费=订单金额*商户手续费比例
                        feeValue = Arith.round(feeValue, 2);//保留两位小数
						BigDecimal minFeeThreshold = payChannel.getMinFeeThreshold();//支付渠道保底支付手续费

						if(minFeeThreshold == null){
							minFeeThreshold = new BigDecimal(0.01);//如果手续费为null设置默认手续费为1分钱
						}
						if(feeValue.compareTo(minFeeThreshold)<0){
							tradeOrder.setFeeValue(minFeeThreshold);//(商户手续费=订单金额*商户手续费比例)如果商户手续费计算出来小于支付渠道保底手续费则使用保底手续费
						}else{
							tradeOrder.setFeeValue(feeValue);//商户手续费=订单金额*商户手续费比例
						}

						if(feeValue.compareTo(minFeeThreshold)<0){
							tradeOrderSeq.setFeeValue(minFeeThreshold);//(商户手续费=订单金额*商户手续费比例)如果商户手续费计算出来小于支付渠道保底手续费则使用保底手续费
						}else{
							tradeOrderSeq.setFeeValue(feeValue);//商户手续费=订单金额*商户手续费比例
						}

						BigDecimal costValue = tradeOrder.getOrderAmount().multiply(tradeOrder.getCostRatio()); //渠道成本手续费=订单交易金额*渠道成本比例
						costValue = Arith.round(costValue, 2);//保留两位小数
						tradeOrder.setCostValue(costValue);//更新渠道成本手续费
						tradeOrderSeq.setCostValue(costValue);//更新渠道成本手续费
						tradeOrder.setFeeRatio(mercFeeValue);//商户手续费比例
						tradeOrderSeq.setFeeRatio(mercFeeValue);//商户手续费比例
						this.tradeOrderMapper.updateByPrimaryKey(tradeOrder);
						this.tradeOrderSeqMapper.updateByPrimaryKey(tradeOrderSeq);


						MerchantAccRel merchantAccRel = null;// 商户虚拟账户表
						merchantAccRel = this.merchantAccRelMapper.selectByMercAcct(tradeOrder.getMercNo(), "00");// 根据账号类型及商户编号查询商户虚拟账户表

						log.info("上游支付异步通知 --平台订单号:" + tradeOrder.getOrderId() + ",商户" + merchantAccRel.getMercNo() + "对应虚户表中的原总金额为:"
								+ Arith.round(merchantAccRel.getAcctBal(), 2) + ",冻结金额为:" +  Arith.round(merchantAccRel.getFreezeBal(), 2) + ",可用金额为:" +  Arith.round(merchantAccRel.getAcctAvaiBal(),2 )
								+ ",入账总金额为:" + Arith.round(merchantAccRel.getInAmt(),2) + ",手续费为:"+ Arith.round(merchantAccRel.getFeeBal(),2) + ",手工已冻结金额:" + Arith.round(merchantAccRel.getFreezeAmt(),2) + ",手工冻结总额:" + Arith.round(merchantAccRel.getFreezeTotalAmt(), 2));
						//mercAdjustDetail.setBankTradeSeq(tradeOrderSeq.getBankTradeSeq());// 上游流水号
						merchantAccRel.setMercNo(tradeOrder.getMercNo());// 下游商户号
						BigDecimal feeBal = merchantAccRel.getFeeBal();// 手续费金额
						if (feeBal == null) {
							feeBal = BigDecimal.valueOf(0);
						}
						merchantAccRel.setFeeBal(Arith.add(tradeOrder.getFeeValue(), feeBal));// 手续费金额（每次累加）

						// 计算统计冻结金额，记录到商户冻结明细表.通过节假日表获取当天交易日是否为节假日及周末(T0,T1).如果不为节假日T1，D1进行当天冻结，如果第二天为节假日及周末T1需要继续冻结.解冻功能通过定时任务进行处理
						// 从缓存中获取商户信息表结算比例字段（merc_scale_rate）
						MerchantInfo merchantInfo = (MerchantInfo) this.redisCache.getCacheObject(tradeOrder.getMercNo());// 根据下游商户号获取商户缓存信息
						// 通过下游商户编号及账户类型（00）获取手工冻结总额(FREEZE_TOTAL_AMT),支付交易时根据手工冻结总额及手工已冻结金额设置手动已冻结金额(FREEZE_AMT);
						BigDecimal freezeTotalAmt = merchantAccRel.getFreezeTotalAmt();// 手工冻结总额
						if (freezeTotalAmt == null) {
							freezeTotalAmt = BigDecimal.valueOf(0);
						}
						BigDecimal freezeAmt = merchantAccRel.getFreezeAmt();// 手工已冻结金额
						if (freezeAmt == null) {
							freezeAmt = BigDecimal.valueOf(0);
						}
						BigDecimal gpfdFreeze = Arith.sub(freezeTotalAmt, freezeAmt);// 手工还需要冻结金额
						BigDecimal freezeBal = BigDecimal.valueOf(0);
						BigDecimal orderAmount = tradeOrder.getOrderAmount();// 订单支付金额
						BigDecimal num = Arith.sub(Arith.sub(gpfdFreeze, orderAmount), feeBal);// 手工需要冻结金额-当前订单金额-手续费
						int compareValue = gpfdFreeze.compareTo(BigDecimal.valueOf(0));// compareValue=-1,表示gpfdFreeze小于0;
						// compareValue=0,表示gpfdFreeze等于0;compareValue=1,表示gpfdFreeze大于0;
						int compare = num.compareTo(BigDecimal.valueOf(0));
						if (compareValue > 0) {// -1,表示compareValue小于0;
							// 0,表示compareValue等于0;
							// 1,表示compareValue大于0;
							BigDecimal amount = Arith.sub(orderAmount, tradeOrder.getFeeValue());// 当前交易金额
							freezeBal = amount;// 冻结金额为当前支付金额-支付手续费
							merchantAccRel.setFreezeAmt(Arith.add(freezeBal, merchantAccRel.getFreezeAmt()));// 手工已冻结金额
							if (compare > 0) {// -1,表示compare小于0;  0,表示compare等于0;1,表示compare大于0;
								tradeOrder.setOrderAmount(num);
								freezeBal = this.statisticsSum(merchantInfo, tradeOrder, tradeOrderSeq.getPayingMercNo());// 统计冻结金额
							}
						} else {
							freezeBal = this.statisticsSum(merchantInfo, tradeOrder, tradeOrderSeq.getPayingMercNo());// 统计冻结金额
						}
						BigDecimal acctAvaiBal = Arith.sub(Arith.sub(tradeOrder.getOrderAmount(), tradeOrder.getFeeValue()),freezeBal);// 当前支付订单扣除冻结金额后的可用余额
						merchantAccRel.setAcctAvaiBal(Arith.add(acctAvaiBal, merchantAccRel.getAcctAvaiBal()));// 可用余额，扣除手续费后的金额
						merchantAccRel.setAcctBal(Arith.add(Arith.add(freezeBal, acctAvaiBal), merchantAccRel.getAcctBal()));// 总余额(扣除手续费后的金额
						// 总金额=可用余额+冻结金额),扣除手续费后
						merchantAccRel.setInAmt(Arith.add(Arith.sub(tradeOrder.getOrderAmount(), tradeOrder.getFeeValue()), merchantAccRel.getInAmt()));// 入账总金额（扣除手续费后的金额）,历史金额+当前金额
						merchantAccRel.setFreezeBal(Arith.add(merchantAccRel.getFreezeBal(), freezeBal));// 冻结金额，扣除手续费后的金额
						merchantAccRel.setUpdateTime(new Date());//更新虚户时间
						this.merchantAccRelMapper.updateBymercNoSelective(merchantAccRel);// 更新商户虚拟账户表
						log.info("上游支付异步通知 --平台订单号:" + tradeOrder.getOrderId() + ",商户" + merchantAccRel.getMercNo() + "对应虚户表中的更新后总金额为:"
								+ Arith.round(merchantAccRel.getAcctBal(),2) + ",冻结金额为:" +  Arith.round(merchantAccRel.getFreezeBal(),2) + ",可用金额为:" +  Arith.round(merchantAccRel.getAcctAvaiBal(),2)
								+ ",入账总金额为:" + Arith.round(merchantAccRel.getInAmt(),2) + ",手续费为:"+ Arith.round(merchantAccRel.getFeeBal(),2) + ",手工已冻结金额:" + Arith.round(merchantAccRel.getFreezeAmt(),2) + ",手工冻结总额:" + Arith.round(merchantAccRel.getFreezeTotalAmt(),2));
						// this.merchantAdjustDetailMapper.insert(mercAdjustDetail);//新增商户资金调整明细表，支付交易不记录商户资金调整明细表，对账模块(支付订单对账、代付订单对账)的调帐功能记录此表(异常订单)
						// 记录商户渠道余额表，先根据商户编号、支付渠道编号、渠道商户号获取记录。如果记录存在就更新，不存在就新增
						saveMchChannelBal(tradeOrder, tradeOrderSeq, freezeBal);
					}
				} catch (InterruptedException e) {
					log.error("分布式锁异常:" + e.getMessage(), e);
				} finally {
					// 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做del操作，因为可能客户端因为某个耗时的操作而挂起，
					lock.unlock();
				}

			}else{
				tradeOrderSeq.setStatus(PayStatus.p0004.getCode());//支付失败
				tradeOrder.setPayStatus(PayStatus.p0004.getCode());//支付失败
				this.tradeOrderMapper.updateByPrimaryKey(tradeOrder);
				this.tradeOrderSeqMapper.updateByPrimaryKey(tradeOrderSeq);
			}
			

			MchNotifyMqContent mchNotifyMqContent = new MchNotifyMqContent();
			PayNotifyResponse payNotifyResponse = new PayNotifyResponse();
			payNotifyResponse.setVersion(tradeOrder.getVersion());
			payNotifyResponse.setLocale(tradeOrder.getLocale());
			payNotifyResponse.setInterfaceCode(tradeOrder.getInterfaceCode());
			payNotifyResponse.setCharset(tradeOrder.getCharset());
			payNotifyResponse.setSignType(tradeOrder.getSignType());
			payNotifyResponse.setMercNo(tradeOrder.getMercNo());
			payNotifyResponse.setMercOrderNo(tradeOrder.getMercOrderNo());
			payNotifyResponse.setTradeType(tradeOrder.getTradeType());
			payNotifyResponse.setTradeAmt(String.valueOf(tradeOrder.getOrderAmount()));
			payNotifyResponse.setTradeTime(DateUtils.formatTime(tradeOrder.getOrderTime()));
			payNotifyResponse.setFeeType(tradeOrder.getCurrency());
			payNotifyResponse.setTermType(tradeOrder.getTermType());
			payNotifyResponse.setOrderId(tradeOrder.getOrderId());
			payNotifyResponse.setTradeEndTime(DateUtils.formatTime(tradeOrderSeq.getCompleteTime()));
			payNotifyResponse.setPayStatus(tradeOrderSeq.getStatus());
			payNotifyResponse.setMercNo(tradeOrderSeq.getMercNo());//下游商户号
			payNotifyResponse.setNotifyUrl(tradeOrder.getNotifyUrl()); // 回调下游URL
			//DtRocketMqProducer rxRocketMqProducer = (DtRocketMqProducer)SpringContextUtil.getBean("rxRocketMqProducer");//消息生产者
			BeanUtils.copyProperties(payNotifyResponse, mchNotifyMqContent);
			//rxRocketMqProducer.mqNotifyMch(mchNotifyMqContent);
			//rxRocketMqProducer.realTimeMqNotifyMch(mchNotifyMqContent);
		}catch(Exception e){
			throw e;
		}
	}
	
	
	/**
	 * 
	 * 方法用途:提供给下游查询订单结果的方法,查询rxpay数据库 <br>
	 * @param payQueryRequest
	 * @return
	 */
	public PayQueryResponse payQuery(PayQueryRequest payQueryRequest){
		PayQueryResponse payQueryResponse = new PayQueryResponse();
		TradeOrder tradeOrder = this.tradeOrderMapper.selectByMechOrderId(payQueryRequest.getMercOrderNo(),payQueryRequest.getOrderId());//根据下游商户订单号获取支付订单
		if(tradeOrder == null) {
			payQueryResponse.setVersion(payQueryRequest.getVersion());
			payQueryResponse.setLocale(payQueryRequest.getLocale());
			payQueryResponse.setInterfaceCode(payQueryRequest.getInterfaceCode());
			payQueryResponse.setCharset(payQueryRequest.getCharset());
			payQueryResponse.setSignType(payQueryRequest.getSignType());
			payQueryResponse.setNoticeStr(payQueryRequest.getNoticeStr());
			payQueryResponse.setMercNo(payQueryRequest.getMercNo());//下游商户号
			payQueryResponse.setRespCode(RespEnum.E00017.getCode());
			payQueryResponse.setRespDesc(RespEnum.E00017.getDesc());
			return payQueryResponse;
		}
		TradeOrderSeq tradeOrderSeq = this.tradeOrderSeqMapper.selectOrderByOrderId(tradeOrder.getOrderId());
		payQueryResponse.setMercOrderNo(tradeOrder.getMercOrderNo());//商户订单号
		payQueryResponse.setTradeType(tradeOrder.getTradeType());//交易类型
		payQueryResponse.setFeeType(tradeOrder.getCurrency());//币种
		payQueryResponse.setTradeAmt(String.valueOf(tradeOrder.getOrderAmount()));//订单金额
		payQueryResponse.setTradeTime(DateUtils.format(tradeOrder.getCreateTime(),"yyyyMMddHHmmss"));//支付提交时间，对应我方系统订单创建时间
		payQueryResponse.setTermType(tradeOrder.getTermType());//终端接入类型
		payQueryResponse.setOrderId(tradeOrder.getOrderId());
		if(tradeOrderSeq.getCompleteTime()==null){
			payQueryResponse.setTradeEndTime(DateUtils.format(tradeOrder.getCreateTime(),"yyyyMMddHHmmss"));//交易完成时间设置为订单创建时间(下游提交支付时间)
		}else {
			payQueryResponse.setTradeEndTime(DateUtils.format(tradeOrderSeq.getCompleteTime(),"yyyyMMddHHmmss"));//交易完成时间
		}
		payQueryResponse.setPayStatus(tradeOrder.getPayStatus());//支付状态
		payQueryResponse.setVersion(payQueryRequest.getVersion());
		payQueryResponse.setLocale(payQueryRequest.getLocale());
		payQueryResponse.setInterfaceCode(payQueryRequest.getInterfaceCode());
		payQueryResponse.setCharset(payQueryRequest.getCharset());
		payQueryResponse.setSignType(payQueryRequest.getSignType());
		payQueryResponse.setNoticeStr(payQueryRequest.getNoticeStr());
		payQueryResponse.setMercNo(payQueryRequest.getMercNo());//下游商户号
		payQueryResponse.setRespCode(RespEnum.S00000.getCode());
		payQueryResponse.setRespDesc(RespEnum.S00000.getDesc());
		
		return payQueryResponse;
	}
	
	
	/**
	 * 支付交易时更新商户渠道余额表
	 * 方法用途: <br>
	 * @param tradeOrder
	 * @param tradeOrderSeq
	 * @param freezeBal 冻结金额
	 */
	public void saveMchChannelBal(TradeOrder tradeOrder,TradeOrderSeq tradeOrderSeq,BigDecimal freezeBal){
		MerchantChannelBalKey key = new MerchantChannelBalKey();
		key.setMercNo(tradeOrder.getMercNo());//下游商户编号
		key.setChannelCode(tradeOrder.getChannelCode());//支付渠道编号
		key.setPayingMercNo(tradeOrderSeq.getPayingMercNo());//渠道商户号
		BigDecimal orderAmount  = Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue());//单笔订单金额-手续费=实际入账金额
		BigDecimal chnAvaiBal = Arith.sub(orderAmount,freezeBal);//可用余额=订单金额-手续费-冻结金额
		MerchantChannelBal merchantChannelBal = this.merchantChannelBalMapper.selectByPrimaryKey(key);//先根据商户编号、支付渠道编号、渠道商户号获取记录。如果记录存在就更新，不存在就新增
		if(cn.itbeien.common.util.StringUtils.isNotNull(merchantChannelBal)){
			log.info("上游支付异步通知 --平台订单号:" + tradeOrderSeq.getOrderId() + ",商户" + tradeOrder.getMercNo() + "--" + tradeOrder.getChannelCode() + "--" + tradeOrderSeq.getPayingMercNo()
				+ "对应渠道余额表中原总金额为:" + Arith.round(merchantChannelBal.getChnBal(),2) + ",可用余额：" + Arith.round(merchantChannelBal.getChnAvaiBal(),2) + ",冻结金额为:" + Arith.round(merchantChannelBal.getChnFreezeBal(),2) + ",手续费为:" + Arith.round(merchantChannelBal.getChnFeeBal(),2) + ",入账总金额:" + Arith.round(merchantChannelBal.getChnInAmt(),2));
			merchantChannelBal.setChnBal(Arith.add(orderAmount, merchantChannelBal.getChnBal()));//总余额=原来的总余额+单笔订单金额
			merchantChannelBal.setChnAvaiBal(Arith.add(chnAvaiBal, merchantChannelBal.getChnAvaiBal()));//可用余额=原来的可用余额+当前可用余额
			merchantChannelBal.setChnFreezeBal(Arith.add(freezeBal, merchantChannelBal.getChnFreezeBal()));//冻结余额=原来冻结余额+当前冻结余额
			merchantChannelBal.setChnFeeBal(Arith.add(tradeOrder.getFeeValue(), merchantChannelBal.getChnFeeBal()));//手续费金额=原来的手续费金额+当前手续费金额
			merchantChannelBal.setChnInAmt(Arith.add(orderAmount,merchantChannelBal.getChnInAmt()));//入账总金额=原有入账总金额+(当前订单订单金额-当前订单金额手续费)
			merchantChannelBal.setCreateTime(new Date());//更新创建时间
			this.merchantChannelBalMapper.updateByPrimaryKey(merchantChannelBal);
			log.info("上游支付异步通知 --平台订单号:" + tradeOrderSeq.getOrderId() + ",商户" + tradeOrder.getMercNo() + "--" + tradeOrder.getChannelCode() + "--" + tradeOrderSeq.getPayingMercNo()
			+ "对应渠道余额表中更新后总金额为:" + Arith.round(merchantChannelBal.getChnBal(),2) + ",可用余额：" + Arith.round(merchantChannelBal.getChnAvaiBal(),2) + ",冻结金额为:" + Arith.round(merchantChannelBal.getChnFreezeBal(),2) + ",手续费为:" + Arith.round(merchantChannelBal.getChnFeeBal(),2) + ",入账总金额:" + Arith.round(merchantChannelBal.getChnInAmt(),2));
		}else{
			MerchantChannelBal record = new MerchantChannelBal();
			record.setMercNo(tradeOrder.getMercNo());
			record.setChannelCode(tradeOrder.getChannelCode());
			record.setPayingMercNo(tradeOrderSeq.getPayingMercNo());
			record.setChnBal(orderAmount);//总余额=单笔订单金额
			record.setChnAvaiBal(chnAvaiBal);//可用余额=订单金额-手续费-冻结金额
			record.setChnFreezeBal(freezeBal);//冻结余额
			record.setChnFeeBal(tradeOrder.getFeeValue());//手续费金额
			record.setCcy(tradeOrder.getCurrency());//币种
			record.setCreateTime(new Date());
			record.setPayingMercName(tradeOrderSeq.getPayingMercName());//渠道商户名称
			record.setChnInAmt(orderAmount);//入账总金额=订单金额-手续费
			record.setChnOutAmt(new BigDecimal("0")); //出账金额默认为0
			this.merchantChannelBalMapper.insert(record);
			log.info("上游支付异步通知 --平台订单号:" + tradeOrderSeq.getOrderId() + ",商户" + tradeOrder.getMercNo() + "--" + tradeOrder.getChannelCode() + "--" + tradeOrderSeq.getPayingMercNo()
			+ "对应渠道余额表中原总金额为:" + Arith.round(record.getChnBal(),2) + ",可用余额：" + Arith.round(record.getChnAvaiBal(),2) + ",冻结金额为:" + Arith.round(record.getChnFreezeBal(),2) + ",手续费为:" + Arith.round(record.getChnFeeBal(),2) + ",入账总金额:" + Arith.round(record.getChnInAmt(),2));
		}
		
	}
	
	
	/**
	 * 获取订单表序列号
	 * @param nextVal 需要生成序列号的名称
	 * 方法用途: <br>
	 * @return
	 */
	private String serialNumber(String nextVal){
		String batchNo = commonMapper.getSeqNextvalByName(nextVal);
		//String dbdate = commonDao.getMysqlDbDate();
		String dbdate = DateUtils.formatTime(new Date());
		if (batchNo.length() < 8) {// 如果序列号不够8位则填充0
			int i = (8 - batchNo.length());
			String prefix = "";
			for (int j = 0; j < i; j++) {
				prefix += "0";
			}
			batchNo = prefix + batchNo;// 以0填充后的序列号
		}
		return dbdate+batchNo;
	}
	
	
	/**
	 * 统计冻结金额、解冻时间,并记录商户冻结明细表
	 * 方法用途: <br>
	 * @return 需要冻结的金额
	 */
	public BigDecimal statisticsSum(MerchantInfo merchantInfo,TradeOrder tradeOrder,String payingMercNo){

		String jsonMercScaleRate =  merchantInfo.getMercScaleRate();//结算比例字段（{"T0":"0.0030","T1":"0.0030","D0":"0.0030","D1":"0.0030"}）
		SettlementRatio settlementRatio = JSON.parseObject(jsonMercScaleRate, SettlementRatio.class);//json格式数据转换为实体类
		
		//获取结算周期各占结算比列
		String T0 = settlementRatio.getT0();
		String T1 = settlementRatio.getT1();
		//String D0 = settlementRatio.getD0();//D0不用冻结
		String D1 = settlementRatio.getD1();

		//根据各结算周期统计各周期的结算金额
		//BigDecimal t0 =  Arith.mul(BigDecimal.valueOf(Double.valueOf(T0)), Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
        BigDecimal t0 =  new BigDecimal(T0).multiply(Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
		//BigDecimal t1 =  Arith.mul(BigDecimal.valueOf(Double.valueOf(T1)), Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
        BigDecimal t1 = new BigDecimal(T1).multiply( Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
		//BigDecimal d0 =  Arith.mul(BigDecimal.valueOf(Double.valueOf(D0)), Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
		//BigDecimal d1 =  Arith.mul(BigDecimal.valueOf(Double.valueOf(D1)), Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
		BigDecimal d1 =  new BigDecimal(D1).multiply(Arith.sub(tradeOrder.getOrderAmount(),tradeOrder.getFeeValue()));
		t0 = Arith.round(t0, 2);//保留两位小数
		t1 = Arith.round(t1, 2);//保留两位小数
        d1 = Arith.round(d1, 2);//保留两位小数

		BigDecimal freezeSum = BigDecimal.valueOf(0);//需要冻结的金额
		//获取交易当天是否为节假日
		//Holiday holiday = holidayMapper.selectByPrimaryKey(DateUtils.parseDate(DateUtils.getLongYMD()));
		boolean flag = this.holidayService.isHolidays();//获取当天是否为节假日
		//String weekDay = holiday.getWeekDay();//获取当天为星期几,星期6，7T0,T1不结算。D0可以结算，D1为下个自然日结算.如果当天为节假日T0,T1为下个工作日进行结算
		if(flag){//为节假日 t0冻结
			freezeSum=t0;
		}
		freezeSum=t1.add(freezeSum);
		freezeSum=d1.add(freezeSum);
		
		FreezeDetail freezeDetail = new FreezeDetail();
		freezeDetail.setOrderId(tradeOrder.getOrderId());
		freezeDetail.setMercNo(tradeOrder.getMercNo());
		freezeDetail.setMercOrderNo(tradeOrder.getMercOrderNo());
		freezeDetail.setTradeType(tradeOrder.getTradeType());
		freezeDetail.setOrderAmount(tradeOrder.getOrderAmount());
		freezeDetail.setRemark(tradeOrder.getRemark());//支付备注
		freezeDetail.setFreezeStatus("00");//00冻结 01解冻
		freezeDetail.setFeeAmount(tradeOrder.getFeeValue());//手续费金额
		freezeDetail.setFreezeAmt(freezeSum);//冻结金额
		freezeDetail.setFreezeTime(new Date());//冻结时间 当前日期
		freezeDetail.setChannelCode(tradeOrder.getChannelCode());
		freezeDetail.setPaywayCode(tradeOrder.getPaywayCode());
		freezeDetail.setSceneCode(tradeOrder.getSceneCode());
		freezeDetail.setPayingMercNo(payingMercNo);
		freezeDetail.setAvaiAmount(Arith.sub(Arith.sub(tradeOrder.getOrderAmount(), freezeSum), tradeOrder.getFeeValue()));//可用金额=订单金额-冻结金额-手续费金额
		double d = 0.0;
		if(BigDecimal.valueOf(d).compareTo(t0)<0){//当t0不为0时记录商户冻结明细表
			String frddzeId = this.serialNumber("FREEZE_NO");//商户冻结明细表主键Id
			freezeDetail.setId(frddzeId);
			freezeDetail.setJsType("t0"); //计算解冻时间,定时任务根据解冻时间进行解冻 
			freezeDetail.setJsScaleRate(BigDecimal.valueOf(Double.valueOf(T0)));//结算比例
			Date date = this.holidayService.nextBusinessDay();//获取下一工作日
			freezeDetail.setUnfreezeTime(date);//解冻时间
			this.freezeDetailMapper.insert(freezeDetail);
		} 
		if(BigDecimal.valueOf(d).compareTo(t1)<0){
			String frddzeId = this.serialNumber("FREEZE_NO");//商户冻结明细表主键Id
			freezeDetail.setId(frddzeId);
			freezeDetail.setJsType("t1");
			freezeDetail.setJsScaleRate(BigDecimal.valueOf(Double.valueOf(T1)));//结算比例
			Date date = this.holidayService.nextBusinessDay();//获取下一工作日
			freezeDetail.setUnfreezeTime(date);//解冻时间
			this.freezeDetailMapper.insert(freezeDetail);
		} 
		/*if(d0!=BigDecimal.valueOf(d)){//d0不用冻结金额
			String frddzeId = this.serialNumber("FREEZE_NO");//商户冻结明细表主键Id
			freezeDetail.setId(frddzeId);
			freezeDetail.setJsType("d0");
			freezeDetail.setUnfreezeTime(new Date());//解冻时间
			this.freezeDetailMapper.insert(freezeDetail);
		} */
		if(BigDecimal.valueOf(d).compareTo(d1)<0){
			String frddzeId = this.serialNumber("FREEZE_NO");//商户冻结明细表主键Id
			freezeDetail.setId(frddzeId);
			freezeDetail.setJsType("d1");
			freezeDetail.setJsScaleRate(BigDecimal.valueOf(Double.valueOf(D1)));//结算比例
			Calendar c = Calendar.getInstance();  
	        c.setTime(new Date());  
	        c.add(Calendar.DAY_OF_MONTH, 1);// 当天+1天  
			freezeDetail.setUnfreezeTime(c.getTime());//解冻时间,当前时间+1天
			this.freezeDetailMapper.insert(freezeDetail);
		}
		
		return freezeSum;//需要冻结的金额
	}

	
}
