package cn.itbeien.payment.core.vo.response;

import cn.itbeien.payment.core.vo.TradeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class PayForQueryResponse extends TradeResponse {
	
	private static final long serialVersionUID = 1L;
	private String mercOrderNo;
	private String tradeAmt;
	private String tradeTime;
	private String feeType;
	private String payId;
	private String toAcctNo;
	private String toAcctName;
	private String toAcctType;
	private String toBankNo;
	private String toBankName;
	private String city;
	private String province;
	private String status;
	
	private String feeValue;
	
	private String tradeEndTime;

	private String bankPayForTradeSeq;
	
	private String retCode;
	
	private String retDesc;
	
	public String getMercOrderNo() {
		return mercOrderNo;
	}
	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}
	public String getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
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
		this.feeType = StringUtils.isEmpty(feeType) ? "CNY" : feeType;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
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
	public String getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	
	public String getBankPayForTradeSeq() {
		return bankPayForTradeSeq;
	}
	public void setBankPayForTradeSeq(String bankPayForTradeSeq) {
		this.bankPayForTradeSeq = bankPayForTradeSeq;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetDesc() {
		return retDesc;
	}
	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}
	
}
