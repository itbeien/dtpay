package cn.itbeien.business.order.service;

import cn.itbeien.business.order.domain.TradeOrder;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 9:20
 * Copyright© 2024 beien
 */
public interface IOrderService {
    public List<TradeOrder> selectTradeOrderList(TradeOrder post);

    public TradeOrder findTradeOrderByOrderId(String orderId);

    public int deleteOrderByIds(String[] orderIds);

    public int update0rder(TradeOrder tradeOrder);
}
