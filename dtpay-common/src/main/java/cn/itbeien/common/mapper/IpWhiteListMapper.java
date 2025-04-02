package cn.itbeien.common.mapper;

import cn.itbeien.common.entity.IpWhiteList;
import cn.itbeien.common.vo.IpWhiteListQryPar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IpWhiteListMapper {
    int deleteByPrimaryKey(String id);

    int insert(IpWhiteList record);

    int insertSelective(IpWhiteList record);

    IpWhiteList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IpWhiteList record);

    int updateByPrimaryKey(IpWhiteList record);
    
    List<Map<String, Object>> getIpWhiteListByMap(@Param("mercNo") String mercNo);

    List<IpWhiteList> selectIpWhiteLists(IpWhiteListQryPar param);
}