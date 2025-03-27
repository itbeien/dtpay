package cn.itbeien.payment.channel;

import cn.itbeien.common.core.vo.PayBackVO;
import cn.itbeien.common.core.vo.request.PayRequest;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 所有支付渠道类全部实现此接口,具体的支付渠道实现类放在cn/itbeien/payment/channel/xxpay/
 * Copyright© 2024 itbeien
 */
public interface IPayService {
    /**
     *
     * 方法用途: 发起支付渠道支付接口调用,内部处理普通商户和服务商模式
     * @param
     * @return PayBackBean 上游支付通道预支付接口返回数据，由具体支付渠道类把预支付返回结果适配为PayBackBean
     * <br>
     */
    public PayBackVO doPay(PayRequest payRequest);
}
