package cn.itbeien.admin.mapper.order;

import cn.itbeien.common.entity.trade.TradeRefundSeq;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TradeRefundSeqMapper {
    int deleteByPrimaryKey(String refundTradeSeq);

    int insert(TradeRefundSeq record);

    int insertSelective(TradeRefundSeq record);

    int updateByPrimaryKeySelective(TradeRefundSeq record);

    int updateByPrimaryKey(TradeRefundSeq record);
    
    /**
     * 退款订单流水列表查询
     * @param param
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);

	/**
	 * 根据退款流水号查询实体
	 * @param refundTradeSeq 退款流水号
	 */
	public TradeRefundSeq selectByPrimaryKey(@Param("refundTradeSeq") String refundTradeSeq);
}