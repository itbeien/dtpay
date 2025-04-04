package cn.itbeien.task.mapper.trade.tradeorder;

import cn.itbeien.common.entity.trade.TradeRefund;
import cn.itbeien.task.vo.trade.TradeRefundQryPar;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface TradeRefundMapper {
    int deleteByPrimaryKey(String refundOrderId);

    int insert(TradeRefund record);

    int insertSelective(TradeRefund record);

    TradeRefund selectByPrimaryKey(String refundOrderId);

    int updateByPrimaryKeySelective(TradeRefund record);

    int updateByPrimaryKey(TradeRefund record);
    
    List<TradeRefund> qryTradeRefundByPage(TradeRefundQryPar param);
}