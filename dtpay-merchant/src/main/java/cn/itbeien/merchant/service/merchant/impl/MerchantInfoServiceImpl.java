package cn.itbeien.merchant.service.merchant.impl;

import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.merchant.mapper.merchant.IMerchantInfoMapper;
import cn.itbeien.merchant.service.merchant.IMerchantInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
public class MerchantInfoServiceImpl implements IMerchantInfoService {

	private final IMerchantInfoMapper merchantInfoMapper;

	@Override
	public MerchantInfo  getMerchantInfo(String mercNo) {
		MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfo(mercNo);
		return merchantInfo;
	}

	@Override
	public MerchantInfo getPasswd(String mercNo) {
		MerchantInfo merchantInfo = merchantInfoMapper.getPasswd(mercNo);
		return merchantInfo;
	}

	@Override
	public boolean updatePasswd(String mercNo) {
		return merchantInfoMapper.updatePasswd(mercNo) > 0;
	}

	@Override
	public MerchantInfo getMerchantInfoByUserId(String userId) {
		return merchantInfoMapper.getMerchantInfoByUserId(userId);
	}

	@Override
	public MerchantInfo getMerchantInfoByMercNo(String mercNo) {
		return merchantInfoMapper.selectByPrimaryKey(mercNo);
	}

}
