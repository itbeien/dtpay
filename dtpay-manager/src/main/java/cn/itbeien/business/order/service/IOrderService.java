package cn.itbeien.business.order.service;

import cn.itbeien.business.order.domain.TradeOrder;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IOrderService {
    public List<TradeOrder> selectTradeOrderList(TradeOrder post);

    public TradeOrder findTradeOrderByOrderId(String orderId);

    public int deleteOrderByIds(String[] orderIds);

    public int update0rder(TradeOrder tradeOrder);
}
