package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 23:26
 * Copyright© 2024 beien
 * 商户信息录入（进件）
 */
@Data
public class ChannelMerchantInfo {
    /***
     * 版本号
     */
    private String version;
    /**
     * 报文类型
     */
    private String messageType;
    /**
     * 机构号
     */
    private String clientCode;

    private String MAC;
    /**
     * 商户信息
     */
    private MerInfo merInfo;
    /**
     * 附加信息
     */
    private PlusInfo plusInfo;
    /**
     * 营业执照信息
     */
    private LicInfo licInfo;

    /**
     * 结算账户信息
     */
    private AccInfo accInfo;

    private FuncMessage funcInfo;

    private PicInfo picInfoList;
    /**
     * 操作流水
     */
    private String operaTrace;
    /**
     * 返回码
     */
    private String retCode;
    /**
     * 错误描述
     */
    private String retMsg;
    /**
     * 商户唯一标识
     */
    private String merTrace;
}
