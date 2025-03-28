package cn.itbeien.merchant.service.finance.impl;


import cn.itbeien.common.entity.finance.MerchantChannelBal;
import cn.itbeien.common.entity.finance.MerchantChannelBalExt;
import cn.itbeien.common.entity.finance.MerchantChannelBalKey;
import cn.itbeien.merchant.mapper.finance.IMerchantChannelBalMapper;
import cn.itbeien.merchant.service.finance.IMerchantChannelBalService;
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
public class MerchantChannelBalServiceImpl implements IMerchantChannelBalService {
	
	private final IMerchantChannelBalMapper merchantChannelBalMapper;

	@Override
	public int deleteByPrimaryKey(MerchantChannelBalKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MerchantChannelBal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(MerchantChannelBal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MerchantChannelBal selectByPrimaryKey(MerchantChannelBalKey key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MerchantChannelBal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MerchantChannelBal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MerchantChannelBalExt> selectMerchantChannelBalExtList(MerchantChannelBal record) {
		List<MerchantChannelBalExt> list = merchantChannelBalMapper.selectMerchantChannelBalExtList(record);
		return list;
	}

	@Override
	public int updateFreezeAmtByPrimaryKey(MerchantChannelBal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MerchantChannelBalExt> selectNoPayForMerchantChannelBalExtList(MerchantChannelBal record) {
		List<MerchantChannelBalExt> list = merchantChannelBalMapper.selectNoPayForMerchantChannelBalExtList(record);
		return list;
	}

	@Override
	public int batchUpdateAvaiBal(List<MerchantChannelBalExt> chooseChns) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}