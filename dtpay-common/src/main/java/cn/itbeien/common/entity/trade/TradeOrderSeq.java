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
public class TradeOrderSeq implements Serializable {
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
    
    private String sceneName;
    
    private String paywayName;
    
    private String startDate;
    
    private String endDate;

    private static final long serialVersionUID = 1L;

    public String getTradeSeq() {
        return tradeSeq;
    }

    public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getPaywayName() {
		return paywayName;
	}

	public void setPaywayName(String paywayName) {
		this.paywayName = paywayName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq == null ? null : tradeSeq.trim();
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

    public String getBankTradeSeq() {
        return bankTradeSeq;
    }

    public void setBankTradeSeq(String bankTradeSeq) {
        this.bankTradeSeq = bankTradeSeq == null ? null : bankTradeSeq.trim();
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp == null ? null : orderIp.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode == null ? null : retCode.trim();
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc == null ? null : retDesc.trim();
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

    public String getPaywayCode() {
        return paywayCode;
    }

    public void setPaywayCode(String paywayCode) {
        this.paywayCode = paywayCode == null ? null : paywayCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getConnectSwitch() {
        return connectSwitch;
    }

    public void setConnectSwitch(String connectSwitch) {
        this.connectSwitch = connectSwitch == null ? null : connectSwitch.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode == null ? null : sceneCode.trim();
    }
}