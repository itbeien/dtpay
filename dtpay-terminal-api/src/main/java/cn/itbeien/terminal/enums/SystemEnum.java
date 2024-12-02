package cn.itbeien.terminal.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public enum SystemEnum {

    SYSTEM_PARAMS_ERROR("10001","系统参数校验失败，不能为空"),
    SUCCESS("200","success"),
    ERROR("500","fail"),
    REG_MERCHANT("1001","商户进件异常，请联系管理员"),
    LOGIN_ERROR("1002","系统登录异常，请联系管理员"),
    BIND_DEVICE("1003","此设备已经绑定其他商户"),
    BIND_DEVICE_FAIL("1004","绑定设备失败"),
    BIND_DEVICE_SUCCESS("1005","绑定设备成功"),
    BIND_DEVICE_QUERY_SUCCESS("1006","获取绑定设备成功"),
    BIND_DEVICE_QUERY_ERROR("1007","获取绑设备失败"),
    MERCHANT_SIGNED_SUCCESS("success","商户电子签约成功"),
    MERCHANT_SIGNED_ERROR("1009","商户电子签约失败"),
    MERCHANT_SIGNED("1010","商户已经进件，请进行电子签约"),
    MERCHANT_REG("1011","请先完成商户进件"),
    BIND_DEVICE_ERROR("1012","还未创建商户,不能进行设备绑定"),
    SMS_ERROR("2001","验证码错误"),
    QUERY_MER_AUDIT("success","查询审核结果成功"),
    QUERY_MER_AUDIT_ERROR("error","查询审核结果失败"),
    QUERY_MER_NOT_AUDIT("error","还未进件不能进行审核结果查询"),
    YS_PAY_SUCCESS("0000","请求上游系统成功标识码"),
    YS_PAY_CALLBACKSUCCESS("000000","上游系统回调成功"),
    UPLOAD_IMG_NOT_EXIST("3001","文件不能为空"),
    D0("0","D0清算（前提：开通秒到功能）"),
    TOKEN_PARAMS_NULL("40001","token不能为空"),
    TOKEN_PARAMS_ERROR("40002","token不合法"),
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
