package cn.itbeien.payment.service.verify.impl;

import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.EncryptionField;
import cn.itbeien.common.util.StringUtils;
import cn.itbeien.common.util.sign.MD5Signature;
import cn.itbeien.common.util.sign.RSASignature;
import cn.itbeien.common.util.sign.RSAUtils;
import cn.itbeien.common.util.sign.SignUtils;
import cn.itbeien.payment.core.vo.TradeRequest;
import cn.itbeien.payment.core.vo.TradeResponse;
import cn.itbeien.payment.core.vo.request.*;
import cn.itbeien.payment.core.vo.response.*;
import cn.itbeien.payment.enums.InterfaceCodeEnum;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.exception.fail.InvalidParamException;
import cn.itbeien.payment.exception.fail.SystemException;
import cn.itbeien.payment.service.risk.RiskManageService;
import cn.itbeien.payment.service.verify.MerchantVerifyService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 商户接入基本验证类
 * 只要验证不通过，抛异常
 * Copyright© 2025 itbeien
 */
@Service
@Slf4j
public class DefaultMerchantVerifyServiceImpl implements MerchantVerifyService {
	
	@Autowired
	private RedisCache redisCacheUtils;
	
	private static PrivateKey GATEWAY_PRIVATE_KEY = null;
	
	private static final String TIMER_REQUEST_PAYFOR = "timerPayFor";  //定时器请求代付查询接口
	
    @Autowired
    private RiskManageService riskManageService;

	@Override
	public <T extends TradeRequest> T getTradeRequest(Map<String, Object> params) {
		JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(params));
		T tradeRequest = getTradeRequest(json);
		
