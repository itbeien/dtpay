package cn.itbeien.business.order.domain.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-25 16:09
 * Copyright© 2024 beien
 */
@Data
public class RefundReqBizData {
    /**
     * 原机构交易流水号
     * 与原交易商户流水号必须传一个，或者两个都传，首选用此单号
     */
    private String oriOrgTrace;

    /**
     * 退货金额
     */
    private String transAmt;

}
