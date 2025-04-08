package cn.itbeien.task.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.task.vo.merchant.MerchantInfoQryPar;
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
    
    /**
     * 查询所有商户信息
     * 方法用途: <br>
     * @return
     */
    List<String> selectList();
    
    /**
     * 根据商户号 查询 开通的产品列表
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
	
	@SuppressWarnings("rawtypes")
	public List<MerchantInfo> qrySettlement(Map map) throws DataAccessException;
	
	@SuppressWarnings("rawtypes")
	public List<MerchantInfo> qryBussConfig(Map map) throws DataAccessException;
	
}