		return tradeRequest;
	}

	@Override
	public <T extends TradeRequest> T getTradeRequest(JSONObject jsonObj){
		if(InterfaceCodeEnum.pay.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起支付请求，交易码{}", jsonObj.get("interfaceCode"));
			PayRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), PayRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.payQuery.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起支付订单查询请求，交易码{}", jsonObj.get("interfaceCode"));
			PayQueryRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), PayQueryRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.refund.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起退款请求，交易码{}", jsonObj.get("interfaceCode"));
			RefundRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), RefundRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.refundQuery.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起退款订单查询请求，交易码{}", jsonObj.get("interfaceCode"));
			RefundQueryRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), RefundQueryRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.payFor.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起代付请求，交易码{}", jsonObj.get("interfaceCode"));
			PayForRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), PayForRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.payForWithAppr.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起审批后代付请求，交易码{}", jsonObj.get("interfaceCode"));
			PayForRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), PayForRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.batchPayFor.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起批量代付请求，交易码{}", jsonObj.get("interfaceCode"));
			BatchPayForRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), BatchPayForRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.payForQuery.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起代付查询请求，交易码{}", jsonObj.get("interfaceCode"));
			PayForQueryRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), PayForQueryRequest.class);
			return (T) tradeRequest;
		}else if(InterfaceCodeEnum.balanceQuery.getCode().equals(jsonObj.get("interfaceCode"))){
			log.info("发起余额查询请求，交易码{}", jsonObj.get("interfaceCode"));
			AvailBalQueryRequest tradeRequest = JSONObject.parseObject(jsonObj.toJSONString(), AvailBalQueryRequest.class);
			return (T) tradeRequest;
		}else{
			log.info("交易码【"+jsonObj.get("interfaceCode")+"】,不匹配");
			throw new InvalidParamException(RespEnum.E00006.getCode(),RespEnum.E00006.getDesc());
		}
	}
	
	@Override
	public <T extends TradeRequest> T validateTradeData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return (T) this.validateTradeData((Map)jsonObj);
	}
	

	@Override
	public <T extends TradeRequest> T validateTradeData(Map<String, Object> params) {
		log.info("报文验签:["+params+"]");
		//1.特殊字符过滤
//		this.filterSpecChar(params);
		//2.公共请求参数 校验
		this.validateCommonparameters(params);

		//3.根据interfaceCode转化为对应的实体类，做数据校验
		T tradeRequest =  this.getTradeRequest(params);
		//tradeRequest.validata();
		//4.验签
		this.verifySign(params);
		log.info("验签后:["+params+"]");
		//5.解密
//		this.unEncryptFields(params);
//		RxpayLogger.info("解密后:["+params+"]");
		
		//校验商户接口权限
		riskManageService.validateInterfaceCode(params);
		
		return tradeRequest;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String generateTradeResponseAsJson(TradeRequest tradeRequest, TradeResponse tradeResponse)
			throws TradeException {
		Map<String, Object> map = null;
		if(InterfaceCodeEnum.pay.getCode().equals(tradeResponse.getInterfaceCode())){
			PayResponse payResponse = (PayResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payResponse,payResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			PayQueryResponse payQueryResponse = (PayQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payQueryResponse,payQueryResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.refund.getCode().equals(tradeResponse.getInterfaceCode())){
			RefundResponse refundResponse = (RefundResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(refundResponse,refundResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(refundResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.refundQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			RefundQueryResponse refundQueryResponse = (RefundQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(refundQueryResponse,refundQueryResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(refundQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payFor.getCode().equals(tradeResponse.getInterfaceCode())){
			PayForResponse payForResponse = (PayForResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payForResponse,payForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payForResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payForWithAppr.getCode().equals(tradeResponse.getInterfaceCode())){
			PayForResponse payForResponse = (PayForResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payForResponse,payForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payForResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payForQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			PayForQueryResponse payForQueryResponse = (PayForQueryResponse)tradeResponse;
			PayForQueryRequest  payForQueryRequest = (PayForQueryRequest)tradeRequest;
			
			//1、报文敏感字段加密
			encryptSomeFields(payForQueryResponse,payForQueryResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payForQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
			
			/**
			 * 除定时器查询代付结果外，返回下游结果时，删除上游流水号
			 */
			if(!TIMER_REQUEST_PAYFOR.equals(payForQueryRequest.getFlag())){
				map.remove("bankPayForTradeSeq");
				map.remove("retCode");
				map.remove("retDesc");
			}
		}else if(InterfaceCodeEnum.batchPayFor.getCode().equals(tradeResponse.getInterfaceCode())){
			BatchPayForResponse batchPayForResponse = (BatchPayForResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payForResponse,payForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(batchPayForResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.balanceQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			AvailBalQueryResponse availBalQueryResponse = (AvailBalQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payForResponse,payForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(availBalQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else{
			throw new InvalidParamException(RespEnum.E00006.getCode(),RespEnum.E00006.getDesc());
		}
		
		//2、报文加签
		String jsonStr = genMessageWithSignature(map);
		
		return jsonStr;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String generateTradeResponseAsJson(TradeResponse tradeResponse)
			throws TradeException {
		Map<String, Object> map = null;
		if(InterfaceCodeEnum.pay.getCode().equals(tradeResponse.getInterfaceCode())){
			PayResponse payResponse = (PayResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payResponse,payResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			PayQueryResponse payQueryResponse = (PayQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payQueryResponse,payQueryResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.refund.getCode().equals(tradeResponse.getInterfaceCode())){
			RefundResponse refundResponse = (RefundResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(refundResponse,refundResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(refundResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.refundQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			RefundQueryResponse refundQueryResponse = (RefundQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(refundQueryResponse,refundQueryResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(refundQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payFor.getCode().equals(tradeResponse.getInterfaceCode())){
			PayForResponse payForResponse = (PayForResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payForResponse,payForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payForResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payForWithAppr.getCode().equals(tradeResponse.getInterfaceCode())){
			PayForResponse payForResponse = (PayForResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(payForResponse,payForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payForResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.payForQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			PayForQueryResponse payForQueryResponse = (PayForQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
			encryptSomeFields(payForQueryResponse,payForQueryResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(payForQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else if(InterfaceCodeEnum.batchPayFor.getCode().equals(tradeResponse.getInterfaceCode())){
			BatchPayForResponse batchPayForResponse = (BatchPayForResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(batchPayForResponse,batchPayForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(batchPayForResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
			
		}else if(InterfaceCodeEnum.balanceQuery.getCode().equals(tradeResponse.getInterfaceCode())){
			AvailBalQueryResponse availBalQueryResponse = (AvailBalQueryResponse)tradeResponse;
			
			//1、报文敏感字段加密
//			encryptSomeFields(batchPayForResponse,batchPayForResponse.getMercNo());
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(availBalQueryResponse));
			map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
		}else{
			throw new InvalidParamException(RespEnum.E00006.getCode(),RespEnum.E00006.getDesc());
		}
		
		//2、报文加签
		String jsonStr = genMessageWithSignature(map);
		
		return jsonStr;
	}

	@Override
	public String generateRuntimeExceptionResponseAsJson(Exception e,JSONObject json) {
		//1、生成异常返回固定报文
		Map<String,Object>  responseTreeMap = new TreeMap<String, Object>();
		responseTreeMap.put("version",json.get("version"));
		responseTreeMap.put("locale",json.get("locale"));
		responseTreeMap.put("interfaceCode",json.get("interfaceCode"));
		responseTreeMap.put("charset",json.get("charset"));
		responseTreeMap.put("signType",json.get("signType"));
		responseTreeMap.put("mercNo",json.get("mercNo"));

		if(e instanceof InvalidParamException){
			responseTreeMap.put("respCode",((InvalidParamException)e).getErrorCode());
			responseTreeMap.put("respDesc",((InvalidParamException)e).getErrorMsg());
		}else if(e instanceof SystemException){
			responseTreeMap.put("respCode",((SystemException)e).getErrorCode());
			responseTreeMap.put("respDesc",((SystemException)e).getErrorMsg());
		}else if(e instanceof TradeException){
			responseTreeMap.put("respCode",((TradeException)e).getErrorCode());
			responseTreeMap.put("respDesc",((TradeException)e).getErrorMsg());
		}else{
			responseTreeMap.put("respCode",RespEnum.E99999.getCode());
			responseTreeMap.put("respDesc",RespEnum.E99999.getDesc());
		}
		
		//2、报文加签
		String jsonStr = genMessageWithSignature(responseTreeMap);
		return jsonStr;
	}
	
	
	/**
	 * 校验公共参数
	 * @param params
	 */
	public  void validateCommonparameters(Map<String,Object> params){
		
		if(!params.containsKey("version") || !params.containsKey("locale") ||
				!params.containsKey("interfaceCode")|| !params.containsKey("charset") ||
				!params.containsKey("signType") || !params.containsKey("signature") ||
				!params.containsKey("mercNo")){
			throw new InvalidParamException(RespEnum.E00009.getCode(),RespEnum.E00009.getDesc());
		}
			
		if(!params.get("version").equals("1.0") || !params.get("locale").toString().toUpperCase().equals("CN") ||
				 !params.get("charset").toString().toUpperCase().equals("UTF-8") || (!params.get("signType").equals("RSA")&&!params.get("signType").equals("MD5"))){
			throw new InvalidParamException(RespEnum.E00010.getCode(),RespEnum.E00010.getDesc());

		}
		if(StringUtils.isBlank((String)params.get("interfaceCode")) ||
				StringUtils.isBlank((String)params.get("signature")) ||  StringUtils.isBlank((String)params.get("mercNo") ) ){
			throw new InvalidParamException(RespEnum.E00010.getCode(),RespEnum.E00010.getDesc());
		}
		
	}
	/**
	 * 对商户请求报文进行验签
	 * 使用商户公钥验签
	 * @param reqMap
	 * @return
	 */
	public void verifySign(Map<String, Object> reqMap) {
		boolean verifySignResult = false;
		try {
            if(!redisCacheUtils.exists((String)reqMap.get("mercNo"))){
            	log.info("缓存中商户{}不存在",reqMap.get("mercNo"));
            	throw new InvalidParamException(RespEnum.E00031.getCode(), RespEnum.E00031.getDesc());
            }
            	
			MerchantInfo merchantInfo = (MerchantInfo)redisCacheUtils.getCacheObject((String)reqMap.get("mercNo"));
			if("01".equals(merchantInfo.getMercSignType())){
				verifySignResult = MD5Signature.doCheck(SignUtils.generateParamStr(reqMap),(String)reqMap.get("signature"),merchantInfo.getMercPrivateKey());
			}else if("02".equals(merchantInfo.getMercSignType())){
				log.info("对商户参数验签:reqMap="+JSONObject.toJSONString(reqMap)+" 商户公钥="+merchantInfo.getPublicKeyPath());
				PublicKey publicKey = RSAUtils.getPublicKey(merchantInfo.getPublicKeyPath());
				verifySignResult =  SignUtils.verifySignature(reqMap, publicKey);
			}else{
				throw new InvalidParamException(RespEnum.E00005.getCode(), RespEnum.E00005.getDesc());
			}
			
		} catch (Exception e) {
			log.info("对商户请求报文验签失败>>>>>>>>" + e.getMessage(), e);
			throw new TradeException(RespEnum.E00005.name(),RespEnum.E00005.getDesc());
		}
		if(!verifySignResult){
			log.info("对商户请求报文验签失败." );
			throw new InvalidParamException(RespEnum.E00005.getCode(), RespEnum.E00005.getDesc());
		}
	}
	
	/**
	 * 对商户请求报文中敏感字段进行解密
	 * 使用平台私钥解密
	 * @param reqMap
	 */
	public void unEncryptFields(Map<String, Object> reqMap) {
		List<String> encryptList = EncryptionField.encryptFields;
		Set<String> keys = reqMap.keySet();
		for(String key : keys){
			if(encryptList.contains(key)){
				try {
					//TODO 平台私钥从缓存中加载
					String decryptStr = SignUtils.decryptByPrivateKey((String)reqMap.get(key), GATEWAY_PRIVATE_KEY);
					reqMap.put(key, decryptStr);
				} catch (Exception e) {
					log.info("敏感字段解密错误>>>>>>>>" + e);
					throw new TradeException(RespEnum.E00012.getCode(),RespEnum.E00012.getDesc());
				}
			}
		}
	}
	
	/**
	 * 对返回商户报文中敏感字段进行加密
	 * 使用商户公钥加密
	 * @param tradeResponse
	 */
	public <T extends Object> void encryptSomeFields(T tradeResponse,String mercNo) {
		List<String> encryptList = EncryptionField.encryptFields;
		Field[] fields = tradeResponse.getClass().getDeclaredFields();
		for(Field field : fields){
			if(encryptList.contains(field.getName())){
				field.setAccessible(true);
				try {
					//TODO 从缓存中获取商户公钥
//					TpMerchantInfo merchantInfo = ServiceCacheTool.getCacheValue(CacheConstants.CACHE_MercCertCACHE, mercNo, TpMerchantInfo.class);
//					String encryptStr = SignUtils.encryptByPublicKey((String)field.get(tradeResponse), merchantInfo.getPublicKey());
//					field.set(tradeResponse, encryptStr);
				} catch (Exception e) {
					log.info("敏感字段加密错误>>>>>>>>" + e);
					throw new TradeException(RespEnum.E00011.getCode(),RespEnum.E00011.getDesc());
				}
			}
		}
	}
	
	
	
	/**
	 * 对返回商户报文进行加签
	 * 使用平台私钥加签
	 * @param responseTreeMap
	 * @return
	 */
	@Override
	public String genMessageWithSignature(Map<String, Object> responseTreeMap) {
		String jsonStr = null;
		if(responseTreeMap.get("mercNo") == null) {
			jsonStr = JSON.toJSONString(responseTreeMap);
			return jsonStr;
		}
		try {
		 	if(!redisCacheUtils.exists((String)responseTreeMap.get("mercNo"))){
				log.info("缓存中商户{}不存在，不加签",responseTreeMap.get("mercNo"));
            	throw new InvalidParamException(RespEnum.E00031.getCode(), RespEnum.E00031.getDesc());
            }
			//缓存中读取商户公钥
			MerchantInfo merchantInfo = (MerchantInfo)redisCacheUtils.getCacheObject((String)responseTreeMap.get("mercNo"));
			log.info("商户号:{},获取为商户信息为{}",(String)responseTreeMap.get("mercNo"),merchantInfo);
			if("01".equals(merchantInfo.getMercSignType())){
				log.info("对返回商户参数加签:reqMap="+JSONObject.toJSONString(responseTreeMap)+" 商户加签类型：{}"+merchantInfo.getMercSignType());
				String signature = MD5Signature.sign(SignUtils.generateParamStr(responseTreeMap), merchantInfo.getMercPrivateKey());
				responseTreeMap.put("signature", signature);
			}else if("02".equals(merchantInfo.getMercSignType())){
				log.info("对返回商户参数加签:reqMap="+JSONObject.toJSONString(responseTreeMap)+" 商户加签类型：{}"+merchantInfo.getMercSignType() +" 商户密钥路径：{}"+merchantInfo.getPublicKeyPath());
				String signature = RSASignature.sign(SignUtils.generateParamStr(responseTreeMap), GATEWAY_PRIVATE_KEY);
				responseTreeMap.put("signature", signature);
			}else{
				throw new InvalidParamException(RespEnum.E00066.getCode(), RespEnum.E00066.getDesc());
			}
			jsonStr = JSON.toJSONString(responseTreeMap);
		} catch (Exception e) {
			log.info("generateSignature生成签名错误>>>>>>>>" + e);
			if(e instanceof InvalidParamException){
				responseTreeMap.put("respCode",((InvalidParamException)e).getErrorCode());
				responseTreeMap.put("respDesc",((InvalidParamException)e).getErrorMsg());
			}else if(e instanceof SystemException){
				responseTreeMap.put("respCode",((SystemException)e).getErrorCode());
				responseTreeMap.put("respDesc",((SystemException)e).getErrorMsg());
			}else if(e instanceof TradeException){
				responseTreeMap.put("respCode",((TradeException)e).getErrorCode());
				responseTreeMap.put("respDesc",((TradeException)e).getErrorMsg());
			}else{
				responseTreeMap.put("respCode",RespEnum.E99999.getCode());
				responseTreeMap.put("respDesc",RespEnum.E99999.getDesc());
			}
			jsonStr = JSON.toJSONString(responseTreeMap);
		} 
		
		return jsonStr;
	}

	@Override
	public String generateTradeResponseAsForm(TradeResponse tradeResponse,
			String url) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		if(InterfaceCodeEnum.pay.name().equals(tradeResponse.getInterfaceCode())){
			PayResponse response = (PayResponse)tradeResponse;
		}
		//签名
		String signature = SignUtils.generateSignature(map,GATEWAY_PRIVATE_KEY);
		map.put("signature", signature);
		
		//生成form TODO
		return null;
	}

	@Override
	public void filterSpecChar(Map<String, Object> params) {
		SignUtils.filterSpecChar(params);
	}
}
