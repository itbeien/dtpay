package cn.itbeien.payment.service.channel.vo;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class PayBackBean {
	   private String version;
	   private String charset;
	   private String signType;
	   private String status;
	   private String message;
	   private String appid;
	   private String mchId;
	   private String resultCode;
	   private String nonceStr;
	   private String errCode;
	   private String errMsg;
	   private String sign;
	   private String payInfo;
	   private String amount;
	   private String codeUrl;
	   private String outTradeId;
	   private String codeImgUrl;
	   private String resultHtml;

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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
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

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getOutTradeId() {
		return outTradeId;
	}

	public void setOutTradeId(String outTradeId) {
		this.outTradeId = outTradeId;
	}

	public String getCodeImgUrl() {
		return codeImgUrl;
	}

	public void setCodeImgUrl(String codeImgUrl) {
		this.codeImgUrl = codeImgUrl;
	}

	public String getResultHtml() {
		return resultHtml;
	}

	public void setResultHtml(String resultHtml) {
		this.resultHtml = resultHtml;
	}

	@Override
	public String toString() {
		return "PayBackBean [version=" + version + ", charset=" + charset + ", signType=" + signType + ", status="
				+ status + ", message=" + message + ", appid=" + appid + ", mchId=" + mchId + ", resultCode="
				+ resultCode + ", nonceStr=" + nonceStr + ", errCode=" + errCode + ", errMsg=" + errMsg + ", sign="
				+ sign + ", payInfo=" + payInfo + ", amount=" + amount + ", codeUrl=" + codeUrl + ", outTradeId="
				+ outTradeId + ", codeImgUrl=" + codeImgUrl + ", resultHtml=" + resultHtml + "]";
	}

}
