package cn.itbeien.admin.service.system.impl;

import cn.itbeien.admin.mapper.system.TransBwListMapper;
import cn.itbeien.admin.service.system.TransBwListService;
import cn.itbeien.admin.vo.system.TransBwListQryPar;
import cn.itbeien.common.entity.TransBwList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Service
public class TransBwListServiceImpl implements TransBwListService {
	
	
	@Autowired
	private TransBwListMapper transBwListMapper;
	
	@Override
	public boolean addTransBwList(TransBwList param) throws DataAccessException {
		int result = transBwListMapper.insert(param);
		
		return result>0;
	}

	@Override
	public boolean delTransBwList(String id) throws DataAccessException {
		int result = transBwListMapper.deleteByPrimaryKey(id);
		
		return result>0;
	}

	@Override
	public boolean uptTransBwList(TransBwList param) throws DataAccessException{
		int result = transBwListMapper.updateByPrimaryKeySelective(param);
		return result>0;
	}

	@Override
	public List<TransBwList> qryTransBwListsByPage(TransBwListQryPar param) throws DataAccessException {
		List<TransBwList> list = this.qryTransBwLists(param);
		return list;
	}

	@Override
	public List<TransBwList> qryTransBwLists(TransBwListQryPar param) throws DataAccessException {
		return transBwListMapper.selectTransBwLists(param);
	}

	@Override
	public TransBwList qryTransBwListById(String id) throws DataAccessException {
		return transBwListMapper.selectByPrimaryKey(id);
	}

}
