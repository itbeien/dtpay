package cn.itbeien.task.mapper.trade.tradeorder;

import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.task.vo.trade.TradeOrderQryPar;

import java.util.List;

public interface TradeOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(TradeOrder record);

    int insertSelective(TradeOrder record);

    TradeOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);
    
    List<TradeOrder> qryTradeOrderByPage(TradeOrderQryPar param);
    
    
    List<TradeOrder> qryTradeOrders(TradeOrderQryPar param);
}