package cn.itbeien.api.service.order;

import cn.itbeien.api.entity.order.TradeOrder;
import cn.itbeien.api.vo.CallBackResp;

/**
 * @author beien
 * @date 2024-03-29 15:37
 * Copyright© 2024 beien
 */
public interface IPayService {
    public int updateTradeInfo(CallBackResp callBackResp);

    public TradeOrder findTradeOrderByOrderId(String orderId);
}
