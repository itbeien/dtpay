package cn.itbeien.merchant.service.trade.impl;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.trade.PlatPayDetailQryPar;
import cn.itbeien.common.vo.trade.TradeOrderSeqReportVO;
import cn.itbeien.merchant.mapper.trade.IPlatPayDetailMapper;
import cn.itbeien.merchant.service.trade.IPlatPayDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PlatPayDetailServiceImpl implements IPlatPayDetailService {
	
	@Autowired
	private final IPlatPayDetailMapper platPayDetailMapper;

	@Override
	public BootTable<PlatPayDetail> getPlatPayDetailList(PlatPayDetailQryPar param) {
		List<PlatPayDetail> list = platPayDetailMapper.getPlatPayDetailList(param);
		return new BootTable<PlatPayDetail>(list);
	}

	@Override
	public BootTable<PlatPayDetail> getSettlementListByPage(PlatPayDetailQryPar param) {
		List<PlatPayDetail> list = platPayDetailMapper.getSettlementList(param);
		return new BootTable<PlatPayDetail>(list);
	}

	@Override
	public BootTable<TradeOrderSeq> getMerchantCapitalChangeList(PlatPayDetailQryPar param) {
		List<TradeOrderSeq> list = platPayDetailMapper.getMerchantCapitalChangeList(param);
		return new BootTable<TradeOrderSeq>(list);
	}
	
	public TradeOrderSeqReportVO getpayForSumAmtAndCount(PlatPayDetailQryPar param) throws DataAccessException {
		return platPayDetailMapper.getpayForSumAmtAndCount(param);
	}

}
