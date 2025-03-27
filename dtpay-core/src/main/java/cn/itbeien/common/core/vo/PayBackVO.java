package cn.itbeien.common.core.vo;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 上游返回数据适配---上游通道预支付返回结构
 * Copyright© 2024 itbeien
 */
public class PayBackVO {
	/** 版本号
	    * */
	   private String version;
	   /** 字符集
	    *  */
	   private String charset;
	   /** 签名方式
	    *  */
	   private String signType;
	   /** 返回状态码
	    *  */
	   private String status;
	   /** 返回信息
	    *  */
	   private String message;
	   /** 公众账号Id
	    *  */
	   private String appid;
	   /** 商户号
	    * */
	   private String mchId;
	   /** 业务结果
	    * */
	   private String resultCode;
	   /** 随机字符串
	    *  */
	   private String nonceStr;
	   /** 错误代码
	    * */
	   private String errCode;
	   /** 错误代码描述
	    *  */
	   private String errMsg;
	   /** 签名
	    * */
	   private String sign;
	   /** 支付地址（唤起手机微信支付url地址）
	    *  */
	   private String payInfo;
	   /**支付金额*/
	   private String amount;
	   /** 二维码支付地址（只有扫码支付才会返回）*/
	   private String codeUrl;
	   /** 外部系统订单号
	    *  */
	   private String outTradeId;
	   
	   /** 二维码图地址
	    *  */
	   private String codeImgUrl;
	   
	   /**
	    * 返回的html form表单
	    */
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
		return "PayBackVO [version=" + version + ", charset=" + charset + ", signType=" + signType + ", status="
				+ status + ", message=" + message + ", appid=" + appid + ", mchId=" + mchId + ", resultCode="
				+ resultCode + ", nonceStr=" + nonceStr + ", errCode=" + errCode + ", errMsg=" + errMsg + ", sign="
				+ sign + ", payInfo=" + payInfo + ", amount=" + amount + ", codeUrl=" + codeUrl + ", outTradeId="
				+ outTradeId + ", codeImgUrl=" + codeImgUrl + ", resultHtml=" + resultHtml + "]";
	}

}
