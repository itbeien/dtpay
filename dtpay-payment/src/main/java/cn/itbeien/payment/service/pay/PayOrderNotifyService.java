package cn.itbeien.payment.service.pay;

import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.enums.NotifyEnum;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.http.HttpClient;
import cn.itbeien.payment.core.vo.mq.MchNotifyMqContent;
import cn.itbeien.payment.core.vo.response.PayNotifyResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.mapper.trade.TradeOrderMapper;
import cn.itbeien.payment.mapper.trade.TradeOrderSeqMapper;
import cn.itbeien.payment.service.verify.MerchantVerifyService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class PayOrderNotifyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderNotifyService.class);
    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Autowired
	private MerchantVerifyService merchantVerifyService;
	@Autowired
	private RedisCache redisCache;
	@Autowired
	private TradeOrderSeqMapper tradeOrderSeqMapper;
	
    private static String UTF8="utf-8";
    
//    private boolean flag = false;//标识是否触发补单机制(false没有触发,true已经触发)
    
    private static final String REISSUE = "reissue"; // 商户后台补发通知标识
    
    
    public TradeOrder findTradeOrderById(String orderId){
    	return this.tradeOrderMapper.selectByPrimaryKey(orderId);
    }
    
    /*
     * 异步通知下游商户支付结果
     */
	public void notifyInformMch(PayNotifyResponse payNotifyResponse){
    	Map<String,Object> map = new HashMap<String,Object>();
    	try {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(payNotifyResponse));
    		map = (Map<String,Object>)jsonObject.toJavaObject(HashMap.class);
    		Object noticeFlagObj = map.remove("flag");
    		String noticeFlag = noticeFlagObj == null ? null : noticeFlagObj.toString() ;
        	String requestBody = merchantVerifyService.genMessageWithSignature(map);
        	String postURL = (String)map.remove("notifyUrl");
        	
    		//success 处理成功，DTPAY平台收到此结果后不再进行后续通知
    		//fail或其它字符 处理不成功，DTPAY平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
    		//如果RXPAY平台收到商户的应答不是纯字符串success或超过5秒后返回时，平台认为通知失败
        	String mchResult = "fail";
        	LOGGER.info("支付异步通知下游 商户订单号：" + payNotifyResponse.getMercOrderNo() + ",平台订单号：" + payNotifyResponse.getOrderId() + ",异步通知地址:" + postURL + ",请求的JSON为：" + requestBody);
			
        	// 如果不是在商户后台点击的补发通知，即 即 交易系统 接口回调
			if(!REISSUE.equals(noticeFlag)) {
				// 如果不存在，设置默认的通知次数为5次
	        	if(!redisCache.exists(payNotifyResponse.getOrderId())) {
	        		redisCache.setCacheObject(payNotifyResponse.getOrderId(), 5);
	        	}
			}
        	mchResult = HttpClient.send(postURL, requestBody, UTF8, UTF8,5);
			
			// 如果不是在商户后台点击的补发通知，即 交易系统 接口回调
			if(!REISSUE.equals(noticeFlag)) {
				int notifyCount = (int)redisCache.getCacheObject(payNotifyResponse.getOrderId());
				if(notifyCount == 0) { // 次数为0 的时候，说明通知的5次都没有成功，将次数在缓存中移除
					redisCache.deleteObject(payNotifyResponse.getOrderId());
				}
				if(NotifyEnum.success.name().equals(mchResult)){
					// 收到下游的success 则修改数据库通知状态 为成功
					tradeOrderSeqMapper.updateOrderByOrderId(payNotifyResponse.getOrderId(), "01");
					// 直接将次数 remove
					if(redisCache.exists(payNotifyResponse.getOrderId())) {
						redisCache.deleteObject(payNotifyResponse.getOrderId());
					}
				} else if(notifyCount > 0) {
					//发起异步通知,补单机制(通知频率为10/10/10/10/10，单位：秒),5次
					//补单机制通过消息队列的延迟发送消息实现(在配置文件中配置messageDelayLevel属性)，不通过定时器实现
					//DtRocketMqProducer rxRocketMqProducer = (DtRocketMqProducer)SpringContextUtil.getBean("rxRocketMqProducer");//消息生产者
					MchNotifyMqContent mchNotifyMqContent = new MchNotifyMqContent();
					BeanUtils.copyProperties(payNotifyResponse,mchNotifyMqContent);
					//rxRocketMqProducer.mqNotifyMch(mchNotifyMqContent);
					// 缓存中存在，且次数大于0，则将原来的次数 减去1
					if(redisCache.exists(payNotifyResponse.getOrderId())) {
						redisCache.setCacheObject(payNotifyResponse.getOrderId(), notifyCount - 1);
					}
				}
			} else {  //  在商户后台点击的补发通知
				if(NotifyEnum.success.name().equals(mchResult)) {
					// 收到下游的success 则修改数据库通知状态 为成功
					tradeOrderSeqMapper.updateOrderByOrderId(payNotifyResponse.getOrderId(), "01");
				}
			}
			
			LOGGER.info("支付异步通知下游商户---下游商户返回结果----"+mchResult + ",商户订单号为:" + payNotifyResponse.getMercOrderNo() + ",平台订单号为:" + payNotifyResponse.getOrderId());
			payNotifyResponse.setRespCode(RespEnum.S00000.getCode());
			payNotifyResponse.setRespCode(RespEnum.S00000.getDesc());
			
		} catch (Exception e) {
			payNotifyResponse.setRespCode(RespEnum.E00009.getCode());
			payNotifyResponse.setRespDesc(RespEnum.E00009.getDesc());
			e.printStackTrace();
			LOGGER.error("支付异步通知下游商户异常,商户订单号：" + payNotifyResponse.getMercOrderNo() + ",平台订单号:" + payNotifyResponse.getOrderId() + ",异常原因:" + e.getMessage(), e);
		}
    	
    }

}
