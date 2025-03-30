package cn.itbeien.common.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantAccessRight;

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
public interface MerchantAccessRightMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantAccessRight record);

    int insertSelective(MerchantAccessRight record);

    MerchantAccessRight selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantAccessRight record);

    int updateByPrimaryKey(MerchantAccessRight record);
    
    /**
     * 获取用户接口授权列表信息
     * @param param
     * @return
     */
    List<Map<String, Object>> getInterfaceList(Map<String, Object> param);
    
    /**
     * 根据商户和接口编码批量更新状态为启用
     * @param param
     * @return
     */
    int updateBatchStatus(Map<String, Object> param);
    
    /**
     * 获取接口授权状态
     * @param param
     * @return
     */
    List<Map<String, Object>> getMapByMercNoAndInterface(Map<String, Object> param);
    
}



