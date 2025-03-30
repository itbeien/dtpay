package cn.itbeien.common.entity.trade;

import java.math.BigDecimal;
import java.util.Date;

public class TradeReport {
    private String reportCode;

    private Date orderTime;

    private String mercName;

    private String mercNo;

    private String sceneName;

    private String sceneCode;

    private BigDecimal costRatio;

    private BigDecimal mercRatio;

    private Long tradeNumber;

    private BigDecimal tradeAmount;

    private BigDecimal tradeCostValue;

    private BigDecimal feeValue;

    private BigDecimal grossMargin;

    private String channelCode;

    private String channelName;

    private String paywayName;

    private String paywayCode;

    private Date reportCreateDate;

    private String payingMercNo;

    private String payingMercName;

    private Date callBackTime;

    private String altfie1;

    private String altfie2;

    private String altfie3;

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode == null ? null : reportCode.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getMercName() {
        return mercName;
    }

    public void setMercName(String mercName) {
        this.mercName = mercName == null ? null : mercName.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode == null ? null : sceneCode.trim();
    }

    public BigDecimal getCostRatio() {
        return costRatio;
    }

    public void setCostRatio(BigDecimal costRatio) {
        this.costRatio = costRatio;
    }

    public BigDecimal getMercRatio() {
        return mercRatio;
    }

    public void setMercRatio(BigDecimal mercRatio) {
        this.mercRatio = mercRatio;
    }

    public Long getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(Long tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getTradeCostValue() {
        return tradeCostValue;
    }

    public void setTradeCostValue(BigDecimal tradeCostValue) {
        this.tradeCostValue = tradeCostValue;
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public BigDecimal getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(BigDecimal grossMargin) {
        this.grossMargin = grossMargin;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getPaywayName() {
        return paywayName;
    }

    public void setPaywayName(String paywayName) {
        this.paywayName = paywayName == null ? null : paywayName.trim();
    }

    public String getPaywayCode() {
        return paywayCode;
    }

    public void setPaywayCode(String paywayCode) {
        this.paywayCode = paywayCode == null ? null : paywayCode.trim();
    }

    public Date getReportCreateDate() {
        return reportCreateDate;
    }

    public void setReportCreateDate(Date reportCreateDate) {
        this.reportCreateDate = reportCreateDate;
    }

    public String getPayingMercNo() {
        return payingMercNo;
    }

    public void setPayingMercNo(String payingMercNo) {
        this.payingMercNo = payingMercNo == null ? null : payingMercNo.trim();
    }

    public String getPayingMercName() {
        return payingMercName;
    }

    public void setPayingMercName(String payingMercName) {
        this.payingMercName = payingMercName == null ? null : payingMercName.trim();
    }

    public Date getCallBackTime() {
        return callBackTime;
    }

    public void setCallBackTime(Date callBackTime) {
        this.callBackTime = callBackTime;
    }

    public String getAltfie1() {
        return altfie1;
    }

    public void setAltfie1(String altfie1) {
        this.altfie1 = altfie1 == null ? null : altfie1.trim();
    }

    public String getAltfie2() {
        return altfie2;
    }

    public void setAltfie2(String altfie2) {
        this.altfie2 = altfie2 == null ? null : altfie2.trim();
    }

    public String getAltfie3() {
        return altfie3;
    }

    public void setAltfie3(String altfie3) {
        this.altfie3 = altfie3 == null ? null : altfie3.trim();
    }
}