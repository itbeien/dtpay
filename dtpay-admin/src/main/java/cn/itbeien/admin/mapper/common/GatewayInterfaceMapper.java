package cn.itbeien.admin.mapper.common;

import cn.itbeien.common.entity.common.GatewayInterface;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface GatewayInterfaceMapper {
    int deleteByPrimaryKey(String interfaceCode);

    int insert(GatewayInterface record);

    int insertSelective(GatewayInterface record);

    GatewayInterface selectByPrimaryKey(String interfaceCode);

    int updateByPrimaryKeySelective(GatewayInterface record);

    int updateByPrimaryKey(GatewayInterface record);
}