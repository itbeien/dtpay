package cn.itbeien.task.mapper.merchant;

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
}