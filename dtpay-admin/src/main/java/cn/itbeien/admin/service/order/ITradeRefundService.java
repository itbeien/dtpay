package cn.itbeien.admin.service.order;

import cn.itbeien.admin.vo.order.TradeRefundQryPar;
import cn.itbeien.common.entity.trade.TradeRefund;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ITradeRefundService {
	
	public List<TradeRefund> qryTradeRefundByPage(TradeRefundQryPar param) throws DataAccessException;

	TradeRefund selectByPrimaryKey(String refundOrderId)throws DataAccessException;
}
