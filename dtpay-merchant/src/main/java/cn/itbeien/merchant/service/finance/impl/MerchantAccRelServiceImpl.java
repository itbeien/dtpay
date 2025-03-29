package cn.itbeien.merchant.service.finance.impl;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.util.uuid.UUID;
import cn.itbeien.common.vo.merchant.MerchantAccRelVO;
import cn.itbeien.merchant.mapper.finance.IMerchantAccRelMapper;
import cn.itbeien.merchant.mapper.trade.IPlatPayDetailMapper;
import cn.itbeien.merchant.service.finance.IMerchantAccRelService;
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
public class MerchantAccRelServiceImpl implements IMerchantAccRelService {
	
	private final IMerchantAccRelMapper merchantAccRelMapper;
	private final IPlatPayDetailMapper platPayDetailMapper;

	@Override
	public MerchantAccRelVO getMerchantAccRel(String mercNo) {
		return merchantAccRelMapper.getMerchantAccRel(mercNo);
	}
	
	@Override
	public MerchantAccRelVO getSettlementInfo(String mercNo) {
		return merchantAccRelMapper.getSettlementInfo(mercNo);
	}

	@Override
	public boolean settlementApply(PlatPayDetail platPayDetail) {
		// --获取结算信息
		MerchantAccRelVO settlementInfo = merchantAccRelMapper.getSettlementInfo(platPayDetail.getMercNo());
		// --保存结算信息到平台代付明细表
		String uuid = UUID.getUUID();
		platPayDetail.setRemark("商户后台接口实时提现");
		if (platPayDetailMapper.saveSettlementInfo(platPayDetail) > 0) {
			return true;
		}
		return false;
	}

}
