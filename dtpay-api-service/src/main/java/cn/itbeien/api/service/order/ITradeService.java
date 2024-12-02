package cn.itbeien.api.service.order;

import cn.itbeien.api.vo.PaymentVO;

/**
 * @author beien
 * @date 2024-10-30 23:05
 * Copyright© 2024 beien
 * 交易类父接口，所有支付渠道对接的接口都必须实现此接口
 * pay,refund
 */
public interface ITradeService {
    public void prepay(PaymentVO paymentVO) throws Exception;
}
