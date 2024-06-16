package cn.itbeien.business.order.service;

import cn.itbeien.business.order.domain.TradeOrderSeq;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 9:20
 * Copyright© 2024 beien
 */
public interface IPaySeqService {
    public List<TradeOrderSeq> selectTradeOrderSeqList(TradeOrderSeq post);

    TradeOrderSeq findTradeOrderSeqBySeq(String tradeSeq);

    int deleteOrderSeqByIds(String[]ids);
}
