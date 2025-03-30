package cn.itbeien.admin.service.order.impl;

import cn.itbeien.admin.mapper.order.TradeRefundSeqMapper;
import cn.itbeien.admin.service.order.ITradeRefundSeqService;
import cn.itbeien.common.entity.trade.TradeRefundSeq;
import com.github.pagehelper.PageHelper;
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
public class TradeRefundSeqServiceImpl implements ITradeRefundSeqService {

	@Autowired
	private TradeRefundSeqMapper tradeRefundSeqMapper;
	
	@Override
	public List<Map<String, Object>> getList(Map<String, Object> param) {
		PageHelper.startPage(Integer.parseInt(param.get("pageNumber").toString()), Integer.parseInt(param.get("pageSize").toString()));
		List<Map<String, Object>> list = tradeRefundSeqMapper.getList(param);
		return list;
	}
	
	@Override
	public TradeRefundSeq getByRefundTradeSeq(String refundTradeSeq) {
		return tradeRefundSeqMapper.selectByPrimaryKey(refundTradeSeq);
	}
	
}