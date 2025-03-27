package cn.itbeien.business.enums;

/**
 * @author beien
 * @date 2024-03-16 14:23
 * Copyright© 2024 beien
 * 支付产品枚举类
 */
public enum SystemEnum {

    SYSTEM_PARAMS_ERROR("10001","系统参数校验失败，不能为空"),
    SUCCESS("200","success"),
    ERROR("500","fail"),
    UPLOAD_IMG_NOT_EXIST("3001","文件不能为空"),
    USER_TYPE_SYSTEM("00","系统用户"),
    USER_TYPE_ORDINARY("01","普通用户"),
    DEFAULT_PASSWORD("888888","默认密码"),
    ORG_LEVEL_ONE("1","机构级别1"),
    ORG_LEVEL_TWO("2","机构级别2"),
    ORG_NODE_EXIST("1","有子节点"),
    ORG_NODE_NONE("0","没有子节点")
    ;
    /**
     * 支付产品
     * alipay
     * wechat
     * CloudPay
     */
    private String code;

    /**
     * 支付产品描述
     */
    private String message;

    SystemEnum(String code, String message){
        this.code =  code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
