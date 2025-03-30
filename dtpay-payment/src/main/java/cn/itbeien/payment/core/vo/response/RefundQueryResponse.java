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
public class RefundQueryResponse extends TradeResponse {

	private static final long serialVersionUID = 1L;
	
	private String mercRefundNo;
	private String refundOrderId;
	private String orgMercOrderNo;
	private String orgOrderId;
	private String refundAmt;
	private String refundTime;
	private String feeType;
	private String tradeEndTime;
	private String refundStatus;
	public String getMercRefundNo() {
		return mercRefundNo;
	}
	public void setMercRefundNo(String mercRefundNo) {
		this.mercRefundNo = mercRefundNo;
	}
	public String getRefundOrderId() {
		return refundOrderId;
	}
	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
	}
	public String getOrgMercOrderNo() {
		return orgMercOrderNo;
	}
	public void setOrgMercOrderNo(String orgMercOrderNo) {
		this.orgMercOrderNo = orgMercOrderNo;
	}
	public String getOrgOrderId() {
		return orgOrderId;
	}
	public void setOrgOrderId(String orgOrderId) {
		this.orgOrderId = orgOrderId;
	}
	public String getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	
}
