package cn.itbeien.payment.service.payfor.impl;

import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.enums.NotifyEnum;
import cn.itbeien.common.mapper.trade.TradeOrderMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.http.HttpClient;
import cn.itbeien.payment.core.vo.mq.MchPayForNotifyMqContent;
import cn.itbeien.payment.core.vo.response.PayForNotifyResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.service.verify.MerchantVerifyService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Component
public class PayForOrderNotifyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PayForOrderNotifyService.class);
    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Autowired
	private MerchantVerifyService merchantVerifyService;
	@Autowired
	private RedisCache redisCache;

    private static String UTF8="utf-8";
    
    
    public TradeOrder findTradeOrderById(String orderId){
    	return this.tradeOrderMapper.selectByPrimaryKey(orderId);
    }
    
    /*
     * 异步通知下游商户代付结果
     */
	public void payForNotifyMch(PayForNotifyResponse payNotifyResponse){
		Map<String,Object>  map = new HashMap<String,Object>();
    	
    	try {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(payNotifyResponse));
    		map = jsonObject.toJavaObject(HashMap.class);
    		String postURL = (String) map.remove("notifyUrl");   //下游商户异步回调URL
        	String requestBody = merchantVerifyService.genMessageWithSignature(map);
        	if(StringUtils.isEmpty(postURL)) {
        		LOGGER.info("从map中取出代付回调URL为空,商户代付订单号:" + payNotifyResponse.getMercOrderNo() + ",平台代付订单号:" + payNotifyResponse.getPayId());
        		return;
        	}
    		//success 处理成功，RXPAY平台收到此结果后不再进行后续通知
    		//fail或其它字符 处理不成功，RXPAY平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
    		//如果RXPAY平台收到商户的应答不是纯字符串success或超过5秒后返回时，平台认为通知失败
        	String mchResult = "fail";
        	LOGGER.info("代付回调下游 商户代付订单号：" + payNotifyResponse.getMercOrderNo() + ",平台代付订单号：" + payNotifyResponse.getPayId()+ ",异步通知地址：" + postURL + ",请求的JSON为：" + requestBody);
			
        	// 如果不存在，设置默认的通知次数为5次
        	if(!redisCache.exists(payNotifyResponse.getPayId())) {
				redisCache.setCacheObject(payNotifyResponse.getPayId(), 5);
        	}
        	
        	mchResult = HttpClient.send(postURL, requestBody, UTF8, UTF8,5);
			
        	int notifyCount = (int)redisCache.getCacheObject(payNotifyResponse.getPayId());
        	// 次数为0 的时候，说明通知的5次都没有成功，将次数在缓存中移除
			if(notifyCount == 0) {
				redisCache.deleteObject(payNotifyResponse.getPayId());
			}
			if(NotifyEnum.success.name().equals(mchResult)){
				if(redisCache.exists(payNotifyResponse.getPayId())) {
					redisCache.deleteObject(payNotifyResponse.getPayId());
				}
			} else if(notifyCount > 0) {
				//发起异步通知,补单机制(通知频率为10/10/10/10/10，单位：秒)
				//补单机制通过消息队列的延迟发送消息实现(在配置文件中配置messageDelayLevel属性)，不通过定时器实现
				//补单机制重复发送消息5次
				//DtRocketMqProducer rxRocketMqProducer = (DtRocketMqProducer)SpringContextUtil.getBean("rxRocketMqProducer");//消息生产者
				MchPayForNotifyMqContent payForNotifyMqContent = new MchPayForNotifyMqContent();
				BeanUtils.copyProperties(payNotifyResponse, payForNotifyMqContent);
				//rxRocketMqProducer.mqPayForNotifyMch(payForNotifyMqContent);
				// 缓存中存在，且次数大于0，则将原来的次数 减去1
				if(redisCache.exists(payNotifyResponse.getPayId())) {
					redisCache.setCacheObject(payNotifyResponse.getPayId(), notifyCount - 1);
				}
			}
			LOGGER.info("代付异步通知下游商户---下游商户返回结果----"+ mchResult + ",商户代付订单号为:" + payNotifyResponse.getMercOrderNo() + ",平台代付订单号为:" + payNotifyResponse.getPayId());
			payNotifyResponse.setRespCode(RespEnum.S00000.getCode());
			payNotifyResponse.setRespCode(RespEnum.S00000.getDesc());
		} catch (Exception e) {
			payNotifyResponse.setRespCode(RespEnum.E00009.getCode());
			payNotifyResponse.setRespDesc(RespEnum.E00009.getDesc());
			LOGGER.error("代付异步通知下游商户异常,商户代付单号：" + payNotifyResponse.getMercOrderNo() + ",平台代付订单号:" + payNotifyResponse.getPayId() + ",异常原因:" + e.getMessage(), e);
		}
    	
    }


	public static void main(String[] args) throws Exception {

		String postURL = "https://www.itbeien.cn";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tradeAmt","0.01");
		jsonObject.put("charset","UTF-8");
		jsonObject.put("orderId","1075723461533634560");
		jsonObject.put("signature","E451FCCDD672FC5B91D1F84B3140A6EE");
		jsonObject.put("termType","pc");
		jsonObject.put("mercOrderNo","181220200350042");
		jsonObject.put("locale","CN");
		jsonObject.put("tradeEndTime","20181220200433");
		jsonObject.put("version","1.0");
		jsonObject.put("mercNo","RX1537280902384");
		jsonObject.put("tradeTime","20181220200353");
		jsonObject.put("notifyUrl","http,//www.yiyg.com.cn/laomi/include/plugin/payway/epay/notify.php");
		jsonObject.put("signType","MD5");
		jsonObject.put("payStatus","p0000");
		jsonObject.put("tradeType","01");
		String mchResult = HttpClient.send(postURL, jsonObject.toJSONString(), UTF8, UTF8,5);
		System.out.println("mchResult==" + mchResult);
	}


}
