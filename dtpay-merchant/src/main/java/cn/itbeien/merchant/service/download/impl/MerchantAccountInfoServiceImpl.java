package cn.itbeien.merchant.service.download.impl;

import cn.itbeien.common.entity.merchant.MerchantAccountInfo;
import cn.itbeien.common.vo.merchant.MerchantAccountInfoVo;
import cn.itbeien.merchant.mapper.download.MerchantAccountInfoMapper;
import cn.itbeien.merchant.service.download.MerchantAccountInfoService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MerchantAccountInfoServiceImpl implements MerchantAccountInfoService {
	
	private final MerchantAccountInfoMapper merchantAccountInfoMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return merchantAccountInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MerchantAccountInfo record) {
		return merchantAccountInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(MerchantAccountInfo record) {
		return merchantAccountInfoMapper.insertSelective(record);
	}

	@Override
	public MerchantAccountInfo selectByPrimaryKey(String id) {
		return merchantAccountInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MerchantAccountInfo record) {
		return merchantAccountInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MerchantAccountInfo record) {
		return merchantAccountInfoMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<MerchantAccountInfo> qryMerchantAccountByPage(MerchantAccountInfoVo param) throws DataAccessException {
		List<MerchantAccountInfo> list = this.selectAll(param);
		return list;
	}

	@Override
	public List<MerchantAccountInfo> selectAll(MerchantAccountInfoVo record) {
		return merchantAccountInfoMapper.selectAll(record);
	}
	
	
}
