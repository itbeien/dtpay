package cn.itbeien.common.mapper;

import cn.itbeien.common.entity.RouterPolicy;

import java.util.List;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface RouterPolicyMapper {
    int deleteByPrimaryKey(String routerCode);

    int insert(RouterPolicy record);

    int insertSelective(RouterPolicy record);

    RouterPolicy selectByPrimaryKey(String routerCode);

    int updateByPrimaryKeySelective(RouterPolicy record);

    int updateByPrimaryKey(RouterPolicy record);
    
    List<RouterPolicy> selectAll();
}