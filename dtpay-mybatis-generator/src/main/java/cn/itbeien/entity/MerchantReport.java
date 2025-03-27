package cn.itbeien.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantReport {
    private Long id;

    private String merNo;

    private String merName;

    private String orderNo;

    private String paywayCode;

    private String sceneCode;

    private BigDecimal amount;

    private BigDecimal refundAmount;

    private BigDecimal merCharge;

    private BigDecimal merSettle;

    private String channelMerNo;

    private String channelMerName;

    private String creatorName;

    private Date tradeTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getPaywayCode() {
        return paywayCode;
    }

    public void setPaywayCode(String paywayCode) {
        this.paywayCode = paywayCode == null ? null : paywayCode.trim();
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode == null ? null : sceneCode.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getMerCharge() {
        return merCharge;
    }

    public void setMerCharge(BigDecimal merCharge) {
        this.merCharge = merCharge;
    }

    public BigDecimal getMerSettle() {
        return merSettle;
    }

    public void setMerSettle(BigDecimal merSettle) {
        this.merSettle = merSettle;
    }

    public String getChannelMerNo() {
        return channelMerNo;
    }

    public void setChannelMerNo(String channelMerNo) {
        this.channelMerNo = channelMerNo == null ? null : channelMerNo.trim();
    }

    public String getChannelMerName() {
        return channelMerName;
    }

    public void setChannelMerName(String channelMerName) {
        this.channelMerName = channelMerName == null ? null : channelMerName.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}