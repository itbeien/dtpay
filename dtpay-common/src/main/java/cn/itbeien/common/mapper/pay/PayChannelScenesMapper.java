package cn.itbeien.common.mapper.pay;

import cn.itbeien.common.entity.pay.PayChannelScenes;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface PayChannelScenesMapper {
    int deleteByPrimaryKey(String id);

    boolean insert(PayChannelScenes record);

    int insertSelective(PayChannelScenes record);

    PayChannelScenes selectByPrimaryKey(String id);
    
    List<PayChannelScenes> selectAll();

    boolean updateByPrimaryKeySelective(PayChannelScenes record);

    int updateByPrimaryKey(PayChannelScenes record);
    
    /**
     * 支付场景列表
     * @param param
     * @return
     * @throws DataAccessException
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);

	/**
	 * 查询所有的产品类型
	 * @return
	 */
	List<Map<String, Object>> getPayway();
	
	/**
	 * 查询所有的产品名称
	 * @return
	 */
	List<String> getScene();

	/**
	 * 根据支付渠道编号、支付方式编号、场景编号判断此支付场景是否已存在
	 * @param payScenes
	 * @return
	 */
	List<String> selectByName(PayChannelScenes payScenes);
	
	/**
	 * 根据支付方式编号、场景编号获取机构下拉列表
	 * @param payScenes
	 * @return
	 */
	List<Map<String, Object>> selectOrg(PayChannelScenes payScenes);
}