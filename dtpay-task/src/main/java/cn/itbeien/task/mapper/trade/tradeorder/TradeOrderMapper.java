package cn.itbeien.task.mapper.trade.tradeorder;

import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.task.vo.trade.TradeOrderQryPar;

import java.util.List;
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
    
    
    List<TradeOrder> qryTradeOrders(TradeOrderQryPar param);
}