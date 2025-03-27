package cn.itbeien.business.enums;

/**
 * @author beien
 * @date 2024-05-12 17:23
 * Copyright© 2024 beien
 */
public enum TradeEnums {
    REDUND_URL("ledger/mposrefund","退款"),
    REDUND_QUERY_URL("ledger/mposfindrefund","退款查询"),
    PAY_QUERY_URL("standard/tradeQuery","支付查询"),
    ;

    private String code;
    private String desc;

    TradeEnums(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
