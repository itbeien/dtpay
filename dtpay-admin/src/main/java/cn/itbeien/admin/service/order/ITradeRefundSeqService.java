package cn.itbeien.admin.service.order;

import cn.itbeien.common.entity.trade.TradeRefundSeq;

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
public interface ITradeRefundSeqService{
	
	/**
	 * 退款流水列表查询
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param);
	
	/**
	 * 根据退款流水号查询实体
	 * @param refundTradeSeq
	 * @return
	 */
	public TradeRefundSeq getByRefundTradeSeq(String refundTradeSeq);
}