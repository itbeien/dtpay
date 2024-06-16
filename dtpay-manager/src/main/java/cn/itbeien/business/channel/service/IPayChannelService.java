package cn.itbeien.business.channel.service;

import cn.itbeien.business.channel.domain.PayChannel;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 13:06
 * Copyright© 2024 beien
 */
public interface IPayChannelService {
    List<PayChannel> selectPayChannelList(PayChannel payChannel);
    int savePayChannel(PayChannel payChannel);

    PayChannel findPayChannelByCode(String channelCode);

    int updateChannel(PayChannel payChannel);

    int deleteChannelByCodes(String [] channelCode);
}
