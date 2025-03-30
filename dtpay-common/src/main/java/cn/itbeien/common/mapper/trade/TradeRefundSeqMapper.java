package cn.itbeien.common.mapper.trade;

import cn.itbeien.common.entity.trade.TradeRefundSeq;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface TradeRefundSeqMapper {
    int deleteByPrimaryKey(String refundTradeSeq);

    int insert(TradeRefundSeq record);

    int insertSelective(TradeRefundSeq record);

//    TradeRefundSeq selectByPrimaryKey(String refundTradeSeq);

    int updateByPrimaryKeySelective(TradeRefundSeq record);

    int updateByPrimaryKey(TradeRefundSeq record);
    
    /**
     * 退款订单流水列表查询
     * @param param
     * @return
     * @throws DataAccessException
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);

	/**
	 * 根据退款流水号查询实体
	 * @param refundTradeSeq 退款流水号
	 * @return
	 * @throws DataAccessException
	 */
	public TradeRefundSeq selectByPrimaryKey(@Param("refundTradeSeq") String refundTradeSeq);
	
	/**
	 * 根据商户号、平台订单号或商户订单号查询订单流水
	 * @param map 
	 * @return
	 * @throws DataAccessException
	 */
	TradeRefundSeq selectByMap(Map<String,Object> map);
}