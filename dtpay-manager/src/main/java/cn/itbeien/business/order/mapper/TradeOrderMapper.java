package cn.itbeien.business.order.mapper;

import cn.itbeien.business.order.domain.TradeOrder;

import java.util.List;

public interface TradeOrderMapper {

    TradeOrder selectByPrimaryKey(String orderId);

    int deleteByPrimaryKey(String orderId);

    int insert(TradeOrder row);

    int insertSelective(TradeOrder row);

    int updateByPrimaryKeySelective(TradeOrder row);

    int updateByPrimaryKey(TradeOrder row);

    public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder);

    int deleteOrderByIds(String[] orderIds);
}