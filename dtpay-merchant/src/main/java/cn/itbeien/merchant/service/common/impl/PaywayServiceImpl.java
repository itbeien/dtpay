package cn.itbeien.merchant.service.common.impl;

import cn.itbeien.common.entity.pay.Payway;
import cn.itbeien.merchant.mapper.common.IPaywayMapper;
import cn.itbeien.merchant.service.common.IPaywayService;
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
public class PaywayServiceImpl implements IPaywayService {
	
	private final IPaywayMapper paywayMapper;

	@Override
	public List<Payway> getList() {
		return paywayMapper.getList();
	}

}
