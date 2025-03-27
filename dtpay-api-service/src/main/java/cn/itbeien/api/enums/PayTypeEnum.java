package cn.itbeien.api.enums;

/**
 * @author beien
 * @date 2024-03-16 14:23
 * Copyright© 2024 beien
 * 支付类型
 */
public enum PayTypeEnum {

    AliPay("WAJS1","支付宝-生活号支付"),
    WechatPay("WTJS1","微信-公众号支付"),

    UnionPay("WUJS1","银联二维码-JS支付");
    /**
     * 支付类型
     * WAJS1:支付宝-生活号支付;
     * WTJS1:微信-公众号支付;
     * WUJS1:银联二维码-JS支付
     */
    private String payType;

    /**
     * 支付类型描述
     */
    private String payDesc;

    PayTypeEnum(String payType, String payDesc){
        this.payType =  payType;
        this.payDesc = payDesc;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }
}
