
  
package cn.itbeien.merchant.service.trade.impl;

import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.merchant.mapper.trade.ITradeOrderMapper;
import cn.itbeien.merchant.service.trade.ITradeOrderService;
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
public class TradeOrderServiceImpl implements ITradeOrderService {

	private final ITradeOrderMapper tradeOrderMapper;

	@Override
	public TradeOrder selectByPrimaryKey(String orderId) {
		return tradeOrderMapper.selectByPrimaryKey(orderId);
	}
	
}
  
