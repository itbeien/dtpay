package cn.itbeien.payment.service.tradequery;


import cn.itbeien.payment.core.vo.request.PayForQueryRequest;
import cn.itbeien.payment.core.vo.request.RefundQueryRequest;
import cn.itbeien.payment.core.vo.response.PayForQueryResponse;
import cn.itbeien.payment.core.vo.response.RefundQueryResponse;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface ITradeQueryService {

	 public void refundQuery(RefundQueryRequest tradeRequest, RefundQueryResponse tradeResponse);
	
	 public void payForQuery(PayForQueryRequest tradeRequest, PayForQueryResponse tradeResponse);
	 
}
