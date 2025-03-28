package cn.itbeien.merchant.service.trade.impl;

import cn.itbeien.common.entity.trade.TradeRefundSeq;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.trade.TradeOrderSeqVO;
import cn.itbeien.merchant.mapper.trade.ITradeRefundSeqMapper;
import cn.itbeien.merchant.service.trade.ITradeRefundSeqService;
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
public class TradeRefundSeqServiceImpl implements ITradeRefundSeqService {
	
	private final ITradeRefundSeqMapper tradeRefundSeqMapper;

	@Override
	public BootTable<TradeRefundSeq> getTradeRefundSeqList(TradeOrderSeqVO param) {
		List<TradeRefundSeq> list = tradeRefundSeqMapper.getTradeRefundSeqList(param);
		return new BootTable<TradeRefundSeq>(list);
	}

}
