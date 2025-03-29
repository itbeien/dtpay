package cn.itbeien.merchant.service.trade.impl;

import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.trade.TradeOrderSeqReportVO;
import cn.itbeien.common.vo.trade.TradeOrderSeqVO;
import cn.itbeien.merchant.mapper.trade.ITradeOrderSeqMapper;
import cn.itbeien.merchant.service.trade.ITradeOrderSeqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Service
@RequiredArgsConstructor
public class TradeOrderSeqServiceImpl implements ITradeOrderSeqService {
	
	private final ITradeOrderSeqMapper tradeOrderSeqMapper;

	@Override
	public List<TradeOrderSeq> getTradeOrderSeqList(TradeOrderSeqVO param) {
		List<TradeOrderSeq> list = tradeOrderSeqMapper.getTradeOrderSeqList(param);
		return list;
	}

	@Override
	public List<TradeOrderSeq> getTradeOrderList(TradeOrderSeqVO param) {
		List<TradeOrderSeq> list = tradeOrderSeqMapper.getTradeOrderSeqList(param);
		return list;
	}
	
	public List<TradeOrderSeq> selectTradeOrderSeqList(TradeOrderSeqVO param) {
		return tradeOrderSeqMapper.selectTradeOrderSeqList(param);
	}
	
	@Override
	public TradeOrderSeqReportVO sumAmtAndCount(TradeOrderSeqVO param) {
		return this.tradeOrderSeqMapper.sumAmtAndCount(param);
	}
}
