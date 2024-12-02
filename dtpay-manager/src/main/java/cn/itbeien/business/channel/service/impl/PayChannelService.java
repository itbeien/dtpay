package cn.itbeien.business.channel.service.impl;

import cn.itbeien.business.channel.domain.PayChannel;
import cn.itbeien.business.channel.mapper.PayChannelMapper;
import cn.itbeien.business.channel.service.IPayChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
public class PayChannelService implements IPayChannelService {
    @Resource
    private PayChannelMapper payChannelMapper;
    @Override
    public List<PayChannel> selectPayChannelList(PayChannel payChannel) {
        return payChannelMapper.selectPayChannelList(payChannel);
    }

    @Override
    public int savePayChannel(PayChannel payChannel) {
        return this.payChannelMapper.insertSelective(payChannel);
    }

    @Override
    public PayChannel findPayChannelByCode(String channelCode) {
        return this.payChannelMapper.selectByPrimaryKey(channelCode);
    }

    @Override
    public int updateChannel(PayChannel payChannel) {
        return this.payChannelMapper.updateByPrimaryKeySelective(payChannel);
    }

    @Override
    public int deleteChannelByCodes(String[] channelCode) {
        return this.payChannelMapper.deleteChannelByCodes(channelCode);
    }
}
