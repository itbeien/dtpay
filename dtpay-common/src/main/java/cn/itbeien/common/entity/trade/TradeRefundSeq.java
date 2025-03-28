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
public class TradeRefundSeq implements Serializable {
    private String refundTradeSeq;

    private String refundOrderId;   // 平台退款订单号

    private String mercNo;

    private String mercRefundNo;

    private String orgMercOrderNo;

    private String orgOrderId;  // 原平台订单流水号

    private Date orgTradeTime;

    private String mercName;

    private String orgProductName;

    private String refundReason;

    private BigDecimal refundAmount;

    private String bankRefundTradeSeq;

    private String refundStatus;

    private String channelCode;

    private Date refundCompleteTime;

    private String remark;

    private String payingMercNo;

    private String payingMercName;

    private String connectSwitch;

    private Date createTime;

    private String retCode;

    private String retDesc;

    private String respCode;

    private String respDesc;

    private String paywayCode;

    private String sceneCode;
    
    private String noticeStr;   // 随机数
    
    private BigDecimal tradeAmt; // 订单金额

    private static final long serialVersionUID = 1L;

    public String getRefundTradeSeq() {
        return refundTradeSeq;
    }

    public void setRefundTradeSeq(String refundTradeSeq) {
        this.refundTradeSeq = refundTradeSeq == null ? null : refundTradeSeq.trim();
    }

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

    public Date getOrgTradeTime() {
        return orgTradeTime;
    }

    public void setOrgTradeTime(Date orgTradeTime) {
        this.orgTradeTime = orgTradeTime;
    }

    public String getMercName() {
        return mercName;
    }

    public void setMercName(String mercName) {
        this.mercName = mercName == null ? null : mercName.trim();
    }

    public String getOrgProductName() {
        return orgProductName;
    }

    public void setOrgProductName(String orgProductName) {
        this.orgProductName = orgProductName == null ? null : orgProductName.trim();
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason == null ? null : refundReason.trim();
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getBankRefundTradeSeq() {
        return bankRefundTradeSeq;
    }

    public void setBankRefundTradeSeq(String bankRefundTradeSeq) {
        this.bankRefundTradeSeq = bankRefundTradeSeq == null ? null : bankRefundTradeSeq.trim();
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Date getRefundCompleteTime() {
        return refundCompleteTime;
    }

    public void setRefundCompleteTime(Date refundCompleteTime) {
        this.refundCompleteTime = refundCompleteTime;
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

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode == null ? null : sceneCode.trim();
    }

	public String getNoticeStr() {
		return noticeStr;
	}

	public void setNoticeStr(String noticeStr) {
		this.noticeStr = noticeStr;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	
}