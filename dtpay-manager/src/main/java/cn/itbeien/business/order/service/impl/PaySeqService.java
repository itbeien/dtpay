package cn.itbeien.business.order.service.impl;

import cn.itbeien.business.order.domain.TradeOrderSeq;
import cn.itbeien.business.order.mapper.TradeOrderSeqMapper;
import cn.itbeien.business.order.service.IPaySeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 9:21
 * Copyright© 2024 beien
 */
@Service
public class PaySeqService implements IPaySeqService {
    @Autowired
    private TradeOrderSeqMapper tradeOrderSeqMapper;
    @Override
    public List<TradeOrderSeq> selectTradeOrderSeqList(TradeOrderSeq post) {
        return this.tradeOrderSeqMapper.selectTradeOrderSeqList(post);
    }

    @Override
    public TradeOrderSeq findTradeOrderSeqBySeq(String tradeSeq) {
        return this.tradeOrderSeqMapper.selectByPrimaryKey(tradeSeq);
    }

    @Override
    public int deleteOrderSeqByIds(String[] ids) {
        return this.tradeOrderSeqMapper.deleteOrderSeqByIds(ids);
    }
}
