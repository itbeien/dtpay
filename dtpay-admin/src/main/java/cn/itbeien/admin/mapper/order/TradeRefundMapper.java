package cn.itbeien.admin.mapper.order;

import cn.itbeien.admin.vo.order.TradeRefundQryPar;
import cn.itbeien.common.entity.trade.TradeRefund;

import java.util.List;

public interface TradeRefundMapper {
    int deleteByPrimaryKey(String refundOrderId);

    int insert(TradeRefund record);

    int insertSelective(TradeRefund record);

    TradeRefund selectByPrimaryKey(String refundOrderId);

    int updateByPrimaryKeySelective(TradeRefund record);

    int updateByPrimaryKey(TradeRefund record);
    
    List<TradeRefund> qryTradeRefundByPage(TradeRefundQryPar param);
    
    List<TradeRefund> qrySellerTradeRefundByPage(TradeRefundQryPar param);
}