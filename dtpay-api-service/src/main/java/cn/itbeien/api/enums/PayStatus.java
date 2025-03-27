package cn.itbeien.api.enums;

/**
 * @author beien
 * @date 2024-03-19 14:58
 * Copyright© 2024 beien
 * p0002:待支付, p0000:支付成功,
 * p0004:支付失败, p0005:已取消,
 */
public enum PayStatus {
    pay_paying("p0002","待支付"),
    pay_success("p0000","支付成功"),
    pay_fail("p0004","支付失败"),
    pay_canal("p0005","已关闭")
    ;

    private String code;
    private String desc;

    PayStatus(String code, String desc){
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
