package cn.itbeien.admin.service.system;

import cn.itbeien.admin.vo.system.TransBwListQryPar;
import cn.itbeien.common.entity.TransBwList;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface TransBwListService {
	
	/**
	 * @Title: addTransBwList
	 * @Description: TODO  增加
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public boolean addTransBwList(TransBwList param) throws DataAccessException;
	
	/**
	 * @Title: delTransBwList
	 * @Description: TODO  删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return: boolean
	 */
	public boolean delTransBwList(String id) throws DataAccessException;
	
	/**
	 * @Title: uptTransBwList
	 * @Description: TODO 修改
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: boolean
	 */
	public boolean uptTransBwList(TransBwList param) throws DataAccessException;
	
	/**
	 * @Title: qryTransBwLists
	 * @Description: TODO 分页查询List
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: List<TransBwList>
	 */
	public List<TransBwList> qryTransBwListsByPage(TransBwListQryPar param) throws DataAccessException ;
	
	/**
	 * @Title: qryTransBwList
	 * @Description: TODO 查询List
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: List<TransBwList>
	 */
	public List<TransBwList> qryTransBwLists(TransBwListQryPar param) throws DataAccessException;
	
	/**
	 * @Title: qryTransBwList
	 * @Description: TODO 根据id查询实体
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return: TransBwList
	 */
	public TransBwList qryTransBwListById(String id) throws DataAccessException;
	
}
