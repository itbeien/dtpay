package cn.itbeien.admin.service.common;

import cn.itbeien.common.entity.pay.Payway;
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
public interface IPaywayService{
	
	/**
	 * 新增支付方式
	 * @param payway
	 * @return
	 */
	public boolean add(Payway payway);
	
	/**
	 * 支付方式列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param);
	
	/**
	 * 根据paywayCode查询实体
	 * @param paywayCode
	 * @return
	 */
	public Payway getByPaywayCode(String paywayCode);

	/**
	 * 更新支付方式
	 * @param payway
	 * @return
	 */
	public boolean update(Payway payway);

	/**
	 * 根据名称查询是否已存在
	 * @param paywayName
	 * @return
	 */
	public List<String> selectByPaywayName(String paywayCode, String paywayName);
	
	/**
	 * 根据编号查询是否已存在
	 * @param paywayCode
	 * @return
	 */
	public List<String> selectByPaywayCode(String paywayCode);
	
	/**
	 * 删除
	 * @param paywayCode
	 * @return
	 */
	public boolean deleteByPrimaryKey(String paywayCode);
	
	/**
	 * 查询所有
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getAllList(Map<String, Object> param);
}