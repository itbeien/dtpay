package cn.itbeien.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FreezeDetail implements Serializable {
    private String id;

    private String orderId;

    private String mercNo;

    private String mercOrderNo;

    private String tradeType;

    private String jsType;

    private BigDecimal orderAmount;

    private BigDecimal avaiAmount;

    private BigDecimal feeAmount;

    private BigDecimal freezeAmt;

    private Date freezeTime;

    private String remark;

    private Date unfreezeTime;

    private String freezeStatus;

    private BigDecimal feeValue;

    private String channelCode;

    private String paywayCode;

    private String sceneCode;

    private BigDecimal jsScaleRate;
    
    private String payingMercNo;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType == null ? null : jsType.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getAvaiAmount() {
        return avaiAmount;
    }

    public void setAvaiAmount(BigDecimal avaiAmount) {
        this.avaiAmount = avaiAmount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFreezeAmt() {
        return freezeAmt;
    }

    public void setFreezeAmt(BigDecimal freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public Date getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(Date freezeTime) {
        this.freezeTime = freezeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUnfreezeTime() {
        return unfreezeTime;
    }

    public void setUnfreezeTime(Date unfreezeTime) {
        this.unfreezeTime = unfreezeTime;
    }

    public String getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(String freezeStatus) {
        this.freezeStatus = freezeStatus == null ? null : freezeStatus.trim();
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
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

    public BigDecimal getJsScaleRate() {
        return jsScaleRate;
    }

    public void setJsScaleRate(BigDecimal jsScaleRate) {
        this.jsScaleRate = jsScaleRate;
    }

	public String getPayingMercNo() {
		return payingMercNo;
	}

	public void setPayingMercNo(String payingMercNo) {
		this.payingMercNo = payingMercNo;
	}
    
    
}