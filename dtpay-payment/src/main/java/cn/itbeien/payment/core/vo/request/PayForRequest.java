package cn.itbeien.payment.core.vo.request;


import cn.itbeien.payment.core.vo.TradeRequest;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class PayForRequest extends TradeRequest {

	private static final long serialVersionUID = 1L;
	
	private String mercOrderNo;
	private String tradeType;
	private String tradeAmt; 
	private String remark;
	private String tradeTime;
	private String feeType;
	private String toAcctNo;
	private String toAcctName;
	private String toAcctType;
	private String toBankNo;
	private String toBankName;
	private String city;
	private String province;
	private String notifyUrl;
	private String returnUrl;

	private String bankNum;
	private String idCard;

	private String mercBatchNo;
	private String mobilePhone;
	
	public String getMercOrderNo() {
		return mercOrderNo;
	}


	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
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


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getTradeTime() {
		return tradeTime;
	}


	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}


	public String getFeeType() {
		return feeType;
	}


	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}


	public String getToAcctNo() {
		return toAcctNo;
	}


	public void setToAcctNo(String toAcctNo) {
		this.toAcctNo = toAcctNo;
	}


	public String getToAcctName() {
		return toAcctName;
	}


	public void setToAcctName(String toAcctName) {
		this.toAcctName = toAcctName;
	}


	public String getToAcctType() {
		return toAcctType;
	}


	public void setToAcctType(String toAcctType) {
		this.toAcctType = toAcctType;
	}


	public String getToBankNo() {
		return toBankNo;
	}


	public void setToBankNo(String toBankNo) {
		this.toBankNo = toBankNo;
	}


	public String getToBankName() {
		return toBankName;
	}


	public void setToBankName(String toBankName) {
		this.toBankName = toBankName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
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

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getMercBatchNo() {
		return mercBatchNo;
	}

	public void setMercBatchNo(String mercBatchNo) {
		this.mercBatchNo = mercBatchNo;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	
}
