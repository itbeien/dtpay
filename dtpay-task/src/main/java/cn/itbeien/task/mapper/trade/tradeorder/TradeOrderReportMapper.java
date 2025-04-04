package cn.itbeien.task.mapper.trade.tradeorder;

import cn.itbeien.common.entity.trade.TradeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TradeOrderReportMapper {
	
    List<TradeOrder> qryTradeOrderRepors(Map<String, Object> param);
    
    /*
     * 批量更新 标识
     */
    boolean batchUpdateFlag(@Param("list") List<String> list);
}