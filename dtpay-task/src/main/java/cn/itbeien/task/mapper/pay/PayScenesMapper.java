package cn.itbeien.task.mapper.pay;

import cn.itbeien.common.entity.pay.PayScenes;
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
public interface PayScenesMapper {
	boolean deleteByPrimaryKey(String sceneCode);

    boolean insert(PayScenes record);

    int insertSelective(PayScenes record);

    PayScenes selectByPrimaryKey(String sceneCode);

    boolean updateByPrimaryKeySelective(PayScenes record);

    int updateByPrimaryKey(PayScenes record);
    
    /**
     * 场景列表
     * @param param
     * @return
     * @throws DataAccessException
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);
	
	/**
	 * 根据名称查询是否已存在
	 * @param sceneCode
	 * @param sceneName
	 * @return
	 */
	List<String> selectByName(@Param("sceneCode") String sceneCode, @Param("sceneName") String sceneName);
	
	/**
	 * 根据编号查询是否已存在
	 * @param sceneCode
	 * @return
	 */
	List<String> selectByCode(@Param("sceneCode") String sceneCode);
}