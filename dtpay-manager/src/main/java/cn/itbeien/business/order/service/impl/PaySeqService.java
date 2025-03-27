package cn.itbeien.business.order.service.impl;

import cn.itbeien.business.order.domain.TradeOrderSeq;
import cn.itbeien.business.order.mapper.TradeOrderSeqMapper;
import cn.itbeien.business.order.service.IPaySeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
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
