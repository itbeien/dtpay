package cn.itbeien.admin.service.system;


import cn.itbeien.common.vo.IpWhiteListQryPar;
import cn.itbeien.common.entity.IpWhiteList;
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
public interface IpWhiteListService {
	
	/**
	 * @Title: addIpWhiteList
	 * @Description: TODO  增加
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public boolean addIpWhiteList(IpWhiteList param) throws DataAccessException;
	
	/**
	 * @Title: delIpWhiteList
	 * @Description: TODO  删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return: boolean
	 */
	public boolean delIpWhiteList(String id) throws DataAccessException;
	
	/**
	 * @Title: uptIpWhiteList
	 * @Description: TODO 修改
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: boolean
	 */
	public boolean uptIpWhiteList(IpWhiteList param) throws DataAccessException;
	
	/**
	 * @Title: qryIpWhiteLists
	 * @Description: TODO 分页查询List
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: List<IpWhiteList>
	 */
	public List<IpWhiteList> qryIpWhiteListsByPage(IpWhiteListQryPar param) throws DataAccessException ;
	
	/**
	 * @Title: qryIpWhiteList
	 * @Description: TODO 查询List
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: List<IpWhiteList>
	 */
	public List<IpWhiteList> qryIpWhiteLists(IpWhiteListQryPar param) throws DataAccessException;
	
	/**
	 * @Title: qryIpWhiteList
	 * @Description: TODO 根据id查询实体
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return: IpWhiteList
	 */
	public IpWhiteList qryIpWhiteListById(String id) throws DataAccessException;
	
}
