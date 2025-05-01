package cn.itbeien.admin.mapper.merchant;

import cn.itbeien.admin.vo.merchant.MerchantInfoQryPar;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

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
public interface MerchantInfoMapper {
    int deleteByPrimaryKey(String mercNo);

    int insert(MerchantInfo record);

    int insertSelective(MerchantInfo record);

    MerchantInfo selectByPrimaryKey(String mercNo);

    int updateByPrimaryKeySelective(MerchantInfo record);

    int updateByPrimaryKey(MerchantInfo record);
    
    List<MerchantInfo> selectMerchantInfoList(MerchantInfoQryPar infoQryPar);
    
    List<MerchantInfo> selecSellerMercList(MerchantInfoQryPar infoQryPar);
    
    /**
     * qryProductsByMercNo:(根据商户号 查询 开通的产品列表).
     * @param infoQryPar
     * @return
     */
    List<MerchantInfo> qryProductsByMercNo(MerchantInfoQryPar infoQryPar);
    
    /**
     * 下游商户通道与上游关联列表
     * @param param
     * @return
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);
	
	public MerchantInfo qryMercProductInfoById(@Param("id") String id) throws DataAccessException;
	
	public List<MerchantInfo> qryBussConfigAndSettlement(Map map) throws DataAccessException;
	
	public List<MerchantInfo> qrySettlement(Map map) throws DataAccessException;
	
	public List<MerchantInfo> qryBussConfig(Map map) throws DataAccessException;
	
	public List<Map<String, Object>> qryMerchantChannelByPage(Map<String, Object> params);
	
	public List<Map<String, Object>> qryMerchantChannelDetail(@Param("params") Map<String, Object> params);
	
	public List<Map<String, Object>> toSetMerchantChannel(@Param("params") Map<String, Object> params);
	
	/**
	 * 获取下游商户列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getMerchatInfoList(@Param("params") Map<String, Object> params);
}