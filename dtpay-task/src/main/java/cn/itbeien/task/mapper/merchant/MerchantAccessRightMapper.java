package cn.itbeien.task.mapper.merchant;


import cn.itbeien.common.entity.merchant.MerchantAccessRight;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface MerchantAccessRightMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantAccessRight record);

    int insertSelective(MerchantAccessRight record);

    MerchantAccessRight selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantAccessRight record);

    int updateByPrimaryKey(MerchantAccessRight record);
}