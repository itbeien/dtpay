package cn.itbeien.business.channel.service;

import cn.itbeien.business.channel.domain.PayChannel;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IPayChannelService {
    List<PayChannel> selectPayChannelList(PayChannel payChannel);
    int savePayChannel(PayChannel payChannel);

    PayChannel findPayChannelByCode(String channelCode);

    int updateChannel(PayChannel payChannel);

    int deleteChannelByCodes(String [] channelCode);
}
