package cn.itbeien.business.order.domain.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-10 16:13
 * Copyright© 2024 beien
 */
@Data
public class ScanPay {
    /**
     * 支付成功后的结果通知地址
     */
    private String orgBackUrl;
    /**
     * 前台通知地址-云闪付-支付成功后的页面跳转地址
     */
    private String orgFrontUrl;
    /**
     * 按类型取值为：用户唯一标识。微信支付的appId(wxSubAppid)下的openId，参考文档，字段名：openid；
     * 支付宝：支付宝用户唯一标识 buyer_user_id，参考文档，字段名：user_id；
     * 银联：传银联JS-获取用户标识 返回的 payerId
     * 通过接口获取微信openid和银联js-获取用户标识
     */
    private String payerId;

    /**
     * WAJS1:支付宝-生活号支付;
     * WAJS2:支付宝-小程序支付;
     * WTJS1:微信-公众号支付;
     * WTJS2:微信-小程序支付;
     * WUJS1:银联二维码-JS支付
     */
    private String tradeCode;

    /**
     *金额,单位分；取值范围：1-10000000000,示例值(1)
     */
    private Long tradeAmt;
    /**
     * 订单标题，最长128字符
     */
    private String orderInfo;

    /**
     * 授权码（付款方返回的临时授权码，一次有效）
     */
    private String authCode;
    /**
     * 授权码（付款方返回的临时授权码，一次有效）
     */
    private String qrUserAuthCode;
    //wx:支付使用的appid
    private String wxSubAppid;
    /**
     * 银联支付标识
     */
    private String qrAppUpIdentifier;

    private  String orgSmercode;

    private String oriOrgTrace;
    private String oriOutTrace;

}
