package cn.itbeien.business.channel.mapper;


import cn.itbeien.business.channel.domain.PayChannel;

import java.util.List;

public interface PayChannelMapper {

    int deleteByPrimaryKey(String channelCode);

    int insert(PayChannel row);

    int insertSelective(PayChannel row);

    PayChannel selectByPrimaryKey(String channelCode);

    int updateByPrimaryKeySelective(PayChannel row);

    int updateByPrimaryKey(PayChannel row);

    List<PayChannel> selectPayChannelList(PayChannel payChannel);

    int deleteChannelByCodes(String[] channelCode);
}