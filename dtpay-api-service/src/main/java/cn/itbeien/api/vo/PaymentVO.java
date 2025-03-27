package cn.itbeien.api.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author beien
 * @date 2024-03-16 15:27
 * Copyright© 2024 beien
 */
@Data
@ToString
public class PaymentVO {
    /**
     * 支付金额 H5页面传入
     */
    private String amount;
    /**
     * 支付产品标识 后台获取
     */
    private String productCode;

    /**
     * 支付方式(场景) 后台获取
     */
    private String payType;

    /**
     * 交易码  后台获取
     * pay  支付
     * refund 退款
     */
    private String interfaceCode;
    /**
     * 二维码code H5页面
     */
    private String qrCode;
    /**
     * 支付产品标识
     */
    private String userAngent;
    /**
     * 商户编码
     */
    private String merchantCode;
    /**
     * 订单的备注
     */
    private String orderRemark;

    private String orderTime;
    /**
     * 终端类型
     */
    private String termType;

    private String payerId;
    /**
     * 支付渠道编号
     */
    String channelCode;

    /**
     * 渠道商户号
     */
    String payingMercNo;

    /**
     * 交易类型
     */
    String tradeType;
    /**
     * 商品名称
     */
    String goodsName;

    /**
     * 支付场景
     */

    String sceneCode;

}
