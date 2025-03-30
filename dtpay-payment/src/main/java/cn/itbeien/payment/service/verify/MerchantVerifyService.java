package cn.itbeien.payment.service.verify;

import cn.itbeien.payment.core.vo.TradeRequest;
import cn.itbeien.payment.core.vo.TradeResponse;
import cn.itbeien.payment.exception.TradeException;
import com.alibaba.fastjson2.JSONObject;

import java.util.Map;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 商户接入报文验证验签
 * Copyright© 2025 itbeien
 */
public interface MerchantVerifyService {

	/**
	 * 对报文进行验签，对参数进行校验,并对敏感信息进行解密
	 * @param  
	 * @return 验证通过后的结果
	 * @throws Exception 验证不通过抛异常
	 */
	public <T extends TradeRequest> T validateTradeData(Map<String,Object> params)throws TradeException;
	
	/**
	 * 对报文进行验签，对参数进行校验,并对敏感信息进行解密
	 * @param  
	 * @return 验证通过后的结果
	 * @throws Exception 验证不通过抛异常
	 */
	public <T extends TradeRequest> T validateTradeData(JSONObject jsonObj)throws TradeException;
	
	/**
	 * 创建商户响应报文，加密、加签
	 * @param tradeRequest 注意map中包含map的情况
	 * @param tradeResponse 注意map中包含map的情况
	 * @return 返回json结构
	 * @throws Exception
	 */
	public String generateTradeResponseAsJson(TradeRequest tradeRequest,TradeResponse tradeResponse)throws TradeException;
	
	/**
	 * 创建商户响应报文，加密、加签
	 * @param tradeResponse 注意map中包含map的情况
	 * @return 返回json结构
	 * @throws Exception
	 */
	public String generateTradeResponseAsJson(TradeResponse tradeResponse)throws TradeException;
	
	/**
	 * 创建商户响应form表单
	 * @param tradeResponse
	 * @param url
	 * @return
	 * @throws TradeException
	 */
	public String generateTradeResponseAsForm(TradeResponse tradeResponse, String url)throws TradeException;
	
	/**
	 * 根据传入参数获取请求对象
	 * @param params
	 * @return
	 */
	public <T extends TradeRequest> T getTradeRequest(Map<String, Object> params);

	/**
	 * 根据传入参数获取请求对象
	 * @param jsonObj
	 * @return
	 */
	public <T extends TradeRequest> T getTradeRequest(JSONObject jsonObj)throws Exception;

	/**
	 * 生成运行时异常报文
	 * @param e 
	 * @param jsonObj 
	 * @return
	 */
	public String generateRuntimeExceptionResponseAsJson(Exception e, JSONObject jsonObj);
	
	/**
	 * 特殊字符过滤
	 * @param params
	 */
	public void filterSpecChar(Map<String, Object> params);
	
	
	/**
	 * 生成加签报文
	 * @param params
	 */
	public String genMessageWithSignature(Map<String, Object> params)throws TradeException;

}
