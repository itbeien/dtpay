package cn.itbeien.admin.service.common;

import cn.itbeien.common.entity.pay.PayScenes;
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
public interface IPayScenesService{
	
	/**
	 * 新增场景
	 * @param payway
	 * @return
	 */
	public boolean add(PayScenes payway);
	
	/**
	 * 场景列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param);
	
	/**
	 * 根据sceneCode查询实体
	 * @param sceneCode
	 * @return
	 */
	public PayScenes getBysceneCode(String sceneCode);

	/**
	 * 更新场景
	 * @param payway
	 * @return
	 */
	public boolean update(PayScenes payway);

	/**
	 * 根据名称查询是否已存在
	 * @param sceneName
	 * @return
	 */
	public List<String> selectByName(String sceneCode, String sceneName);
	
	/**
	 * 根据编号查询是否已存在
	 * @param sceneCode
	 * @return
	 */
	public List<String> selectByCode(String sceneCode);
	
	/**
	 * 删除场景
	 * @param sceneCode
	 * @return
	 */
	public boolean delete(String sceneCode);
	
	/**
	 * 启用or禁用
	 * @param param
	 * @return
	 */
	public boolean able(PayScenes param);
	
	/**
	 * 查询list
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getAllList(Map<String, Object> param);
	
}