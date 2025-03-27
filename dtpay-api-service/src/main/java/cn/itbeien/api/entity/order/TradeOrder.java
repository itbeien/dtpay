package cn.itbeien.api.entity.order;

import java.math.BigDecimal;
import java.util.Date;

public class TradeOrder {
    /**
     * 微服务生成的订单号
     */
    private String orderId;
    /**
     * 下游商户编号
     */
    private String mercNo;
    /**
     * 下游商户订单号
     */
    private String mercOrderNo;
    /**
     * 交易类型 pay,refund
     */
    private String tradeType;
    /**
     * 商品名称
     */
    private String subject;
    /**
     * 商品详情
     */
    private String body;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    private Date orderTime;

    private String orderIp;

    private String returnUrl;

    private String notifyUrl;

    private String refererUrl;

    private Integer orderPeriod;

    private Date expireTime;

    private String remark;

    private Date createTime;

    private String payStatus;

    private String isRefund;

    private Integer refundTimes;

    private BigDecimal successRefundAmount;

    private String termType;

    private String signType;

    private String charset;

    private String version;

    private String locale;

    private String currency;

    private String mpOpenid;

    private String mpAppid;

    private String interfaceCode;

    private BigDecimal feeValue;

    private String isStatic;

    private String channelCode;

    private String paywayCode;

    private String sceneCode;

    private Date callBackTime;

    private Long timeDifference;

    private BigDecimal costValue;

    private BigDecimal costRatio;

    private BigDecimal feeRatio;

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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp == null ? null : orderIp.trim();
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getRefererUrl() {
        return refererUrl;
    }

    public void setRefererUrl(String refererUrl) {
        this.refererUrl = refererUrl == null ? null : refererUrl.trim();
    }

    public Integer getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(Integer orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund == null ? null : isRefund.trim();
    }

    public Integer getRefundTimes() {
        return refundTimes;
    }

    public void setRefundTimes(Integer refundTimes) {
        this.refundTimes = refundTimes;
    }

    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getMpOpenid() {
        return mpOpenid;
    }

    public void setMpOpenid(String mpOpenid) {
        this.mpOpenid = mpOpenid == null ? null : mpOpenid.trim();
    }

    public String getMpAppid() {
        return mpAppid;
    }

    public void setMpAppid(String mpAppid) {
        this.mpAppid = mpAppid == null ? null : mpAppid.trim();
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode == null ? null : interfaceCode.trim();
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public String getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(String isStatic) {
        this.isStatic = isStatic == null ? null : isStatic.trim();
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

    public Date getCallBackTime() {
        return callBackTime;
    }

    public void setCallBackTime(Date callBackTime) {
        this.callBackTime = callBackTime;
    }

    public Long getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(Long timeDifference) {
        this.timeDifference = timeDifference;
    }

    public BigDecimal getCostValue() {
        return costValue;
    }

    public void setCostValue(BigDecimal costValue) {
        this.costValue = costValue;
    }

    public BigDecimal getCostRatio() {
        return costRatio;
    }

    public void setCostRatio(BigDecimal costRatio) {
        this.costRatio = costRatio;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }
}