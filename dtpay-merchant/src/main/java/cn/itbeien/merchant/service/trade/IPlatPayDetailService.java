package cn.itbeien.merchant.service.trade;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.trade.PlatPayDetailQryPar;
import cn.itbeien.common.vo.trade.TradeOrderSeqReportVO;
import org.springframework.dao.DataAccessException;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IPlatPayDetailService {
	
	public BootTable<PlatPayDetail> getSettlementListByPage(PlatPayDetailQryPar param);

	/**
	 * 订单支付列表
	 * @param param
	 * @return
	 */
	public BootTable<PlatPayDetail> getPlatPayDetailList(PlatPayDetailQryPar param);
	
	/**
	 * 商户资金变动表
	 * @param param
	 * @return
	 */
	public BootTable<TradeOrderSeq> getMerchantCapitalChangeList(PlatPayDetailQryPar param);
	
	/**
	 * @Title: getSumAmtAndCount
	 * @Description: 根据条件汇总金额和笔数
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return: PlatPayDetailExt
	 */
	public TradeOrderSeqReportVO getpayForSumAmtAndCount(PlatPayDetailQryPar param) throws DataAccessException;
	
}
