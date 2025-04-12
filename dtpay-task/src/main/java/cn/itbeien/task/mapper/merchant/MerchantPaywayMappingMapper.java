package cn.itbeien.task.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;
import cn.itbeien.task.vo.merchant.MerchantPaywayMappingQryPar;

import java.util.List;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface MerchantPaywayMappingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantPaywayMapping record);

    int insertSelective(MerchantPaywayMapping record);

    MerchantPaywayMapping selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantPaywayMapping record);

    int updateByPrimaryKey(MerchantPaywayMapping record);
    
    List<MerchantPaywayMapping> selectMerchantPaywayMappingList(MerchantPaywayMappingQryPar infoQryPar);
}