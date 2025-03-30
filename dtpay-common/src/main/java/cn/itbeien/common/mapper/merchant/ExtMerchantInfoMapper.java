package cn.itbeien.common.mapper.merchant;

import cn.itbeien.common.entity.merchant.ExtMerchantInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExtMerchantInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExtMerchantInfo record);

    int insertSelective(ExtMerchantInfo record);

    ExtMerchantInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExtMerchantInfo record);

    int updateByPrimaryKey(ExtMerchantInfo record);
    /**
     * 配置商户通道列表查询
     * @param param
     * @return
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);
	
	/**
	 * 上游商户产品通道列表查询
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getUpperPoductChannelList(@Param("param") Map<String, Object> param);
	
	/**
	 * 上游商户产品通道基本详情查询
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getUpperPoductChannelDetail(@Param("param") Map<String, Object> param);

	/**
	 * 上游商户产品通道统计
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getUpperPoductChannelStatistics(@Param("param") Map<String, Object> param);
	
	/**
	 * 根据支付渠道编号获取渠道商户号
	 * @return
	 */
	List<Map<String, Object>> selectPayingMercNo(ExtMerchantInfo extMerchantInfo);
	
	/**
	 * 获取所有渠道商户信息表
	 * 方法用途: <br>
	 * @return
	 */
	List<ExtMerchantInfo>  selectAll();
}