package cn.itbeien.merchant.service.trade;

import cn.itbeien.common.entity.trade.TradeRefundSeq;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.trade.TradeOrderSeqVO;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface ITradeRefundSeqService {

	/**
	 * 订单退款列表
	 * @param param
	 * @return
	 */
	public List<TradeRefundSeq> getTradeRefundSeqList(TradeOrderSeqVO param) throws Exception;
	
}
