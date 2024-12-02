package cn.itbeien.task.dao.order;

import cn.itbeien.task.entity.TradeOrderSeq;
import org.apache.ibatis.annotations.Param;

public interface TradeOrderSeqMapper {


    int deleteByPrimaryKey(String tradeSeq);

    int insert(TradeOrderSeq row);

    int insertSelective(TradeOrderSeq row);

    TradeOrderSeq selectByPrimaryKey(String tradeSeq);

    int updateByPrimaryKeySelective(TradeOrderSeq row);

    int updateByPrimaryKey(TradeOrderSeq row);

    int updateTradeOrderSeqByOrderId(@Param("payStatus") String payStatus, @Param("orderId") String orderId, @Param("transactionId") String transactionId);
}