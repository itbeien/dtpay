package cn.itbeien.admin.service.order;

import cn.itbeien.admin.vo.order.TradeOrderQryPar;
import cn.itbeien.common.entity.trade.TradeOrder;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface ITradeOrderService {
	
	public List<TradeOrder> qryTradeOrderByPage(TradeOrderQryPar param) throws DataAccessException;
	
	TradeOrder selectByPrimaryKey(String orderId) throws DataAccessException;
	
	public List<TradeOrder> qryTradeOrders(TradeOrderQryPar param) throws DataAccessException;

	Map<String, Object> sumAmtAndCount(TradeOrderQryPar param) throws DataAccessException;
	
}
