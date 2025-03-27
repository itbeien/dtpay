package cn.itbeien.payment.dao;


import cn.itbeien.payment.entity.MerchantAccessRight;

import java.util.List;
import java.util.Map;

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



