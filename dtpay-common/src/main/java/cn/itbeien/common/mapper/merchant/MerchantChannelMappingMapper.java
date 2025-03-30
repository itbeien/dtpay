package cn.itbeien.common.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantChannelMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface MerchantChannelMappingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantChannelMapping record);

    int insertSelective(MerchantChannelMapping record);

    MerchantChannelMapping selectByPrimaryKey(String id);
    /**
     * 
     * 方法用途: <br>
     * @param defaultFlag 支付通道默认标识 0非默认  1默认通道
     * @return
     */
    List<MerchantChannelMapping> selectAll(@Param("defaultFlag")String defaultFlag);

    int updateByPrimaryKeySelective(MerchantChannelMapping record);

    int updateByPrimaryKey(MerchantChannelMapping record);
    
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
	
	int batchUpdateStatus(List<String> list);
	
	int batchSwitch(Map<String, Object> param);
	
	int batchCheckStatus(Map<String, Object> params);
	
	/**
	 * 配置支付通道
	 * @param params
	 * @return
	 */
	int saveConfig(Map<String, Object> params);
}