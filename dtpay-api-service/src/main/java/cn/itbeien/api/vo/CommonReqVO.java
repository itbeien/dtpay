package cn.itbeien.api.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-10 15:39
 * Copyright© 2024 beien
 */
@Data
public class CommonReqVO {
    /**
     *客户编号
     */
    private String orgId;
    /**
     * 交易商户号
     */
    private String orgMercode;
    /**
     *交易终端号
     */
    private String orgTermno;
    /**
     * 交易流水，35位不定长，
     * 建议按照如下规则：orgId
     * 前四位+后四位+yyyyMMddhhmmss+自定义 （需要保证唯一）
     */
    private String orgTrace;
    /**
     * 签名
     */
    private String sign;
    /**
     *签名方式
     */
    private String signType;

}
