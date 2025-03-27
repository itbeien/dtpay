package cn.itbeien.api.enums;

/**
 * @author beien
 * @date 2024-03-16 14:23
 * Copyright© 2024 beien
 * 交易类型
 */
public enum TradeTypeEnum {

    PAY("01","支付")
    ;
    /**
     * 01 支付
     */
    private String payType;

    /**
     * 支付类型描述
     */
    private String payDesc;

    TradeTypeEnum(String payType, String payDesc){
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
