package cn.itbeien.terminal.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public enum MerchantEnum {
    /**
     * '状态  00-待审核  01-已通过  02-驳回   03-启用 04-停用
     */
    STATE_AUDIT("00","待审核"),
    STATE_PASS("01","已通过"),
    STATE_NO_PASS("02","驳回"),
    STATE_ENABLE("03","启用"),
    STATE_DISABLE("04","停用"),
    BUSIN_FORM_01("01","连锁店"),
    BUSIN_FORM_02("02","普通店"),
    EMPLOYEE_NUM_1("1","0-50人"),
    EMPLOYEE_NUM_2("2","50-100人"),
    EMPLOYEE_NUM_3("3","100以上")
    ;

    private String code;
    private String desc;

    MerchantEnum(String code, String desc){
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
