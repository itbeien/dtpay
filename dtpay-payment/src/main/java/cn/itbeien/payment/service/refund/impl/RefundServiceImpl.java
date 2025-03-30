package cn.itbeien.payment.service.refund.impl;

import cn.itbeien.common.entity.merchant.MerchantChannelBal;
import cn.itbeien.common.entity.merchant.MerchantChannelBalExt;
import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.entity.trade.TradeRefund;
import cn.itbeien.common.entity.trade.TradeRefundSeq;
import cn.itbeien.common.enums.PayStatus;
import cn.itbeien.common.enums.RefundStatus;
import cn.itbeien.common.mapper.merchant.MerchantChannelBalMapper;
import cn.itbeien.common.util.DateUtils;
import cn.itbeien.common.util.SpringUtils;
import cn.itbeien.common.util.uuid.UUID;
import cn.itbeien.payment.channel.vo.ChannelVo;
import cn.itbeien.payment.channel.vo.ChnRefundResponse;
import cn.itbeien.payment.core.vo.request.RefundRequest;
import cn.itbeien.payment.core.vo.response.RefundResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.mapper.trade.TradeOrderMapper;
import cn.itbeien.payment.mapper.trade.TradeOrderSeqMapper;
import cn.itbeien.payment.mapper.trade.TradeRefundMapper;
import cn.itbeien.payment.mapper.trade.TradeRefundSeqMapper;
import cn.itbeien.payment.service.genseq.SeqService;
import cn.itbeien.payment.service.refund.IRefundService;
import cn.itbeien.payment.service.route.IRouteService;
import cn.itbeien.payment.service.route.model.RouteResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class RefundServiceImpl implements IRefundService {

	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	
	@Autowired
	private TradeOrderSeqMapper tradeOrderSeqMapper;
  
	@Autowired
	private TradeRefundMapper tradeRefundMapper;
  
	@Autowired
	private TradeRefundSeqMapper tradeRefundSeqMapper;
	
	@Autowired
	private MerchantChannelBalMapper merchantChannelBalMapper;
	
	@Autowired
	private IRouteService routeServiceImpl;
	
	@Autowired
	private SeqService seqService;
	
	@Override
	public void refund(RefundRequest refundRequest, RefundResponse refundResponse) {

		  //判断商户订单是否重复
		  Map<String,Object> map = new HashMap<>();
		  map.put("mercNo", refundRequest.getMercNo());
		  map.put("mercRefundNo", refundRequest.getMercRefundNo());
		  TradeRefund  tradeRefund = tradeRefundMapper.selectByMap(map);
		  if(tradeRefund!=null){
			  throw new TradeException(RespEnum.E00014.getCode(), RespEnum.E00014.getDesc());
		  }
		  
		  //判断支付订单是否成功并且是否可退款
		  TradeOrder tradeOrder = tradeOrderMapper.selectByPrimaryKey(refundRequest.getOrgOrderId());
		  
		  if(PayStatus.p0000.getCode().equals(tradeOrder.getPayStatus())){
			  if(!"n".equals(tradeOrder.getIsRefund())){
				  throw new TradeException(RespEnum.E00013.getCode(), RespEnum.E00013.getDesc());
			  }
		  }else{
			  throw new TradeException(RespEnum.E00015.getCode(), RespEnum.E00015.getDesc());
		  }
		  
		  TradeOrderSeq tradeOrderSeq = tradeOrderSeqMapper.selectOrderSuccessByOrderId(tradeOrder.getOrderId());
		  if(tradeOrderSeq==null){
			  throw new TradeException(RespEnum.E00015.getCode(), RespEnum.E00015.getDesc());
		  }
		  
		  //汇总退款订单总金额是否大于订单金额
		  TradeRefund tradeRefundReq = new TradeRefund();
		  tradeRefundReq.setOrgOrderId(refundRequest.getOrgOrderId());
		  tradeRefundReq.setOrgMercOrderNo(refundRequest.getOrgMercOrderNo());
		  BigDecimal  sumAmt = tradeRefundMapper.selectRefundSumAmt(tradeRefundReq);
		  sumAmt = sumAmt==null?new BigDecimal(0.00):sumAmt;
		  if(sumAmt.add(new BigDecimal(refundRequest.getRefundAmt())).compareTo(tradeOrder.getOrderAmount())>0){
			  throw new TradeException(RespEnum.E00016.getCode(), RespEnum.E00016.getDesc());
		  }
		  
		  //路由到对应退款通道
		  RouteResultModel routeResultModel = routeServiceImpl.routeByCondition(refundRequest.getMercNo(), tradeOrder.getChannelCode(),
				  tradeOrder.getPaywayCode(), tradeOrder.getSceneCode());
		  
//		  IChannelService channelService = routeResultModel.getChannelService();
		  //查询对应通道可用金额是否大于退款金额
		  MerchantChannelBal merchantChannelBal  = new MerchantChannelBal();
		  merchantChannelBal.setMercNo(tradeOrderSeq.getMercNo());
		  merchantChannelBal.setChannelCode(routeResultModel.getChannelCode());
		  merchantChannelBal.setPayingMercNo(tradeOrderSeq.getPayingMercNo());;
		  List<MerchantChannelBalExt>  merchantChannelBalExts = merchantChannelBalMapper.selectNoPayForMerchantChannelBalExtList(merchantChannelBal);
		  if(merchantChannelBalExts!=null&& !merchantChannelBalExts.isEmpty()){
			  MerchantChannelBalExt chooseChn = merchantChannelBalExts.get(0);
			  if(new BigDecimal(refundRequest.getRefundAmt()).compareTo(chooseChn.getChnAvaiBal())>0){
				  throw new TradeException(RespEnum.E00022.getCode(), RespEnum.E00022.getDesc());
			  }
		  }else{
			  throw new TradeException(RespEnum.E00024.getCode(), RespEnum.E00024.getDesc());
		  }
		 
		  //记录退款订单信息
		  TradeRefund tradeRefundIns = new TradeRefund();
		  String refundOrderId =  "R"+ seqService.getOrderId();
		  tradeRefundIns.setRefundOrderId(refundOrderId);
		  tradeRefundIns.setCharset(refundRequest.getCharset());
		  tradeRefundIns.setLocale(refundRequest.getLocale());
		  tradeRefundIns.setSignType(refundRequest.getSignType());
		  tradeRefundIns.setVersion(refundRequest.getVersion());
		  tradeRefundIns.setMercNo(refundRequest.getMercNo());
		  tradeRefundIns.setCancelReason("");
		  tradeRefundIns.setMercRefundNo(refundRequest.getMercRefundNo());
		  tradeRefundIns.setOrgMercOrderNo(refundRequest.getOrgMercOrderNo());
		  tradeRefundIns.setOrgOrderId(refundRequest.getOrgOrderId());
		  tradeRefundIns.setChannelCode(tradeOrderSeq.getChannelCode());  
		  tradeRefundIns.setPaywayCode(tradeOrderSeq.getPaywayCode());
		  tradeRefundIns.setSceneCode(tradeOrderSeq.getSceneCode());
		  tradeRefundIns.setRedundStatus(RefundStatus.r0001.getCode());
		  tradeRefundIns.setTradeAmount(new BigDecimal(refundRequest.getRefundAmt()));
		  tradeRefundIns.setTradeTime(new Date());
		  tradeRefundMapper.insert(tradeRefundIns);
		  
		  //记录退款订单流水
		  TradeRefundSeq tradeRefundSeq = new TradeRefundSeq();
		  tradeRefundSeq.setChannelCode(routeResultModel.getChannelCode());
		  tradeRefundSeq.setMercNo(refundRequest.getMercNo());
		  tradeRefundSeq.setMercRefundNo(refundRequest.getMercRefundNo());
		  tradeRefundSeq.setOrgMercOrderNo(refundRequest.getOrgMercOrderNo());
		  tradeRefundSeq.setOrgOrderId(refundRequest.getOrgOrderId());
		  tradeRefundSeq.setCreateTime(new Date());
		  tradeRefundSeq.setOrgProductName(tradeOrder.getSubject());
		  tradeRefundSeq.setOrgTradeTime(tradeOrder.getOrderTime());
		  tradeRefundSeq.setMercName(tradeOrderSeq.getPayingMercName());
		  tradeRefundSeq.setConnectSwitch(tradeOrderSeq.getConnectSwitch());
		  tradeRefundSeq.setPayingMercNo(tradeOrderSeq.getPayingMercNo());
		  tradeRefundSeq.setPayingMercName(tradeOrderSeq.getPayingMercName());
		  tradeRefundSeq.setRefundAmount(new BigDecimal(refundRequest.getRefundAmt()));
		  tradeRefundSeq.setRefundOrderId(tradeRefundIns.getRefundOrderId());
		  tradeRefundSeq.setRefundStatus(RefundStatus.r0001.getCode());
		
		  //订单号规则
//		  String  seqNo= SeqUtil.getOrderId(mercAndCertModel.getPayingMercCode(),paymentInterfaceResponse.getChannelCode());
		  String  seqNo = UUID.getUUID();
		  tradeRefundSeq.setRefundTradeSeq(seqNo);
		  tradeRefundSeqMapper.insertSelective(tradeRefundSeq);
		  //将订单置为正在退款中
		  int count = tradeOrderMapper.updateIsRefund(tradeOrder.getOrderId());
		  if(count != 1){
			  throw new TradeException(RespEnum.E00017.getCode(), RespEnum.E00017.getDesc());
		  }
		
		  tradeRefundSeq.setTradeAmt(tradeOrder.getOrderAmount());
		  tradeRefundSeq.setNoticeStr(refundRequest.getNoticeStr());
		  this.channelRefund(refundResponse,tradeOrder,tradeOrderSeq,tradeRefundIns,tradeRefundSeq,routeResultModel.getServiceBeanId());
		  //接收退款结果并返回,交易结束
		  
	}
	
	/**
	 * 渠道退款
	 * @param refundResponse
	 * @param order
	 * @param orderSeq
	 * @param refundOrder
	 * @param refundOrderSeq
	 */
	private void channelRefund(RefundResponse refundResponse, TradeOrder order, TradeOrderSeq orderSeq, TradeRefund refundOrder, TradeRefundSeq refundOrderSeq, String serviceBeanId) {
		// TODO Auto-generated method stub
		try{
			//6、发起退款请求
			//step2根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doRefund", new Class[] {TradeRefundSeq.class});

			//step3调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnRefundResponse chnRefundResponse =(ChnRefundResponse) method.invoke(clazz, new Object[] {refundOrderSeq});//同步返回预支付结果
			
			this.handleRefundResult(refundResponse, order, refundOrder, refundOrderSeq, chnRefundResponse.getBankRefundTradeSeq(), chnRefundResponse.getRefundCompleteTime(), chnRefundResponse);
		}catch(TradeException e){
			log.error("["+refundResponse.getRefundOrderId()+"]退款发生异常",e);
			this.refundExceptionHandle(refundResponse,refundOrder,refundOrderSeq,e.getErrorCode(),e.getErrorMsg());
			return;
		}catch(Exception e){
			log.error("["+refundResponse.getRefundOrderId()+"]退款发生异常",e);
			this.refundExceptionHandle(refundResponse,refundOrder,refundOrderSeq, RespEnum.E99999.getCode(),RespEnum.E99999.getDesc());
			return;
		}
	}
	
	
	/**
	 * 退款结果处理
	 * @param refundResponse
	 * @param order
	 * @param refundOrder
	 * @param refundOrderSeq
	 * @param bankRefundSeqId
	 * @param bankRefundTime
	 */
	private <T extends ChannelVo>  void handleRefundResult(RefundResponse refundResponse,TradeOrder order, TradeRefund refundOrder,
			TradeRefundSeq refundOrderSeq, String bankRefundSeqId,Date bankRefundTime, T bankResponse) {
		// TODO Auto-generated method stub
		if(bankResponse.getRespCode().equals(RespEnum.S00000.getCode())){//成功
			
			this.successHandle(refundResponse,order,refundOrder,refundOrderSeq,bankRefundSeqId,bankRefundTime,bankResponse);
//		}else if(UnKnownStatusUtil.isUnKnownStatus(bankResponse.getRetCode())){//未知
//			
//			this.unknownHandle(refundResponse,refundOrder,refundOrderSeq,bankResponse);
		}else{//失敗
			
			this.failHandle(refundResponse,order,refundOrder,refundOrderSeq,bankRefundSeqId,bankRefundTime,bankResponse);
		}
	}

	/**
	 * 
	 * @param refundResponse
	 * @param refundOrder
	 * @param refundOrderSeq
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void unknownHandle(RefundResponse refundResponse, TradeRefund refundOrder,
			TradeRefundSeq refundOrderSeq, T bankResponse) {
		//修改订单状态为超时
		refundOrderSeq.setRefundStatus(RefundStatus.r0003.getCode());
		refundOrderSeq.setRetCode(bankResponse.getRetCode());
		refundOrderSeq.setRetDesc(bankResponse.getRetDesc());
		refundOrderSeq.setRespCode(bankResponse.getRespCode());
		refundOrderSeq.setRespDesc(bankResponse.getRespDesc());
		tradeRefundSeqMapper.updateByPrimaryKeySelective(refundOrderSeq);
		
		refundOrder.setRedundStatus(RefundStatus.r0003.getCode());
		tradeRefundMapper.updateByPrimaryKeySelective(refundOrder);
		
		//设置返回值
		refundResponse.setRefundOrderId(refundOrder.getRefundOrderId());
		refundResponse.setRefundStatus(RefundStatus.r0003.getCode());
		refundResponse.setRespCode(bankResponse.getRespCode());
		refundResponse.setRespDesc(bankResponse.getRespDesc());
	}

	/**
	 * 退款失败结果处理--事务控制，异常回滚
	 * @param refundResponse
	 * @param order
	 * @param refundOrder
	 * @param refundOrderSeq
	 * @param bankRefundSeqId
	 * @param bankRefundTime
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void failHandle(RefundResponse refundResponse,TradeOrder order, TradeRefund refundOrder,
			TradeRefundSeq refundOrderSeq,String bankRefundSeqId,Date bankRefundTime, T bankResponse) {
		//修改订单状态为失败
		refundOrderSeq.setRefundStatus(RefundStatus.r0004.getCode());
		refundOrderSeq.setRetCode(bankResponse.getRetCode());
		refundOrderSeq.setRetDesc(bankResponse.getRetDesc());
		refundOrderSeq.setRespCode(bankResponse.getRespCode());
		refundOrderSeq.setRespDesc(bankResponse.getRespDesc());
		refundOrderSeq.setBankRefundTradeSeq(bankRefundSeqId);
		refundOrderSeq.setRefundCompleteTime(bankRefundTime);
		tradeRefundSeqMapper.updateByPrimaryKeySelective(refundOrderSeq);
		
		refundOrder.setRedundStatus(RefundStatus.r0004.getCode());
		tradeRefundMapper.updateByPrimaryKeySelective(refundOrder);
		
		order.setIsRefund("n");
		tradeOrderMapper.updateByPrimaryKeySelective(order);
		//设置返回值
		refundResponse.setRefundOrderId(refundOrder.getRefundOrderId());
		refundResponse.setRefundStatus(RefundStatus.r0004.getCode());
		refundResponse.setRespCode(bankResponse.getRespCode());
		refundResponse.setRespDesc(bankResponse.getRespDesc());
		if(bankRefundTime != null){
			refundResponse.setRefundEndTime(DateUtils.formatTime(bankRefundTime));
		}
		
	}

	/**
	 * 退款成功结果处理--事务控制异常回滚
	 * @param refundResponse
	 * @param order
	 * @param refundOrder
	 * @param refundOrderSeq
	 * @param bankRefundSeqId
	 * @param bankRefundTime
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void successHandle(RefundResponse refundResponse,TradeOrder order, TradeRefund refundOrder,
			TradeRefundSeq refundOrderSeq,String bankRefundSeqId,Date bankRefundTime, T bankResponse) {
		//修改订单状态为成功
		refundOrderSeq.setRefundStatus(RefundStatus.r0000.getCode());
		refundOrderSeq.setRetCode(bankResponse.getRetCode());
		refundOrderSeq.setRetDesc(bankResponse.getRetDesc());
		refundOrderSeq.setRespCode(bankResponse.getRespCode());
		refundOrderSeq.setRespDesc(bankResponse.getRespDesc());
		refundOrderSeq.setBankRefundTradeSeq(bankRefundSeqId);
		if(bankRefundTime != null){
			refundOrderSeq.setRefundCompleteTime(bankRefundTime);
		}else{
			refundOrderSeq.setRefundCompleteTime(new Date());
		}
		tradeRefundSeqMapper.updateByPrimaryKeySelective(refundOrderSeq);
		
		refundOrder.setRedundStatus(RefundStatus.r0000.getCode());
		tradeRefundMapper.updateByPrimaryKeySelective(refundOrder);
		
		//设置返回值
		refundResponse.setRefundOrderId(refundOrder.getRefundOrderId());
		refundResponse.setRefundStatus(RefundStatus.r0000.getCode());
		refundResponse.setRespCode(bankResponse.getRespCode());
		refundResponse.setRespDesc(bankResponse.getRespDesc());
		if(bankRefundTime != null){
			refundResponse.setRefundEndTime(DateUtils.formatTime(bankRefundTime));
		}
		
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
	}

	/**
	 * 退款时发生异常---交易时异常做未知状态处理
	 * 1、修改订单及流水状态，并更新失败原因
	 * 2、封装返回对象
	 * 3、添加异步查询队列到redis定时查询银行返回结果
	 * @param refundResponse
	 * @param refundOrder
	 * @param refundOrderSeq
	 * @param errorCode
	 * @param errorMsg
	 */
	private void refundExceptionHandle(RefundResponse refundResponse,
									   TradeRefund refundOrder, TradeRefundSeq refundOrderSeq, String errorCode, String errorMsg) {
		ChannelVo channelVo = new ChannelVo();
		channelVo.setMercNo(refundOrder.getMercNo());
		channelVo.setOrderId(refundOrder.getRefundOrderId());
		channelVo.setRespCode(errorCode);
		channelVo.setRespDesc(errorMsg);
		this.unknownHandle(refundResponse, refundOrder,refundOrderSeq,  channelVo);
	}
}
