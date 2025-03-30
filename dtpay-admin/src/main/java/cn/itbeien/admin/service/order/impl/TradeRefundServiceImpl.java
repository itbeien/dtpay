package cn.itbeien.admin.service.order.impl;

import cn.itbeien.admin.mapper.order.TradeRefundMapper;
import cn.itbeien.admin.service.order.ITradeRefundService;
import cn.itbeien.admin.vo.order.TradeRefundQryPar;
import cn.itbeien.common.entity.trade.TradeRefund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeRefundServiceImpl implements ITradeRefundService {
	
	@Autowired
	private TradeRefundMapper tradeRefundMapper;
	

	@Override
	public List<TradeRefund> qryTradeRefundByPage(TradeRefundQryPar param) throws DataAccessException {
		List<TradeRefund> tradeRefunds = null;
		// 说明是商务
		if("1".equals(param.getQryType())) {
			tradeRefunds = tradeRefundMapper.qrySellerTradeRefundByPage(param);
		} else {
			tradeRefunds = tradeRefundMapper.qryTradeRefundByPage(param);
		}
		
		return tradeRefunds;
	}


	@Override
	public TradeRefund selectByPrimaryKey(String refundOrderId) throws DataAccessException {
		TradeRefund tradeRefund = this.tradeRefundMapper.selectByPrimaryKey(refundOrderId);
		return tradeRefund;
	}
	
}
