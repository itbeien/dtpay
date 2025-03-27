package cn.itbeien.common.core.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 支付方式枚举
 * Copyright© 2024 itbeien
 */
public enum PaywayCodeEnum {

    QR_AGGREGATED("QR_AGGREGATED","通过二维码跳转到聚合收银台完成支付"),
    AUTO_BAR("BAR_AGGREGATED","条码聚合支付（自动识别条码类型）"),
    ALI_BAR("ALI_BAR","支付宝条码支付"),
    ALI_JSAPI("ALI_JSAPI","支付宝服务窗支付"),
    ALI_MINI_PROGRAM("ALI_MINI_PROGRAM","支付宝小程序支付"),
    ALI_APP("ALI_APP","支付宝APP支付"),
    ALI_PC("ALI_PC","支付宝网站支付"),
    ALI_WAP("ALI_WAP","支付宝wap支付"),
    ALI_QR("ALI_QR","支付宝二维码支付"),
    WX_JSAPI("WX_JSAPI","微信jsapi支付"),
    WX_MINI_PROGRAM("WX_MINI_PROGRAM","微信小程序支付"),
    WX_BAR("WX_BAR","微信条码支付"),
    WX_H5("WX_H5","微信H5支付"),
    WX_NATIVE("WX_NATIVE","微信扫码支付"),
    YL_APP("YL_APP","银联APP支付"),
    YL_WAP("YL_WAP","银联手机网站支付"),
    YL_QR("YL_QR","银联二维码支付"),
    YL_PC("YL_PC","银联网关支付"),
    UP_JSAPI("UP_JSAPI","银联JS支付"),
    YSF_BAR("YSF_BAR","云闪付条码支付"),
    YSF_JSAPI("YSF_JSAPI","云闪付服务窗支付"),
    ;

    private String code;
    private String desc;
    PaywayCodeEnum(String code,String desc){
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
