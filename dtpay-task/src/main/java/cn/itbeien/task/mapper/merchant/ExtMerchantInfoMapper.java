package cn.itbeien.task.mapper.merchant;

import cn.itbeien.common.entity.merchant.ExtMerchantInfo;
import cn.itbeien.common.entity.merchant.ExtMerchantInfoExt;
import cn.itbeien.task.vo.merchant.ExtMerchantInfoQryPar;
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
public interface ExtMerchantInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExtMerchantInfo record);

    int insertSelective(ExtMerchantInfo record);

    ExtMerchantInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExtMerchantInfo record);

    int updateByPrimaryKey(ExtMerchantInfo record);
    
    List<ExtMerchantInfoExt> selectExtMerchantInfoExtList(ExtMerchantInfoQryPar record);
    
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
}