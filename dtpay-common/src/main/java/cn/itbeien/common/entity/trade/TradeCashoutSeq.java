package cn.itbeien.common.entity.trade;

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
public class TradeCashoutSeq implements Serializable {
    private String tradeSeqNo;

    private String orderId;

    private String mercNo;

    private String mercOrderNo;

    private String acctType;

    private String toAcctNo;

    private String toAcctName;

    private String toAcctType;

    private String toAcctBank;

    private BigDecimal tradeAmt;

    private Date orderTime;

    private String subject;

    private String body;

    private String feeType;

    private String termType;

    private String status;

    private Date createTime;

    private String channelCode;

    private String respCode;

    private String respDesc;

    private static final long serialVersionUID = 1L;

    public String getTradeSeqNo() {
        return tradeSeqNo;
    }

    public void setTradeSeqNo(String tradeSeqNo) {
        this.tradeSeqNo = tradeSeqNo == null ? null : tradeSeqNo.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getMercOrderNo() {
        return mercOrderNo;
    }

    public void setMercOrderNo(String mercOrderNo) {
        this.mercOrderNo = mercOrderNo == null ? null : mercOrderNo.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getToAcctNo() {
        return toAcctNo;
    }

    public void setToAcctNo(String toAcctNo) {
        this.toAcctNo = toAcctNo == null ? null : toAcctNo.trim();
    }

    public String getToAcctName() {
        return toAcctName;
    }

    public void setToAcctName(String toAcctName) {
        this.toAcctName = toAcctName == null ? null : toAcctName.trim();
    }

    public String getToAcctType() {
        return toAcctType;
    }

    public void setToAcctType(String toAcctType) {
        this.toAcctType = toAcctType == null ? null : toAcctType.trim();
    }

    public String getToAcctBank() {
        return toAcctBank;
    }

    public void setToAcctBank(String toAcctBank) {
        this.toAcctBank = toAcctBank == null ? null : toAcctBank.trim();
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode == null ? null : respCode.trim();
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc == null ? null : respDesc.trim();
    }
}