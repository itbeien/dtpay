package cn.itbeien.payment.mapper.trade;

import cn.itbeien.common.entity.trade.TradeRefund;

import java.math.BigDecimal;
import java.util.Map;

public interface TradeRefundMapper {
    int deleteByPrimaryKey(String refundOrderId);

    int insert(TradeRefund record);

    int insertSelective(TradeRefund record);

    TradeRefund selectByPrimaryKey(String refundOrderId);

    int updateByPrimaryKeySelective(TradeRefund record);

    int updateByPrimaryKey(TradeRefund record);
    
    BigDecimal selectRefundSumAmt(TradeRefund record);
    
    TradeRefund selectByMap(Map<String,Object> map);
    
}