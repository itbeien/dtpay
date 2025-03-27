package cn.itbeien.terminal.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public enum BankHbdm {
    ZGYH("104","中国银行"),
    NYYH("103","中国农业银行"),
    GSYH("102","中国工商银行"),
    JSYH("105","中国建设银行"),
    ZSYH("308","招商银行"),
    MSYH("305","中国民生银行"),
    JTYH("301","交通银行"),
    PFYH("310","浦发银行")
    ;
    private String code;
    private String desc;

    BankHbdm(String code, String desc){
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
