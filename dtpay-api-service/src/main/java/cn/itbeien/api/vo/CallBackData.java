package cn.itbeien.api.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-25 12:11
 * Copyright© 2024 beien
 */
@Data
public class CallBackData {
    private String finRetcode;
    private String appendRetcode;
    private String appendRetmsg;
    private String tradeCode;
    private String outCdnoType;
    private String pcTrace;
    private String outTrace;
    private String payerId;
    private String payerOtherInfo;
    private String settleDate;
    private String timeEnd;
    private String  settleAmt;
    private String payerAmt;
    private String patnerSettleFlag;
    private String tradeAmt;
    private String oriOrgTrace;
}
