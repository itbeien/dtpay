package cn.itbeien.payment.api;

import cn.itbeien.payment.core.vo.TradeRequest;
import cn.itbeien.payment.core.vo.TradeResponse;
import cn.itbeien.payment.core.vo.request.*;
import cn.itbeien.payment.core.vo.response.*;
import cn.itbeien.payment.service.balance.impl.BalanceQueryServiceImpl;
import cn.itbeien.payment.enums.InterfaceCodeEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.service.payfor.IPayForService;
import cn.itbeien.payment.service.refund.IRefundService;
import cn.itbeien.payment.service.tradequery.ITradeQueryService;
import cn.itbeien.payment.service.verify.MerchantVerifyService;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@Scope(value="prototype")
@Slf4j
public class TradeController{

	  @Autowired
	  private MerchantVerifyService merchantVerifyService;
	  
	  @Autowired
	  private IRefundService refundServiceImpl;
	  
	  @Autowired
	  private IPayForService payForServiceImpl;
	  
	  @Autowired
	  private ITradeQueryService tradeQueryServiceImpl;
	  
	  @Autowired
	  private BalanceQueryServiceImpl balanceQueryService;
	  
	  @RequestMapping(value = "/gateway/trade.do", method = RequestMethod.POST)
	  public String trade(@RequestParam String jsonParam) {
		  log.info("下游请求参数：{}",jsonParam);
		  String jsonRes = "";
		  //接收报文
		  JSONObject jsonObject = JSONObject.parseObject(jsonParam);
		  
		  TradeResponse tradeResponse = null;
		  try {
			  
			  //2、参数封装到bean中
			  TradeRequest tradeRequest = merchantVerifyService.getTradeRequest(jsonObject);
			  
			  //初始化返回对象
			  tradeResponse = this.initResponse(tradeRequest);
			  
			  //3、对商户报文验证，商户验签
			  tradeRequest = merchantVerifyService.validateTradeData(jsonObject);
				
			  //4、核心业务处理
			  this.tradeHandle(tradeRequest,tradeResponse);
				
			  //5、对返回报文加签
			  jsonRes = merchantVerifyService.generateTradeResponseAsJson(tradeRequest,tradeResponse);
		 } catch(TradeException e){//业务异常
			log.error("TradeException交易失败：{0}",e);
			if(tradeResponse != null){
				tradeResponse.setRespCode(e.getErrorCode());
				tradeResponse.setRespDesc(e.getErrorMsg());
				jsonRes = merchantVerifyService.generateTradeResponseAsJson(tradeResponse);
			}else{
				jsonRes = merchantVerifyService.generateRuntimeExceptionResponseAsJson(e,jsonObject);
			}
			
		}catch (Exception e) {//系统异常
			log.error("系统异常：{0}",e);
			jsonRes = merchantVerifyService.generateRuntimeExceptionResponseAsJson(e,jsonObject);
		}
		return jsonRes;
	}
	  
	  
  /**
	 * 初始化响应参数，将参数公共部分从request同步到response
	 * @param request
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 */
	private TradeResponse initResponse(TradeRequest request) throws IllegalAccessException, InvocationTargetException {
		TradeResponse tradeResponse = null;
		if(InterfaceCodeEnum.pay.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new PayResponse();
			PayRequest tradeRequest = (PayRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.payQuery.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new PayQueryResponse();
			PayQueryRequest tradeRequest = (PayQueryRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.refund.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new RefundResponse();
			RefundRequest tradeRequest = (RefundRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.refundQuery.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new RefundQueryResponse();
			RefundQueryRequest tradeRequest = (RefundQueryRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.payFor.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new PayForResponse();
			PayForRequest tradeRequest = (PayForRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.payForWithAppr.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new PayForResponse();
			PayForRequest tradeRequest = (PayForRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.batchPayFor.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new BatchPayForResponse();
			BatchPayForRequest tradeRequest = (BatchPayForRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.payForQuery.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new PayForQueryResponse();
			PayForQueryRequest tradeRequest = (PayForQueryRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}else if(InterfaceCodeEnum.balanceQuery.getCode().equals(request.getInterfaceCode())){
			tradeResponse = new AvailBalQueryResponse();
			AvailBalQueryRequest tradeRequest = (AvailBalQueryRequest) request;
			BeanUtils.copyProperties(tradeRequest, tradeResponse);
		}
		return tradeResponse;
	}

	/**
	 * 后台类交易统一处理
	 * @param tradeRequest
	 * @param tradeResponse
	 */
	private void tradeHandle(TradeRequest tradeRequest,
			TradeResponse tradeResponse) {
	    if(InterfaceCodeEnum.refund.getCode().equals(tradeRequest.getInterfaceCode())){
			//退款
			refundServiceImpl.refund((RefundRequest)tradeRequest, (RefundResponse)tradeResponse);
			
		}else if(InterfaceCodeEnum.refundQuery.getCode().equals(tradeRequest.getInterfaceCode())){
			//退款查询
			tradeQueryServiceImpl.refundQuery((RefundQueryRequest)tradeRequest, (RefundQueryResponse)tradeResponse);
		
		}else if(InterfaceCodeEnum.payFor.getCode().equals(tradeRequest.getInterfaceCode())){
			//代付
			payForServiceImpl.payFor((PayForRequest)tradeRequest, (PayForResponse)tradeResponse);
			
		}else if(InterfaceCodeEnum.payForWithAppr.getCode().equals(tradeRequest.getInterfaceCode())){
			//代付审批
			payForServiceImpl.payForWithAppr((PayForRequest)tradeRequest, (PayForResponse)tradeResponse);
			
		}else if(InterfaceCodeEnum.batchPayFor.getCode().equals(tradeRequest.getInterfaceCode())){
			//批量代付
			payForServiceImpl.batchPayFor((BatchPayForRequest)tradeRequest, (BatchPayForResponse)tradeResponse);
			
		}else if(InterfaceCodeEnum.payForQuery.getCode().equals(tradeRequest.getInterfaceCode())){
			//代付查询
			tradeQueryServiceImpl.payForQuery((PayForQueryRequest)tradeRequest, (PayForQueryResponse)tradeResponse);
			
		}else if(InterfaceCodeEnum.balanceQuery.getCode().equals(tradeRequest.getInterfaceCode())){
			//余额查询
			balanceQueryService.balanceQuery((AvailBalQueryRequest)tradeRequest, (AvailBalQueryResponse)tradeResponse);
		}
	}
}
