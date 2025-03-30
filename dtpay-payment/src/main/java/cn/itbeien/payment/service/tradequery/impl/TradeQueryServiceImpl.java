package cn.itbeien.payment.service.tradequery.impl;

import cn.itbeien.common.entity.FreezeDetail;
import cn.itbeien.common.entity.merchant.MerchantAccRel;
import cn.itbeien.common.entity.merchant.MerchantChannelBal;
import cn.itbeien.common.entity.merchant.MerchantChannelBalKey;
import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.entity.trade.TradeRefund;
import cn.itbeien.common.entity.trade.TradeRefundSeq;
import cn.itbeien.common.enums.ApprStatusEnum;
import cn.itbeien.common.enums.PayForStatusEnum;
import cn.itbeien.common.enums.RefundStatus;
import cn.itbeien.common.mapper.FreezeDetailMapper;
import cn.itbeien.common.mapper.PlatPayDetailMapper;
import cn.itbeien.common.mapper.merchant.MerchantAccRelMapper;
import cn.itbeien.common.mapper.merchant.MerchantChannelBalMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.Arith;
import cn.itbeien.common.util.DateUtils;
import cn.itbeien.common.util.SpringUtils;
import cn.itbeien.common.util.StringUtils;
import cn.itbeien.payment.channel.vo.ChnPayForResponse;
import cn.itbeien.payment.channel.vo.ChnRefundQueryResponse;
import cn.itbeien.payment.component.cache.RedisLock;
import cn.itbeien.payment.core.vo.request.PayForQueryRequest;
import cn.itbeien.payment.core.vo.request.RefundQueryRequest;
import cn.itbeien.payment.core.vo.response.PayForQueryResponse;
import cn.itbeien.payment.core.vo.response.RefundQueryResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.mapper.trade.TradeOrderMapper;
import cn.itbeien.payment.mapper.trade.TradeRefundMapper;
import cn.itbeien.payment.mapper.trade.TradeRefundSeqMapper;
import cn.itbeien.payment.service.route.IRouteService;
import cn.itbeien.payment.service.route.model.RouteResultModel;
import cn.itbeien.payment.service.tradequery.ITradeQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
@Component
@Slf4j
public class TradeQueryServiceImpl implements ITradeQueryService {

	@Autowired
	private TradeRefundMapper tradeRefundMapper;
	
	@Autowired
	private TradeRefundSeqMapper tradeRefundSeqMapper;
	
	@Autowired
	private PlatPayDetailMapper platPayDetailMapper;
	
	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	
	@Autowired
	private IRouteService routeServiceImpl;
	
	@Autowired
	private FreezeDetailMapper freezeDetailMapper;
	
	@Autowired
	private MerchantAccRelMapper merchantAccRelMapper;
	
	@Autowired
	private MerchantChannelBalMapper merchantChannelBalMapper;
	
	@Autowired
	private  RedisTemplate redisTemplate;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisCache redisCacheUtils;
	
