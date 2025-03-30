package cn.itbeien.admin.service.order.impl;

import cn.itbeien.admin.mapper.order.TradeOrderSeqMapper;
import cn.itbeien.admin.service.order.ITradeOrderSeqService;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 @Service
public class TradeOrderSeqServiceImpl implements ITradeOrderSeqService {

	@Autowired
	private TradeOrderSeqMapper tradeOrderSeqMapper;
	
	@Override
	public List<Map<String, Object>> getList(Map<String, Object> param) {
		List<Map<String, Object>> list = tradeOrderSeqMapper.getList(param);
		return list;
	}
	
	@Override
	public TradeOrderSeq getByTradeSeq(String tradeSeq) {
		return tradeOrderSeqMapper.selectByPrimaryKey(tradeSeq);
	}

	@Override
	public TradeOrderSeq getByBankTradeSeq(String bankTradeSeq) {
		// TODO Auto-generated method stub
		return this.tradeOrderSeqMapper.selectByBankTradeSeq(bankTradeSeq);
	}
	
	@Override
	public Map<String,Object> getMapByOrderId(String tradeSeq){
		return this.tradeOrderSeqMapper.getMapByOrderId(tradeSeq);
	}
	
}