package cn.itbeien.merchant.service.common.impl;

import cn.itbeien.common.entity.merchant.CusLoginRecord;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.merchant.mapper.common.IUserLoginRecordMapper;
import cn.itbeien.merchant.service.common.IUserLoginRecordService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserLoginRecordServiceImpl implements IUserLoginRecordService {
	
	private final IUserLoginRecordMapper userLoginRecordMapper;

	@Override
	public boolean insertRecordOne(CusLoginRecord param) {
		return userLoginRecordMapper.insertRecordOne(param) > 0;
	}

	@Override
	public BootTable<CusLoginRecord> getUserLoginRecordList(CusLoginRecord param) {
		List<CusLoginRecord> list = userLoginRecordMapper.getUserLoginRecordList(param);
		return new BootTable<CusLoginRecord>(list);
	}
	

}
