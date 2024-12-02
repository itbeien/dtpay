package cn.itbeien.business.enums;

/**
 * @author beien
 * @date 2024-03-16 14:23
 * Copyright© 2024 beien
 * 支付产品枚举类
 */
public enum QREnum {

    SYSTEM("10000","system"),
    STATUS_ENABLE("0","启用"),
    STATUS_STOP("1","停用")
    ;

    private String code;


    private String name;

    QREnum(String code, String name){
        this.code =  code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
