package cn.itbeien.business.enums.merchant;

/**
 * @author beien
 * @date 2024-03-27 6:31
 * Copyright© 2024 beien
 * 图片类型
 */
public enum PicModeEnum {
    ID_CARD_FRONT("01","01-法人身份证人像面"),
    ID_CARD_BACK("02","02-法人身份证国徽面"),
    BUSINESS_LICENSE("05","05-营业执照"),
    CASHIER_DESK("06","06-收银台"),
    INTERNAL_MEDIUM_IMG("07","07-内部环境照"),
    PLACE_OF_BUSINESS_IMG("08","08-营业场所门头照"),
    HOUSE_NUMBER("09","09-门牌号"),
    PROTOCOL("10","10-协议（指线下签订的纸质协议）-非必填（使用电子协议时）")
    ;
    private String code;
    private String desc;

    PicModeEnum(String code,String desc){
        this.code =  code;
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
