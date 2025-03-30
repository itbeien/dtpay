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
public class TradeRefund implements Serializable {
    private String refundOrderId;

    private String mercNo;

    private String mercRefundNo;

    private String orgMercOrderNo;

    private String orgOrderId;

    private BigDecimal tradeAmount;

    private Date tradeTime;

    private String redundStatus;

    private String cancelReason;

    private String signType;

    private String charset;

    private String version;

    private String locale;

    private String channelCode;

    private String paywayCode;

    private String sceneCode;

    private static final long serialVersionUID = 1L;

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId == null ? null : refundOrderId.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getMercRefundNo() {
        return mercRefundNo;
    }

    public void setMercRefundNo(String mercRefundNo) {
        this.mercRefundNo = mercRefundNo == null ? null : mercRefundNo.trim();
    }

    public String getOrgMercOrderNo() {
        return orgMercOrderNo;
    }

    public void setOrgMercOrderNo(String orgMercOrderNo) {
        this.orgMercOrderNo = orgMercOrderNo == null ? null : orgMercOrderNo.trim();
    }

    public String getOrgOrderId() {
        return orgOrderId;
    }

    public void setOrgOrderId(String orgOrderId) {
        this.orgOrderId = orgOrderId == null ? null : orgOrderId.trim();
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getRedundStatus() {
        return redundStatus;
    }

    public void setRedundStatus(String redundStatus) {
        this.redundStatus = redundStatus == null ? null : redundStatus.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType == null ? null : signType.trim();
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset == null ? null : charset.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale == null ? null : locale.trim();
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
}