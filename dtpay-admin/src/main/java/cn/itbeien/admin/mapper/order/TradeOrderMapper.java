package cn.itbeien.admin.mapper.order;

import cn.itbeien.admin.vo.order.TradeOrderQryPar;
import cn.itbeien.common.entity.trade.TradeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface TradeOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(TradeOrder record);

    int insertSelective(TradeOrder record);

    TradeOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);
    
    List<TradeOrder> qryTradeOrderByPage(TradeOrderQryPar param);
    
    List<TradeOrder> qrySellerTradeOrderByPage(TradeOrderQryPar param);
    
    List<TradeOrder> qryTradeOrders(TradeOrderQryPar param);
    
    List<Map<String, Object>> qryEveryHourOrders(@Param("currtdayDate") String currtdayDate) ;
    
    TradeOrder getTotalAmount(TradeOrderQryPar param);

    /**
     * 汇总订单总笔数及订单总金额
     * @param param
     * @return
     */
    Map<String, Object> sumAmtAndCount(TradeOrderQryPar param);
}