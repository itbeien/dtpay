package cn.itbeien.admin.service.order;


import cn.itbeien.common.entity.trade.TradeOrderSeq;

import java.util.List;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface ITradeOrderSeqService{
	
	/**
	 * 支付订单流水列表查询
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param);
	
	/**
	 * 根据支付流水号查询实体
	 * @param tradeSeq
	 * @return
	 */
	public TradeOrderSeq getByTradeSeq(String tradeSeq);
	
	/**
	 * 根据上游订单号查询实体
	 * @param orderId
	 * @return
	 */
	public TradeOrderSeq getByBankTradeSeq(String orderId);
	
	/**
	 * 根据支付流水号查询实体(Map)
	 * @param tradeSeq
	 * @return
	 */
	public Map<String,Object> getMapByOrderId(String tradeSeq);
	
}