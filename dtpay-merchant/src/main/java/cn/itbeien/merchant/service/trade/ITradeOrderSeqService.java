package cn.itbeien.merchant.service.trade;

import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.trade.TradeOrderSeqReportVO;
import cn.itbeien.common.vo.trade.TradeOrderSeqVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface ITradeOrderSeqService {


	public BootTable<TradeOrderSeq> getTradeOrderSeqList(TradeOrderSeqVO param);
	
	
	List<TradeOrderSeq> getTradeOrderList(TradeOrderSeqVO param);


	TradeOrderSeqReportVO sumAmtAndCount(TradeOrderSeqVO param) throws DataAccessException;
	 
	 public List<TradeOrderSeq> selectTradeOrderSeqList(TradeOrderSeqVO param);
	
}
