package cn.itbeien.admin.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantChannelMapping;
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
public interface MerchantChannelMappingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantChannelMapping record);

    int insertSelective(MerchantChannelMapping record);

    MerchantChannelMapping selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantChannelMapping record);

    int updateByPrimaryKey(MerchantChannelMapping record);
    
    int updateByMercProIdSelective(MerchantChannelMapping record);
    
    int updateByMercNoSelective(MerchantChannelMapping record);
    
    /**
     * 根据mercNo查询payingMercNo
     * @param mercNo
     * @return
     */
	String getPayingMercNoByMercNo(String mercNo);

	/**
	 * 该上游商户产品通道下商户列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getMerchantList(@Param("param") Map<String, Object> param);
	
	/**
	 * 断开通道
	 * @param list
	 * @return
	 */
	int batchUpdateStatus(List<String> list);
	
	/**
	 * 断开通道清理缓存
	 * @param list
	 * @return
	 */
	List<Map<String, Object>> selectListByIds(List<String> list);
	
	int batchSwitch(Map<String, Object> param);
	
	int batchCheckStatus(Map<String, Object> params);
	
	/**
	 * 配置支付通道
	 * @param params
	 * @return
	 */
	int saveConfig(Map<String, Object> params);
	
	/**
	 * 查询是否已经配置上游支付通道,没有配置就可以配置,配置就提示
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getMerchantChannelMapping(Map<String, Object> params);
	
	List<MerchantChannelMapping> selectByProId(@Param("mercProId") String mercProId);
	
	/**
	 * 根据MERC_NO,PAYWAY_CODE,SCENE_CODE设置通道状态"0"
	 */
	int batchSetDefaultChannel(Map<String, Object> param);
	
	List<MerchantChannelMapping> qryMerchantChannelMappingList(Map<String, Object> params);
	
	List<MerchantChannelMapping> qryMerchantChannelMappingGroup(Map<String, Object> params);
	
	
}