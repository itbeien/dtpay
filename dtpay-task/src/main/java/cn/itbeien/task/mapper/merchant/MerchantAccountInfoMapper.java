package cn.itbeien.task.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantAccountInfo;
import cn.itbeien.common.vo.merchant.MerchantAccountInfoVo;

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
public interface MerchantAccountInfoMapper {
    /**
     */
    int deleteByPrimaryKey(String id);

    /**
     */
    int insert(MerchantAccountInfo record);

    /**
     */
    int insertSelective(MerchantAccountInfo record);

    /**
     */
    MerchantAccountInfo selectByPrimaryKey(String id);
    
    List<MerchantAccountInfo> selectAll(MerchantAccountInfoVo record);

    /**
     */
    int updateByPrimaryKeySelective(MerchantAccountInfo record);

    /**
     */
    int updateByPrimaryKey(MerchantAccountInfo record);
    
    Long selectCount(Map<String, Object> map);
}