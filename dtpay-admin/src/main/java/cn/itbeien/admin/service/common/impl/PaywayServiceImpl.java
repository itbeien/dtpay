package cn.itbeien.admin.service.common.impl;

import cn.itbeien.admin.mapper.common.PaywayMapper;
import cn.itbeien.admin.service.common.IPaywayService;
import cn.itbeien.common.entity.pay.Payway;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
@Service
public class PaywayServiceImpl implements IPaywayService {

	@Autowired
	private PaywayMapper paywayMapper;

	public boolean add(Payway payway){
		return paywayMapper.insert(payway);
	}

	
	public List<Map<String, Object>> getList(Map<String, Object> param){
		PageHelper.startPage(Integer.parseInt(param.get("pageNumber").toString()), Integer.parseInt(param.get("pageSize").toString()));
		List<Map<String, Object>> list = paywayMapper.getList(param);
		return list;
	}
	
	public Payway getByPaywayCode(String paywayCode) throws DataAccessException {
		return paywayMapper.selectByPrimaryKey(paywayCode);
	}

	@Override
	public boolean update(Payway payway) {
		return paywayMapper.updateByPrimaryKeySelective(payway);
	}

	@Override
	public List<String> selectByPaywayName(String paywayCode, String paywayName) {
		return paywayMapper.selectByPaywayName(paywayCode, paywayName);
	}
	
	@Override
	public List<String> selectByPaywayCode(String paywayCode) {
		return paywayMapper.selectByPaywayCode(paywayCode);
	}

	@Override
	public boolean deleteByPrimaryKey(String paywayCode) {
		return paywayMapper.deleteByPrimaryKey(paywayCode) > 0;
	}
	
	@Override
	public List<Map<String, Object>> getAllList(Map<String, Object> param){
		List<Map<String, Object>> list = paywayMapper.getList(param);
		return list;
	}
	
}