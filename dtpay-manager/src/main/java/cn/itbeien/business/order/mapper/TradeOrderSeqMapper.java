package cn.itbeien.business.order.mapper;

import cn.itbeien.business.order.domain.TradeOrderSeq;

import java.util.List;

public interface TradeOrderSeqMapper {

    int deleteByPrimaryKey(String tradeSeq);

    int insert(TradeOrderSeq row);

    int insertSelective(TradeOrderSeq row);

    TradeOrderSeq selectByPrimaryKey(String tradeSeq);

    int updateByPrimaryKeySelective(TradeOrderSeq row);

    int updateByPrimaryKey(TradeOrderSeq row);

    List<TradeOrderSeq> selectTradeOrderSeqList(TradeOrderSeq tradeOrderSeq);

    int deleteOrderSeqByIds(String[]ids);
}