	private static final String TIMER_REQUEST_PAYFOR = "timerPayFor";  //定时器请求代付查询接口
	@Override
	public void refundQuery(RefundQueryRequest tradeRequest, RefundQueryResponse tradeResponse) {
		//查询退款记录
	    Map<String,Object> reqMap = new HashMap<String,Object>();
	    reqMap.put("mercNo", tradeRequest.getMercNo());
	    reqMap.put("refundOrderId", tradeRequest.getRefundOrderId());
	    reqMap.put("mercRefundNo", tradeRequest.getMercRefundNo());
	    TradeRefund tradeRefund = tradeRefundMapper.selectByMap(reqMap);
	    
	    if(tradeRefund==null){
	    	throw new TradeException(RespEnum.E00017.getCode(), RespEnum.E00017.getDesc());
	    }
	    
	    TradeRefundSeq tradeRefundSeq = tradeRefundSeqMapper.selectByMap(reqMap);
	    if(tradeRefundSeq==null){
	    	throw new TradeException(RespEnum.E00017.getCode(), RespEnum.E00017.getDesc());
	    }

	    
	    if(RefundStatus.r0000.getCode().equals(tradeRefund.getRedundStatus())
	    		||RefundStatus.r0003.getCode().equals(tradeRefund.getRedundStatus())
	    		||RefundStatus.r0004.equals(tradeRefund.getRedundStatus())){
	    	BeanUtils.copyProperties(tradeRequest, tradeResponse);
	    	BeanUtils.copyProperties(tradeRefund, tradeResponse);
	    	tradeResponse.setInterfaceCode(tradeRequest.getInterfaceCode());
	    	tradeResponse.setRefundStatus(tradeRefundSeq.getRefundStatus());
	    	tradeResponse.setRefundAmt(tradeRefundSeq.getTradeAmt().toString());
	    	tradeResponse.setFeeType("CNY");
	    	tradeResponse.setRespCode(RespEnum.S00000.getCode());
	    	tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
	    }else if(RefundStatus.r0001.getCode().equals(tradeRefund.getRedundStatus())){
	    	//如果为退款中，则去渠道查询退款状态
    	    //路由到对应退款通道
		    RouteResultModel routeResultModel = routeServiceImpl.routeByCondition(tradeRequest.getMercNo(), tradeRefund.getChannelCode(),
		    		tradeRefund.getPaywayCode(), tradeRefund.getSceneCode());
		    tradeRefundSeq.setNoticeStr(tradeRequest.getNoticeStr());
		    this.channelRefundQuery(tradeResponse,tradeRequest,tradeRefund,tradeRefundSeq,routeResultModel.getServiceBeanId());
	    }

	}
	
	
	/**
	 * 查询退款状态
	 * @param tradeResponse
	 * @param refundOrder
	 * @param serviceBeanId
	 */
	private void channelRefundQuery(RefundQueryResponse tradeResponse,RefundQueryRequest tradeRequest,TradeRefund refundOrder,TradeRefundSeq tradeRefundSeq,String serviceBeanId) {
		try{
			//6、发起退款请求
			//根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doRefundQuery", new Class[] {TradeRefundSeq.class});

			//调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnRefundQueryResponse chnRefundQueryResponse =(ChnRefundQueryResponse) method.invoke(clazz, new Object[] {tradeRefundSeq});//同步返回预支付结果
			
			this.handleRefundQueryResult(tradeResponse,refundOrder,tradeRefundSeq,chnRefundQueryResponse);
		}catch(TradeException e){
			log.error("["+refundOrder.getRefundOrderId()+"]退款查询发生异常",e);
			tradeResponse.setRefundOrderId(refundOrder.getRefundOrderId());
			tradeResponse.setRefundStatus(RefundStatus.r0001.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespCode(RespEnum.E99999.getDesc());
			return;
		}catch(Exception e){
			log.error("["+refundOrder.getRefundOrderId()+"]退款查询发生异常",e);
			tradeResponse.setRefundOrderId(refundOrder.getRefundOrderId());
			tradeResponse.setRefundStatus(RefundStatus.r0001.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespCode(RespEnum.E99999.getDesc());
			return;
		}
	}
	
	/**
	 * 更新退款明细
	 * @param tradeResponse
	 * @param refundOrder
	 * @param tradeRefundSeq
	 */
	private void handleRefundQueryResult(RefundQueryResponse tradeResponse,TradeRefund refundOrder,TradeRefundSeq tradeRefundSeq,ChnRefundQueryResponse chnRefundQueryResponse) {
		
		if(chnRefundQueryResponse.getRespCode().equals(RespEnum.S00000.getCode())){//成功
			log.info("查询到t退款交易成功，交易金额为{}", chnRefundQueryResponse.getRefundAmt());
			this.successRefundQueryHandle(tradeResponse,refundOrder,tradeRefundSeq,chnRefundQueryResponse);
		}else if(chnRefundQueryResponse.getRespCode().equals(RespEnum.E00003.getCode())){//上游处理中状态
			tradeResponse.setRefundOrderId(tradeRefundSeq.getRefundOrderId());
			tradeResponse.setRefundStatus(RefundStatus.r0001.getCode());
			tradeResponse.setRespCode(RespEnum.S00000.getCode());
    	    tradeResponse.setRespCode(RespEnum.S00000.getDesc());
		}else{
			this.failRefundQueryHandle(tradeResponse,refundOrder,tradeRefundSeq,chnRefundQueryResponse);
		}
	}
	
	
	/**
	 * 代付失败结果处理--事务控制，异常回滚
	 */
	@Transactional
	protected void failRefundQueryHandle(RefundQueryResponse tradeResponse,TradeRefund refundOrder,TradeRefundSeq refundOrderSeq,ChnRefundQueryResponse chnRefundQueryResponse) {
		//修改代付状态为成功
		refundOrderSeq.setRefundStatus(RefundStatus.r0001.getCode());
		refundOrderSeq.setRetCode(chnRefundQueryResponse.getRetCode());
		refundOrderSeq.setRetDesc(chnRefundQueryResponse.getRetDesc());
		refundOrderSeq.setRespCode(chnRefundQueryResponse.getRespCode());
		refundOrderSeq.setRespDesc(chnRefundQueryResponse.getRespDesc());
		refundOrderSeq.setBankRefundTradeSeq(chnRefundQueryResponse.getBankRefundTradeSeq());
		refundOrderSeq.setRefundCompleteTime(chnRefundQueryResponse.getRefundCompleteTime());
		tradeRefundSeqMapper.updateByPrimaryKeySelective(refundOrderSeq);
		
		//退款交易金额与手续费金额
		
		//设置返回值
		tradeResponse.setRefundOrderId(refundOrderSeq.getRefundOrderId());
		tradeResponse.setRefundStatus(RefundStatus.r0001.getCode());
		tradeResponse.setRespCode(chnRefundQueryResponse.getRespCode());
		tradeResponse.setRespDesc(chnRefundQueryResponse.getRespDesc());
		
	}

	/**
	 * 代付成功结果处理--事务控制异常回滚
	 */
	@Transactional
	protected void successRefundQueryHandle(RefundQueryResponse tradeResponse,TradeRefund refundOrder,TradeRefundSeq refundOrderSeq,ChnRefundQueryResponse chnRefundQueryResponse) {
		//修改代付状态为成功
		refundOrderSeq.setRefundStatus(RefundStatus.r0000.getCode());
		refundOrderSeq.setRetCode(chnRefundQueryResponse.getRetCode());
		refundOrderSeq.setRetDesc(chnRefundQueryResponse.getRetDesc());
		refundOrderSeq.setRespCode(chnRefundQueryResponse.getRespCode());
		refundOrderSeq.setRespDesc(chnRefundQueryResponse.getRespDesc());
		refundOrderSeq.setBankRefundTradeSeq(chnRefundQueryResponse.getBankRefundTradeSeq());
		refundOrderSeq.setRefundCompleteTime(chnRefundQueryResponse.getRefundCompleteTime());
		tradeRefundSeqMapper.updateByPrimaryKeySelective(refundOrderSeq);
		
		refundOrder.setRedundStatus(RefundStatus.r0000.getCode());
		tradeRefundMapper.updateByPrimaryKeySelective(refundOrder);
		
		TradeOrder order =  tradeOrderMapper.selectByPrimaryKey(refundOrder.getOrgOrderId());
		//更新支付订单退款信息
		order.setRefundTimes((order.getRefundTimes()==null?0:order.getRefundTimes())+1);
		BigDecimal successRefundAmount = new BigDecimal(0);
		if(order.getSuccessRefundAmount() != null){
			successRefundAmount = order.getSuccessRefundAmount();
		}
		order.setSuccessRefundAmount(successRefundAmount.add(refundOrder.getTradeAmount()));
		if(order.getSuccessRefundAmount().compareTo(order.getOrderAmount()) >=0){
			order.setIsRefund("y");
		}else{
			order.setIsRefund("n");
		}
		tradeOrderMapper.updateByPrimaryKeySelective(order);
		
		//设置返回值
		tradeResponse.setRefundOrderId(refundOrderSeq.getRefundOrderId());
		tradeResponse.setRefundStatus(RefundStatus.r0000.getCode());
		tradeResponse.setRespCode(chnRefundQueryResponse.getRespCode());
		tradeResponse.setRespDesc(chnRefundQueryResponse.getRespDesc());
		if(chnRefundQueryResponse.getRefundCompleteTime()!= null){
			tradeResponse.setTradeEndTime(DateUtils.formatTime(chnRefundQueryResponse.getRefundCompleteTime()));
		}
	}
	

	@Override
	public void payForQuery(PayForQueryRequest tradeRequest, PayForQueryResponse tradeResponse) {
		//查询
	    Map<String,Object> reqMap = new HashMap<String,Object>();
	    reqMap.put("mercNo", tradeRequest.getMercNo());
	    reqMap.put("payId", tradeRequest.getPayId());
	    reqMap.put("mercOrderNo", tradeRequest.getMercOrderNo());
	    PlatPayDetail platPayDetail = platPayDetailMapper.selectByMap(reqMap);
	    
	    if(platPayDetail==null){
	    	throw new TradeException(RespEnum.E00017.getCode(), RespEnum.E00017.getDesc());
	    }
	    
	    BeanUtils.copyProperties(tradeRequest, tradeResponse);
	    tradeResponse.setInterfaceCode(tradeRequest.getInterfaceCode());
	    tradeResponse.setPayId(platPayDetail.getPayId());
	    tradeResponse.setMercOrderNo(platPayDetail.getMercOrderNo());
	    tradeResponse.setToAcctNo(platPayDetail.getToAcctNo());
	    tradeResponse.setToAcctName(platPayDetail.getToAcctName());
	    tradeResponse.setToAcctType(platPayDetail.getAcctType());
	    tradeResponse.setToBankNo(platPayDetail.getToBankNo());
	    tradeResponse.setToBankName(platPayDetail.getToBankName());
	    tradeResponse.setCity(platPayDetail.getRsfld4());
	    tradeResponse.setProvince(platPayDetail.getRsfld5());
	    tradeResponse.setTradeTime(DateUtils.formatTime(platPayDetail.getTradeTime()));
    	if(PayForStatusEnum.pf0000.getCode().equals(platPayDetail.getStatus())
    			|| PayForStatusEnum.pf0001.getCode().equals(platPayDetail.getStatus())
	    		||PayForStatusEnum.pf0003.getCode().equals(platPayDetail.getStatus())
	    		||PayForStatusEnum.pf0004.getCode().equals(platPayDetail.getStatus())){
    			
    			tradeResponse.setBankPayForTradeSeq(platPayDetail.getBankTradeSeq());
    			tradeResponse.setStatus(platPayDetail.getStatus());
    		    if(PayForStatusEnum.pf0000.getCode().equals(platPayDetail.getStatus())){
    		    	tradeResponse.setFeeType(platPayDetail.getCurrency());
    		    	tradeResponse.setTradeAmt(String.valueOf(platPayDetail.getTransAmt().setScale(2,BigDecimal.ROUND_HALF_UP)));
    		    	tradeResponse.setFeeValue(String.valueOf(platPayDetail.getFeeValue().setScale(2,BigDecimal.ROUND_HALF_UP)));
    		    }
    	    	if(platPayDetail.getSettleTime()!=null){
    	    		tradeResponse.setTradeEndTime(DateUtils.formatTime(platPayDetail.getSettleTime()));
    	    	}
    	    	tradeResponse.setRespCode(RespEnum.S00000.getCode());
    	    	tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
	    }else if(PayForStatusEnum.pf0002.getCode().equals(platPayDetail.getStatus())){
	    	/**
	    	 * 为代付中，则去渠道查询代付状态
	    	 * 1、代付如未发起调用上游商户（处于审核状态）时，则直接返回"代付中"
	    	 * 2、代付如已经发起，则到上游渠道查询代付状态:A、调用上游返回为代付成功，则更新代付表状态  B、调用上游接口返回为其他状态，则等待通知更新表状态
	    	 */
	    	if(ApprStatusEnum.PENDING_APPRV.getCode().equals(platPayDetail.getApprStatus())){
	    		 tradeResponse.setStatus(platPayDetail.getStatus());
	    	     tradeResponse.setRespCode(RespEnum.S00000.getCode());
	    	     tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
	    	}else{
	    		/**
	    		 * 路由到对应代付通道
	    		 * 调用上游并且更新表状态 start
	    		 */
//	    		RouteResultModel  routeResultModel = routeService.routeByCondition(tradeRequest.getMercNo(), platPayDetail.getChannelCode(),
//	    				null, null);
//	    		
//	    		this.channelPayForQuery(tradeResponse,platPayDetail,routeResultModel.getServiceBeanId(), tradeRequest.getFlag());
	    		//end
	    		
	    		/**
	    		 * 路由到对应代付通道
	    		 * 调用上游并且更新表状态 start
	    		 */
	    		tradeResponse.setFeeType(platPayDetail.getCurrency());
	    		tradeResponse.setTradeAmt(String.valueOf(platPayDetail.getTransAmt().setScale(2,BigDecimal.ROUND_HALF_UP)));
	    		tradeResponse.setFeeValue(String.valueOf(platPayDetail.getFeeValue().setScale(2,BigDecimal.ROUND_HALF_UP)));
	    		if(TIMER_REQUEST_PAYFOR.equals(tradeRequest.getFlag())){
	    			RouteResultModel  routeResultModel = routeServiceImpl.routeByCondition(tradeRequest.getMercNo(), platPayDetail.getChannelCode(),
		    				null, null);
	    			this.channelPayForTimeQuery(tradeResponse,platPayDetail,routeResultModel.getServiceBeanId(), tradeRequest.getFlag());
	    		}else{
	    			tradeResponse.setBankPayForTradeSeq(platPayDetail.getBankTradeSeq());
	    			tradeResponse.setStatus(platPayDetail.getStatus());
	     	    	tradeResponse.setRespCode(RespEnum.S00000.getCode());
	     	    	tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
	    			
	    		}
	    	}
	    }
	}
	
	/**
	 * 查询代付状态
	 * @param tradeResponse
	 * @param platPayDetail
	 * @param serviceBeanId
	 * @param flag  判断是定时器请求   还是下游请求
	 */
	private void channelPayForTimeQuery(PayForQueryResponse tradeResponse,PlatPayDetail platPayDetail,String serviceBeanId, String flag) {
		
		try{
			//6、发起退款请求  A、调用上游返回为代付成功，则更新代付表状态  B、调用上游接口返回为其他状态，则等待通知更新表状态
			//根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doPayForQuery", new Class[] {PlatPayDetail.class});

			//调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnPayForResponse chnPayForResponse =(ChnPayForResponse) method.invoke(clazz, new Object[] {platPayDetail});//同步返回预支付结果
			
			if(chnPayForResponse.getRespCode().equals(RespEnum.S00000.getCode())){//成功
				log.info("查询到代付订单号{}交易成功，交易金额为{}",chnPayForResponse.getPayId(),chnPayForResponse.getTradeAmt());
				if(chnPayForResponse.getCompleteTime() != null){
					tradeResponse.setTradeEndTime(DateUtils.formatTime(chnPayForResponse.getCompleteTime()));
				}
				tradeResponse.setBankPayForTradeSeq(chnPayForResponse.getBankPayForTradeSeq());//为定时回调接口返回上游流水号（如高汇通）
				tradeResponse.setStatus(PayForStatusEnum.pf0000.getCode());
				tradeResponse.setRespCode(chnPayForResponse.getRespCode());
				tradeResponse.setRespDesc(chnPayForResponse.getRespDesc());
				tradeResponse.setRetCode(chnPayForResponse.getRetCode());
				tradeResponse.setRetDesc(chnPayForResponse.getRetDesc());
			}else if(chnPayForResponse.getRespCode().equals(RespEnum.E00003.getCode())){//上游处理中状态
				log.info("查询到代付订单号{}处理中，交易金额为{}",chnPayForResponse.getPayId(),chnPayForResponse.getTradeAmt());
				tradeResponse.setStatus(PayForStatusEnum.pf0002.getCode());//处理中
				tradeResponse.setRespCode(RespEnum.S00000.getCode());
	    	    tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
	    		tradeResponse.setRetCode(chnPayForResponse.getRetCode());
				tradeResponse.setRetDesc(chnPayForResponse.getRetDesc());
			} else if(chnPayForResponse.getRespCode().equals(RespEnum.E00015.getCode())){
				log.info("查询到代付订单号{}交易失败，原因是{}",chnPayForResponse.getPayId(),tradeResponse.getRespDesc());
				tradeResponse.setStatus(PayForStatusEnum.pf0004.getCode());
				tradeResponse.setRespCode(chnPayForResponse.getRespCode());
				tradeResponse.setRespDesc(chnPayForResponse.getRespDesc());
				tradeResponse.setRetCode(chnPayForResponse.getRetCode());
				tradeResponse.setRetDesc(chnPayForResponse.getRetDesc());
			} else{
				log.info("查询到代付订单号{},系统异常,需再次查询确认结果",chnPayForResponse.getPayId());
				tradeResponse.setRespCode(chnPayForResponse.getRespCode());
				tradeResponse.setRespDesc(chnPayForResponse.getRespDesc());
	    		tradeResponse.setRetCode(chnPayForResponse.getRetCode());
				tradeResponse.setRetDesc(chnPayForResponse.getRetDesc());
			}
		}catch(TradeException e){
			log.error("["+platPayDetail.getPayId()+"]代付查询发生异常",e);
			tradeResponse.setPayId(platPayDetail.getPayId());
			tradeResponse.setStatus(PayForStatusEnum.pf0002.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespDesc(RespEnum.E99999.getDesc());
			return;
		}catch(Exception e){
			log.error("["+platPayDetail.getPayId()+"]代付查询发生异常",e);
			tradeResponse.setPayId(platPayDetail.getPayId());
			tradeResponse.setStatus(PayForStatusEnum.pf0002.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespDesc(RespEnum.E99999.getDesc());
			return;
		}
	}
	
	
	/**
	 * 查询代付状态
	 * @param tradeResponse
	 * @param platPayDetail
	 * @param serviceBeanId
	 * @param flag  判断是定时器请求   还是下游请求
	 */
	private void channelPayForQuery(PayForQueryResponse tradeResponse,PlatPayDetail platPayDetail,String serviceBeanId, String flag) {
		
		try{
			//6、发起退款请求  A、调用上游返回为代付成功，则更新代付表状态  B、调用上游接口返回为其他状态，则等待通知更新表状态
			//根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doPayForQuery", new Class[] {PlatPayDetail.class});

			//调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnPayForResponse chnPayForResponse =(ChnPayForResponse) method.invoke(clazz, new Object[] {platPayDetail});//同步返回预支付结果
			
		      
//		      ChnPayForResponse chnPayForResponse = new ChnPayForResponse();
//		      chnPayForResponse.setOrderId(platPayDetail.getMercOrderNo());
//		      chnPayForResponse.setMercNo(platPayDetail.getPayingMercNo());
//		      chnPayForResponse.setRetCode("0000");
//		      chnPayForResponse.setRetDesc("");
//		      chnPayForResponse.setRespCode("000000");
//		      chnPayForResponse.setRespDesc("");
//		      chnPayForResponse.setStatus("0000"); // 0000
//		      chnPayForResponse.setPayTradeSeq(StrUtil.getUUID()); // 交易号，上游返回的
//		      Thread.sleep(400);
			
			this.handlePayForQueryResult(tradeResponse,platPayDetail,chnPayForResponse, flag);
		}catch(TradeException e){
			log.error("["+platPayDetail.getPayId()+"]代付查询发生异常",e);
			tradeResponse.setPayId(platPayDetail.getPayId());
			tradeResponse.setStatus(PayForStatusEnum.pf0002.getCode());
			tradeResponse.setRespCode(RespEnum.S00000.getCode());
    	    tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
			return;
		}catch(Exception e){
			log.error("["+platPayDetail.getPayId()+"]代付查询发生异常",e);
			tradeResponse.setPayId(platPayDetail.getPayId());
			tradeResponse.setStatus(PayForStatusEnum.pf0002.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespDesc(RespEnum.E99999.getDesc());
			return;
		}
	}
	

	
	/**
	 * 代付結果處理
	 * @param platPayDetail
	 */
	private void handlePayForQueryResult(PayForQueryResponse tradeResponse,PlatPayDetail platPayDetail,ChnPayForResponse chnPayForResponse, String flag) {
		if(chnPayForResponse.getRespCode().equals(RespEnum.S00000.getCode())){//成功
			log.info("查询到代付交易成功，交易金额为{}", chnPayForResponse.getTradeAmt());
			this.successPayForQueryHandle(tradeResponse,platPayDetail,chnPayForResponse, flag);
		}else if(chnPayForResponse.getRespCode().equals(RespEnum.E00003.getCode())){//上游处理中状态
			tradeResponse.setPayId(platPayDetail.getPayId());
			tradeResponse.setStatus(PayForStatusEnum.pf0002.getCode());//处理中
			tradeResponse.setRespCode(RespEnum.S00000.getCode());
    	    tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
		}else{
			this.failPayForQueryHandle(tradeResponse,platPayDetail,chnPayForResponse, flag);
		}
	}

	/**
	 * 代付失败结果处理--事务控制，异常回滚
	 * @param platPayDetail
	 */
	@Transactional
	protected void failPayForQueryHandle(PayForQueryResponse payForQueryResponse,PlatPayDetail platPayDetail,ChnPayForResponse chnPayForResponse, String flag) {
		//修改代付状态为成功           //解冻代付交易金额与手续费金额
		if(StringUtils.isBlank(flag) || StringUtils.isNotNull(flag) && !flag.equals(TIMER_REQUEST_PAYFOR)) {
			platPayDetail.setStatus(PayForStatusEnum.pf0004.getCode());
			platPayDetail.setRetCode(chnPayForResponse.getRetCode());
			platPayDetail.setRetDesc(chnPayForResponse.getRetDesc());
			platPayDetail.setRespCode(chnPayForResponse.getRespCode());
			platPayDetail.setRespDesc(chnPayForResponse.getRespDesc());
			platPayDetail.setBankTradeSeq(chnPayForResponse.getPayTradeSeq());
			platPayDetail.setSettleTime(chnPayForResponse.getCompleteTime());
			platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
			
			//获取分布式锁
			RedisLock distributedLock = new RedisLock(redisTemplate, platPayDetail.getMercNo(), 50000,60000);
			boolean lockFlag;
			try {
				lockFlag = distributedLock.lock();
				log.info("获取锁标识值为：{}，获取分布式锁{}",lockFlag,lockFlag?"成功":"失败");
				if(!lockFlag){
					throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
				}
			
				// 根据账号类型及代付订单号查询商户虚拟账户表
				MerchantAccRel merchantAccRel = this.merchantAccRelMapper.selectByMercAcct(platPayDetail.getMercNo(), "00");
				
				// 根据代付订单号查询  支付渠道编号  渠道商户号  商户编号
				Map<String, String> keyMap = platPayDetailMapper.selectKeyByPrimaryKey(platPayDetail.getPayId());
				MerchantChannelBalKey key = new MerchantChannelBalKey();
				key.setMercNo(merchantAccRel.getMercNo());// 下游商户编号
				key.setChannelCode(keyMap.get("channelCode"));	// 支付渠道编号
				key.setPayingMercNo(keyMap.get("payingMercNo"));	// 渠道商户号
				// 根据商户编号、支付渠道编号、渠道商户号获取记录。
				MerchantChannelBal merchantChannelBal = this.merchantChannelBalMapper.selectByPrimaryKey(key);
				// 支付失败则 将原来虚户 和 对应渠道  中 冻结的资金还原
				// 虚户中 总金额 加 代付金额 + 手续费， 冻结金额 减 代付金额 + 手续费
				// 手续费
				BigDecimal feeBal = platPayDetail.getFeeValue();
				if(feeBal == null) {
					feeBal = new BigDecimal("0");
				}
				// 代付金额
				BigDecimal transAmt = platPayDetail.getTransAmt();
				// 要减去 或者 加上的金额
				BigDecimal subAmt = Arith.add(transAmt, feeBal);
				
				merchantAccRel.setAcctAvaiBal(Arith.add(merchantAccRel.getAcctAvaiBal(), subAmt));
				merchantAccRel.setFreezeBal(Arith.sub(merchantAccRel.getFreezeBal(), subAmt));
				merchantAccRel.setUpdateTime(new Date());
				// 更新商户虚拟账户表
				this.merchantAccRelMapper.updateBymercNoSelective(merchantAccRel);
			
				// 更新对应渠道表
				merchantChannelBal.setChnAvaiBal(Arith.add(merchantChannelBal.getChnAvaiBal(), subAmt));
				merchantChannelBal.setChnFreezeBal(Arith.sub(merchantChannelBal.getChnFreezeBal(), subAmt));
				this.merchantChannelBalMapper.updateByPrimaryKey(merchantChannelBal);
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error("获取分布式锁失败！");
				log.error("代付查询：["+platPayDetail.getPayId()+"]代付结果更新发生异常",e);
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("获取分布式锁失败！");
				log.error("代付查询：["+platPayDetail.getPayId()+"]代付结果更新发生异常",e);
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
			finally{
				log.error("释放分布式锁");
				distributedLock.unlock();
			}
		
			// 解冻
			this.unfreeze(platPayDetail.getRsfld2());
		}
		
		//设置返回值
		payForQueryResponse.setPayId(platPayDetail.getPayId());
		payForQueryResponse.setStatus(PayForStatusEnum.pf0004.getCode());
		payForQueryResponse.setRespCode(payForQueryResponse.getRespCode());
		payForQueryResponse.setRespDesc(payForQueryResponse.getRespDesc());
		
	}
	
	/**
	 * unfreeze:(解冻记录).  
	 * @author 
	 * 2018年2月28日上午10:55:38
	 *
	 * @param uuid
	 */
	private void unfreeze(String uuid){
		FreezeDetail freezeDetail = new FreezeDetail();
		freezeDetail.setId(uuid);
		freezeDetail.setFreezeStatus("01");
		freezeDetailMapper.updateByPrimaryKeySelective(freezeDetail);
	}
	

	/**
	 * 代付成功结果处理--事务控制异常回滚
	 * @param platPayDetail
	 */
	@Transactional
	protected void successPayForQueryHandle(PayForQueryResponse payForQueryResponse,PlatPayDetail platPayDetail,ChnPayForResponse chnPayForResponse, String flag) {
		//修改代付状态为成功     如果是下游请求的才进入这里
		if(StringUtils.isBlank(flag) || cn.itbeien.common.util.StringUtils.isNotNull(flag) && !flag.equals(TIMER_REQUEST_PAYFOR)) {
			platPayDetail.setStatus(PayForStatusEnum.pf0000.getCode());
			platPayDetail.setRetCode(chnPayForResponse.getRetCode());
			platPayDetail.setRetDesc(chnPayForResponse.getRetDesc());
			platPayDetail.setRespCode(chnPayForResponse.getRespCode());
			platPayDetail.setRespDesc(chnPayForResponse.getRespDesc());
			platPayDetail.setBankTradeSeq(chnPayForResponse.getBankPayForTradeSeq());
			platPayDetail.setSettleTime(chnPayForResponse.getCompleteTime());
			platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
			
			// 手续费
			BigDecimal feeBal = platPayDetail.getFeeValue();
			if(feeBal == null) {
				feeBal = new BigDecimal("0");
			}
			// 代付金额
			BigDecimal transAmt = platPayDetail.getTransAmt();
			// 要减去 或者 加上的金额
			BigDecimal subAmt = Arith.add(transAmt, feeBal);

			//获取同步锁
			RedisLock distributedLock = new RedisLock(redisTemplate, platPayDetail.getMercNo(), 50000,60000);
			boolean lockFlag;
			try {
				lockFlag = distributedLock.lock();
				log.info("获取锁标识值为：{}，获取分布式锁{}",lockFlag,lockFlag?"成功":"失败");
				if(!lockFlag){
					throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
				}
				// 根据账号类型及代付订单号查询商户虚拟账户表
				MerchantAccRel merchantAccRel = this.merchantAccRelMapper.selectByMercAcct(platPayDetail.getMercNo(), "00");
				
				// 根据代付订单号查询  支付渠道编号  渠道商户号  商户编号
				Map<String, String> keyMap = platPayDetailMapper.selectKeyByPrimaryKey(platPayDetail.getPayId());
				MerchantChannelBalKey key = new MerchantChannelBalKey();
				key.setMercNo(merchantAccRel.getMercNo());// 下游商户编号
				key.setChannelCode(keyMap.get("channelCode"));	// 支付渠道编号
				key.setPayingMercNo(keyMap.get("payingMercNo"));	// 渠道商户号
				// 根据商户编号、支付渠道编号、渠道商户号获取记录。
				MerchantChannelBal merchantChannelBal = this.merchantChannelBalMapper.selectByPrimaryKey(key);
				
				// 总余额 
				merchantAccRel.setAcctBal(Arith.sub(merchantAccRel.getAcctBal(), subAmt));
				// 冻结金额
				merchantAccRel.setFreezeBal(Arith.sub(merchantAccRel.getFreezeBal(), subAmt));
				// 出账总金额
				merchantAccRel.setOutAmt(Arith.add(merchantAccRel.getOutAmt(), subAmt));
				// 手续费
				merchantAccRel.setFeeBal(Arith.add(merchantAccRel.getFeeBal(), feeBal));
				merchantAccRel.setUpdateTime(new Date());
				//更新商户虚拟账户表
				this.merchantAccRelMapper.updateBymercNoSelective(merchantAccRel);	
				
				// 总余额 
				merchantChannelBal.setChnBal(Arith.sub(merchantChannelBal.getChnBal(), subAmt));
				// 冻结金额
				merchantChannelBal.setChnFreezeBal(Arith.sub(merchantChannelBal.getChnFreezeBal(), subAmt));
				// 出账总金额
				merchantChannelBal.setChnOutAmt(Arith.add(merchantChannelBal.getChnOutAmt(), subAmt));
				// 手续费
				merchantChannelBal.setChnFeeBal(Arith.add(merchantChannelBal.getChnFeeBal(), feeBal));
				this.merchantChannelBalMapper.updateByPrimaryKey(merchantChannelBal);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("获取分布式锁失败！");
				log.error("代付查询：["+platPayDetail.getPayId()+"]代付结果更新发生异常",e);
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			} finally{
				log.error("释放分布式锁");
				distributedLock.unlock();
			}
		
			// 解冻
			this.unfreeze(platPayDetail.getRsfld2());
		}
		
		//设置返回值
		payForQueryResponse.setPayId(platPayDetail.getPayId());
		payForQueryResponse.setStatus(PayForStatusEnum.pf0000.getCode());
		payForQueryResponse.setRespCode(chnPayForResponse.getRespCode());
		payForQueryResponse.setRespDesc(chnPayForResponse.getRespDesc());
		payForQueryResponse.setTradeTime(DateUtils.formatTime(platPayDetail.getTradeTime()));
		if(chnPayForResponse.getCompleteTime() != null){
			payForQueryResponse.setTradeEndTime(DateUtils.formatTime(chnPayForResponse.getCompleteTime()));
		}
		payForQueryResponse.setMercOrderNo(platPayDetail.getMercOrderNo());
		payForQueryResponse.setTradeAmt(String.valueOf(platPayDetail.getFeeValue().setScale(2, BigDecimal.ROUND_HALF_UP)));
		payForQueryResponse.setPayId(platPayDetail.getPayId());
		payForQueryResponse.setToAcctNo(platPayDetail.getToAcctNo());
		payForQueryResponse.setToAcctName(platPayDetail.getToAcctName());
		payForQueryResponse.setToBankNo(platPayDetail.getToBankNo());
		payForQueryResponse.setToBankName(platPayDetail.getToBankName());
		payForQueryResponse.setBankPayForTradeSeq(chnPayForResponse.getBankPayForTradeSeq());//为定时回调接口返回上游流水号（如高汇通）
		payForQueryResponse.setCity(platPayDetail.getRsfld4());
		payForQueryResponse.setProvince(platPayDetail.getRsfld5());
		payForQueryResponse.setToAcctType(platPayDetail.getAcctType());
		payForQueryResponse.setFeeType(platPayDetail.getCurrency());
		payForQueryResponse.setFeeValue(String.valueOf(platPayDetail.getFeeValue().setScale(2, BigDecimal.ROUND_HALF_UP)));
		
	}
	
	
	/**
	 * TODO 余额查询
	 * @param tradeResponse
	 * @param refundOrder
	 * @param serviceBeanId
	 */
	private void channelAvalBalQuery(RefundQueryResponse tradeResponse,RefundQueryRequest tradeRequest,TradeRefund refundOrder,TradeRefundSeq tradeRefundSeq,String serviceBeanId) {
		try{
			//6、发起退款请求
			//根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doAvalBalQuery", new Class[] {TradeRefund.class});

			//调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnRefundQueryResponse chnRefundQueryResponse =(ChnRefundQueryResponse) method.invoke(clazz, new Object[] {refundOrder});//同步返回预支付结果
			
			this.handleRefundQueryResult(tradeResponse,refundOrder,tradeRefundSeq,chnRefundQueryResponse);
		}catch(TradeException e){
			log.error("["+refundOrder.getRefundOrderId()+"]退款查询发生异常",e);
			tradeResponse.setRefundOrderId(refundOrder.getRefundOrderId());
			tradeResponse.setRefundStatus(RefundStatus.r0001.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespDesc(RespEnum.E99999.getDesc());
			return;
		}catch(Exception e){
			log.error("["+refundOrder.getRefundOrderId()+"]退款查询发生异常",e);
			tradeResponse.setRefundOrderId(refundOrder.getRefundOrderId());
			tradeResponse.setRefundStatus(RefundStatus.r0001.getCode());
			tradeResponse.setRespCode(RespEnum.E99999.getCode());
    	    tradeResponse.setRespDesc(RespEnum.E99999.getDesc());
			return;
		}
	}


	
}
