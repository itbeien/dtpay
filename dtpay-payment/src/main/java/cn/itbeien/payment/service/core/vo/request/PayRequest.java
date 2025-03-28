package cn.itbeien.payment.service.core.vo.request;

import cn.itbeien.payment.service.core.vo.TradeRequest;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * Copyright© 2025 itbeien
 */
public class PayRequest extends TradeRequest {

	private static final long serialVersionUID = 1L;
	
	private String mercOrderNo;
	
	private String subject;
	
	private String body;
	
	private String tradeType;
	
	private String tradeAmt;
	
	private String feeType;
	
	private String tradeTime;
	
	private String notifyUrl;
	
	private String returnUrl;
	
	private String refererUrl;
	
	private String termType;
	
	private String paywayCode;
	
	private String sceneCode;
	
	private String ip;
	
	private String merchantId;
	
	private String storeId;
	
	private String payeeId;
	
	private String attach;
	
	private String deviceInfo;
	
	private String mchAppName;
	
	private String mchAppId;
	
	private String quickPayAttach;
	
	private String remark;
	
	private String orderPeriod;
	
	private String noticeStr;
	
	private String payingMercNo;
	
	private String bankCode;
	
	private String orderId;
	
	private String payType;
	
	public String getMercOrderNo() {
		return mercOrderNo;
	}
	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getRefererUrl() {
		return refererUrl;
	}
	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	
	public String getPaywayCode() {
		return paywayCode;
	}
	public void setPaywayCode(String paywayCode) {
		this.paywayCode = paywayCode;
	}
	public String getSceneCode() {
		return sceneCode;
	}
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getMchAppName() {
		return mchAppName;
	}
	public void setMchAppName(String mchAppName) {
		this.mchAppName = mchAppName;
	}
	public String getMchAppId() {
		return mchAppId;
	}
	public void setMchAppId(String mchAppId) {
		this.mchAppId = mchAppId;
	}
	public String getQuickPayAttach() {
		return quickPayAttach;
	}
	public void setQuickPayAttach(String quickPayAttach) {
		this.quickPayAttach = quickPayAttach;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderPeriod() {
		return orderPeriod;
	}
	public void setOrderPeriod(String orderPeriod) {
		this.orderPeriod = orderPeriod;
	}
	
	public String getNoticeStr() {
		return noticeStr;
	}
	public void setNoticeStr(String noticeStr) {
		this.noticeStr = noticeStr;
	}
	public String getPayingMercNo() {
		return payingMercNo;
	}
	public void setPayingMercNo(String payingMercNo) {
		this.payingMercNo = payingMercNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}

}
