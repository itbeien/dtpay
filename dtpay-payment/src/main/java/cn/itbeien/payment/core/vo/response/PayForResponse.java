package cn.itbeien.payment.core.vo.response;

import cn.itbeien.payment.core.vo.TradeResponse;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class PayForResponse extends TradeResponse {
	
	private static final long serialVersionUID = 1L;
	
	private String mercOrderNo;
	private String tradeType;
	private String tradeAmt;
	private String tradeTime;
	private String feeType;
	private String payId;
	private String status;// pf0001:提现成功 pf0001:提现中  pf0002:提交成功待返回通知  pf0003:交易超时    pf0004:提现失败
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
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
