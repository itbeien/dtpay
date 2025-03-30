package cn.itbeien.payment.core.vo.response;

import cn.itbeien.common.util.uuid.UUID;
import cn.itbeien.payment.core.vo.TradeResponse;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class PayForNotifyResponse extends TradeResponse {
	
	private static final long serialVersionUID = -2431889620441293001L;

	private String version;// 版本号
	private String locale;
	private String interfaceCode;
	private String charset;
	private String signType;

	private String mercNo;
	private String mercOrderNo;// 商户订单号
	private String payId; // 平台订单号
	private String tradeAmt; // 交易金额
	private String status;// 支付状态
	
    private String feeValue;// 手续费
    private String noticeStr;
    
    private String tradeTime;   // 代付时间
    private String tradeEndTime; // 代付完成时间
    
    private String notifyUrl; // 下游回调地址

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? "1.0" : version;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale == null ? "CN" : locale;
	}

	public String getInterfaceCode() {
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode == null ? "payFor" : interfaceCode;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset == null ? "UTF-8" : charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType == null ? "MD5" : signType;
	}

	public String getMercNo() {
		return mercNo;
	}

	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}

	public String getMercOrderNo() {
		return mercOrderNo;
	}

	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(String feeValue) {
		this.feeValue = feeValue;
	}

	public String getNoticeStr() {
		return noticeStr;
	}

	public void setNoticeStr(String noticeStr) {
		this.noticeStr = noticeStr == null ? UUID.getUUID() : noticeStr;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeEndTime() {
		return tradeEndTime;
	}

	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}

}
