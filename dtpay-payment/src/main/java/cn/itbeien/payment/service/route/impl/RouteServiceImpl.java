package cn.itbeien.payment.service.route.impl;

import cn.itbeien.common.entity.pay.PayChannel;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.SpringUtils;
import cn.itbeien.payment.core.IDtPayService;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.service.route.IRouteService;
import cn.itbeien.payment.service.route.model.RouteResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RouteServiceImpl implements IRouteService {

	private final RedisCache redisCache;

	@Override
	public RouteResultModel freeRoute(String mercNo, String paywayCode, String sceneCode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public RouteResultModel routeByCondition(String mercNo, String channelCode, String paywayCode, String sceneCode) {
		log.info("对应商户号：{}，对应渠道编号：{}",mercNo,channelCode);
		log.info("对应支付编号：{}，对应场景编号：{}",paywayCode,sceneCode);
		
		RouteResultModel routeResultModel = new RouteResultModel();
		routeResultModel.setChannelCode(channelCode);
		routeResultModel.setPaywayCode(paywayCode);
		routeResultModel.setScenesCode(sceneCode);
		PayChannel payChannel = redisCache.getCacheObject(channelCode);
		if(payChannel==null){
			log.info("获取渠道缓存失败,对应渠道编号{}",paywayCode);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		}
		
		log.info("对应渠道{}的BEAN_ID为：{}",payChannel.getChannelName(),payChannel.getServiceBeanId());
		routeResultModel.setServiceBeanId(payChannel.getServiceBeanId());
		routeResultModel.setChannelType(payChannel.getRsfld1());
		routeResultModel.setPayFeeValue(payChannel.getPayFeeValue());
		routeResultModel.setRxService((IDtPayService) SpringUtils.getBean(payChannel.getServiceBeanId()));
		return routeResultModel;
	}

}
