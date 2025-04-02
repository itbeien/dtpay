package cn.itbeien.admin.service.common.impl;

import cn.itbeien.admin.mapper.common.PayScenesMapper;
import cn.itbeien.admin.service.common.IPayScenesService;
import cn.itbeien.common.entity.pay.PayScenes;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PayScenesServiceImpl implements IPayScenesService {

	@Autowired
	private PayScenesMapper payScenesMapper;

	public boolean add(PayScenes payScenes){
		return payScenesMapper.insert(payScenes);
	}
	
	public List<Map<String, Object>> getList(Map<String, Object> param){
		List<Map<String, Object>> list = payScenesMapper.getList(param);
		return list;
	}
	
	public PayScenes getBysceneCode(String sceneCode) {
		return payScenesMapper.selectByPrimaryKey(sceneCode);
	}

	@Override
	public boolean update(PayScenes payScenes) {
		return payScenesMapper.updateByPrimaryKeySelective(payScenes);
	}

	@Override
	public List<String> selectByName(String sceneCode, String sceneName) {
		return payScenesMapper.selectByName(sceneCode, sceneName);
	}
	
	@Override
	public List<String> selectByCode(String sceneCode) {
		return payScenesMapper.selectByCode(sceneCode);
	}
	
	public boolean delete(String sceneCode){
		return payScenesMapper.deleteByPrimaryKey(sceneCode);
	}
	
	public boolean able(PayScenes param){
		return payScenesMapper.updateByPrimaryKeySelective(param);
	}
	
	public List<Map<String, Object>> getAllList(Map<String, Object> param){
		return payScenesMapper.getList(param);
	}
}