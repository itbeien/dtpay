package cn.itbeien.payment.service.channel.vo;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class PayOrderQerBean {

	private String version;

	private String charset;

	private String signType;

	private String status;

	private String message;

	private String resultCode;

	private String mchId;

	private String deviceInfo;

	private String nonceStr;

	private String errCode;

	private String errMsg;

	private String sign;

	private String tradeState;

	private String tradeType;

	private String openid;

	private String isSubscribe;

	private String transactionId;

	private String outTradeNo;

	private String totalFee;

	private String feeType;

	private String attach;

	private String bankType;

	private String bankBillno;

	private String timeEnd;

	private String appid;

	private String outTransactionId;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}


	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}


	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankBillno() {
		return bankBillno;
	}

	public void setBankBillno(String bankBillno) {
		this.bankBillno = bankBillno;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOutTransactionId() {
		return outTransactionId;
	}

	public void setOutTransactionId(String outTransactionId) {
		this.outTransactionId = outTransactionId;
	}

	public String getTotalFee() {
		return totalFee;
	}
	

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	@Override
	public String toString() {
		return "PayOrderQerBean [version=" + version + ", charset=" + charset + ", signType=" + signType + ", status="
				+ status + ", message=" + message + ", resultCode=" + resultCode + ", mchId=" + mchId + ", deviceInfo="
				+ deviceInfo + ", nonceStr=" + nonceStr + ", errCode=" + errCode + ", errMsg=" + errMsg + ", sign="
				+ sign + ", tradeState=" + tradeState + ", tradeType=" + tradeType + ", openid=" + openid
				+ ", isSubscribe=" + isSubscribe + ", transactionId=" + transactionId + ", outTradeNo=" + outTradeNo
				+ ", totalFee=" + totalFee + ", feeType=" + feeType + ", attach=" + attach + ", bankType=" + bankType
				+ ", bankBillno=" + bankBillno + ", timeEnd=" + timeEnd + ", appid=" + appid + ", outTransactionId="
				+ outTransactionId + "]";
	}
}
