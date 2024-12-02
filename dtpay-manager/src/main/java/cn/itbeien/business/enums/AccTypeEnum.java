package cn.itbeien.business.enums;

/**
 * @author beien
 * @date 2024-04-24 21:15
 * Copyright© 2024 beien
 * 账户类型：00-个人(对私)；10-对公
 */
public enum AccTypeEnum {
    //账户类型：00-个人(对私)；10-对公
    ENTERPRISE("10","对公"),
    PERSON("00","个人(对私)")
    ;
    private String code;
    private String desc;

    AccTypeEnum(String code,String desc){
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
