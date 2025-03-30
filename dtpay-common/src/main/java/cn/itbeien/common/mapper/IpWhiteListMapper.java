package cn.itbeien.common.mapper;

import cn.itbeien.common.entity.IpWhiteList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IpWhiteListMapper {
    int deleteByPrimaryKey(String id);

    int insert(IpWhiteList record);

    int insertSelective(IpWhiteList record);

    IpWhiteList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IpWhiteList record);

    int updateByPrimaryKey(IpWhiteList record);
    
    List<Map<String, Object>> getIpWhiteListByMap(@Param("mercNo") String mercNo);
}