package cn.itbeien.common.mapper.pay;


import cn.itbeien.common.entity.pay.PayChannel;

import java.util.List;
import java.util.Map;


public interface PayChannelMapper {
    int deleteByPrimaryKey(String channelCode);

    int insert(PayChannel record);

    int insertSelective(PayChannel record);

    PayChannel selectByPrimaryKey(String channelCode);

    int updateByPrimaryKeySelective(PayChannel record);

    int updateByPrimaryKey(PayChannel record);
    
    List<PayChannel> selectAll();
    /**
     * 查询所有机构名称
     * @return
     */
	List<Map<String, Object>> getChannelNames();
}