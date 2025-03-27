package cn.itbeien.common.core.enums;

public enum ApiEnum {
    PARAMS_ERROR(11, "参数有误[%s]"),
    E50017(50017, "接口未授权"),
    ;

    private int code;

    private String msg;

    ApiEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
