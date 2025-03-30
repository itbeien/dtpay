package cn.itbeien.admin.service.order.impl;

import cn.itbeien.admin.mapper.order.TradeOrderMapper;
import cn.itbeien.admin.service.order.ITradeOrderService;
import cn.itbeien.admin.vo.order.TradeOrderQryPar;
import cn.itbeien.common.entity.trade.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
public class TradeOrderServiceImpl implements ITradeOrderService {
	

	@Autowired
	private TradeOrderMapper tradeOrderMapper;

	@Override
	public List<TradeOrder> qryTradeOrderByPage(TradeOrderQryPar param) throws DataAccessException {
		List<TradeOrder> tradeOrders = null;
		// 说明是商务
		if("1".equals(param.getQryType())) {
			tradeOrders = tradeOrderMapper.qrySellerTradeOrderByPage(param);
		} else {
			tradeOrders = tradeOrderMapper.qryTradeOrderByPage(param);
		}
		
		return tradeOrders;
	}

	@Override
	public TradeOrder selectByPrimaryKey(String orderId) throws DataAccessException {
		TradeOrder tradeOrder = this.tradeOrderMapper.selectByPrimaryKey(orderId);
		return tradeOrder;
	}
	
	
	@Override
	public List<TradeOrder> qryTradeOrders(TradeOrderQryPar param) throws DataAccessException {
		List<TradeOrder> tradeOrders = null;
		// 说明是商务
		if("1".equals(param.getQryType())) {
			tradeOrders = tradeOrderMapper.qrySellerTradeOrderByPage(param);
		} else {
			tradeOrders = tradeOrderMapper.qryTradeOrderByPage(param);
		}
		
		return tradeOrders;
	}

	@Override
	public Map<String, Object> sumAmtAndCount(TradeOrderQryPar param) throws DataAccessException {
		return this.tradeOrderMapper.sumAmtAndCount(param);
	}


}
