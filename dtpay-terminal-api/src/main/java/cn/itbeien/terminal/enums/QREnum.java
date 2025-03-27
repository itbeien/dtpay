package cn.itbeien.terminal.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
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

    // 根据code获取中文描述
    public static String getNameByCode(String code) {
        for (QREnum qrEnum : QREnum.values()) {
            if (qrEnum.getCode().equals(code)) {
                return qrEnum.getName();
            }
        }
        return null; // 如果没有匹配的code，返回null或抛出异常
    }
}
