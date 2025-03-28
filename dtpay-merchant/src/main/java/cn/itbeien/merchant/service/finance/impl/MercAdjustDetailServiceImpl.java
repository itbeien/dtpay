package cn.itbeien.merchant.service.finance.impl;

import cn.itbeien.common.entity.merchant.MercAdjustDetail;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.merchant.MercAdjustDetailVO;
import cn.itbeien.merchant.mapper.finance.IMercAdjustDetailMapper;
import cn.itbeien.merchant.service.finance.IMercAdjustDetailService;
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
public class MercAdjustDetailServiceImpl implements IMercAdjustDetailService {
	
	private final IMercAdjustDetailMapper mercAdjustDetailMapper;

	@Override
	public BootTable<MercAdjustDetail> getMercAdjustDetailListByPage(MercAdjustDetailVO param) {
		List<MercAdjustDetail> list = mercAdjustDetailMapper.getMercAdjustDetailList(param);
		return new BootTable<MercAdjustDetail>(list);
	}



}
