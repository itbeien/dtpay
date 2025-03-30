package cn.itbeien.payment.service.route;

import cn.itbeien.payment.service.route.model.RouteResultModel;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IRouteService {

	
	RouteResultModel freeRoute(String mercNo, String paywayCode, String sceneCode);
	
	RouteResultModel routeByCondition(String mercNo, String channelCode,String paywayCode,String sceneCode);
	
}
