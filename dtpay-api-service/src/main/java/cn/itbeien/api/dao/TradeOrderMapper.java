package cn.itbeien.api.dao;

import cn.itbeien.api.entity.order.TradeOrder;
import org.apache.ibatis.annotations.Param;

public interface TradeOrderMapper {

    int insert(TradeOrder row);

    int insertSelective(TradeOrder row);

    int updateByPrimaryKeySelective(TradeOrder row);

    int updateByPrimaryKey(TradeOrder row);

    int updateTradeOrderByOrderId(@Param("payStatus") String payStatus,@Param("orderId") String orderId);

    TradeOrder selectTradeOrderByPrimaryKey(String orderId);
}