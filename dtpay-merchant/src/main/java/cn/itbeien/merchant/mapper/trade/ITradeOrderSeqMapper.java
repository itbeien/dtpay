package cn.itbeien.merchant.mapper.trade;


import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.vo.trade.TradeOrderSeqReportVO;
import cn.itbeien.common.vo.trade.TradeOrderSeqVO;

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
public interface ITradeOrderSeqMapper {
	
	/**
	 * 交易管理->收款订单
	 * 商户下面的客户支付给商户的订单流水列表
	 * @param param
	 * @return
	 */
	public List<TradeOrderSeq> getTradeOrderSeqList(TradeOrderSeqVO param);
	
	 /**
     * 汇总订单总笔数及订单总金额
     * @param param
     * @return
     */
	 TradeOrderSeqReportVO sumAmtAndCount(TradeOrderSeqVO param);
    
    /**
	 * 交易管理->收款订单
	 * 商户下面的客户支付给商户的订单流水列表
	 * @param param
	 * @return
	 */
	public List<TradeOrderSeq> selectTradeOrderSeqList(TradeOrderSeqVO param);
	
}
