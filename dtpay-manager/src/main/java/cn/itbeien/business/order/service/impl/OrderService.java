package cn.itbeien.business.order.service.impl;

import cn.itbeien.business.order.domain.TradeOrder;
import cn.itbeien.business.order.mapper.TradeOrderMapper;
import cn.itbeien.business.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 9:21
 * Copyright© 2024 beien
 */
@Service
public class OrderService implements IOrderService {
    @Autowired
    private TradeOrderMapper tradeOrderMapper;
    @Override
    public List<TradeOrder> selectTradeOrderList(TradeOrder post) {
        return this.tradeOrderMapper.selectTradeOrderList(post);
    }

    @Override
    public TradeOrder findTradeOrderByOrderId(String orderId) {
        return this.tradeOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int deleteOrderByIds(String[] orderIds) {
        return this.tradeOrderMapper.deleteOrderByIds(orderIds);
    }

    @Override
    public int update0rder(TradeOrder tradeOrder) {
        return this.tradeOrderMapper.updateByPrimaryKeySelective(tradeOrder);
    }
}
