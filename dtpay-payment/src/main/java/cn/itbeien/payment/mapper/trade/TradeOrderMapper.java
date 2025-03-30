package cn.itbeien.payment.mapper.trade;

import cn.itbeien.common.entity.trade.TradeOrder;
import org.apache.ibatis.annotations.Param;

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
    
    TradeOrder selectByMechOrderId(@Param("mechOrderId")String mechOrderId,@Param("orderId")String orderId);

    int updateByPrimaryKeySelective(TradeOrder record);

    int updateByPrimaryKey(TradeOrder record);
    
    //更新可退款状态
    int updateIsRefund(String orderId);


    TradeOrder getTotalAmount(TradeOrder param);

    TradeOrder getTodayTotalAmount(TradeOrder param);
}