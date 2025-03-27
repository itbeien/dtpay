package cn.itbeien.business.enums;

/**
 * @author beien
 * @date 2024-04-24 20:19
 * Copyright© 2024 beien
 * 商户类型枚举
 */
public enum MerModeEnum {
    //商户类型（0-企业 1-个体户 2-个人）
    ENTERPRISE("0","企业"),
    SELF_EMPLOYED("1","个体户"),
    PERSON("2","个人")
    ;
    private String code;
    private String desc;

    MerModeEnum(String code,String desc){
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
