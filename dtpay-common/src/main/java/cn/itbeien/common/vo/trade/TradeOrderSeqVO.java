package cn.itbeien.common.vo.trade;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Data
public class TradeOrderSeqVO implements Serializable {
    private String tradeSeq;

    private String orderId;

    private String mercNo;

    private String mercOrderNo;

    private String tradeType;

    private String subject;

    private String body;

    private String bankTradeSeq;

    private String orderIp;

    private BigDecimal orderAmount;

    private String channelCode;

    private Date completeTime;

    private String status;

    private String retCode;

    private String retDesc;

    private String respCode;

    private String respDesc;

    private String paywayCode;

    private String remark;

    private String payingMercNo;

    private String payingMercName;

    private String connectSwitch;

    private Date createTime;

    private BigDecimal feeValue;

    private String sceneCode;

    private String  startDate;

    private String  endDate;
}