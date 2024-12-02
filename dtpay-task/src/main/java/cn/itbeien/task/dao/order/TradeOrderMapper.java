package cn.itbeien.task.dao.order;

import cn.itbeien.task.entity.AgentReportDetail;
import cn.itbeien.task.entity.MerchantReport;
import cn.itbeien.task.entity.TradeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeOrderMapper {

    int insert(TradeOrder row);

    int insertSelective(TradeOrder row);

    int updateByPrimaryKeySelective(TradeOrder row);

    int updateByPrimaryKey(TradeOrder row);

    int updateTradeOrderByOrderId(@Param("payStatus") String payStatus,@Param("orderId") String orderId);

    TradeOrder selectTradeOrderByPrimaryKey(String orderId);

    List<AgentReportDetail> selectTradeOrderByAgentId(@Param("agentId") Long agentId, @Param("agentRate") Double rate);

    List<MerchantReport> selectTradeOrderByMerchantNo(String merchantNo);
}