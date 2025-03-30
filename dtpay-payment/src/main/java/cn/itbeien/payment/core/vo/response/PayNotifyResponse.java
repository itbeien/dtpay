package cn.itbeien.payment.core.vo.response;


import cn.itbeien.payment.core.vo.TradeResponse;

public class PayNotifyResponse extends TradeResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9185041148087873969L;
	private String mercOrderNo;//商户订单号
	private String tradeType;//交易类型
	private String tradeAmt;//交易金额
	private String tradeTime;//支付提交时间
	private String feeType;//币种
	private String termType;//终端接入类型
	private String orderId;//平台订单号
	private String tradeEndTime;//交易完成时间
	private String payStatus;//支付状态
	private String bankType;//付款银行
	
	// flag  notifyUrl字段不用返回给下游
	private String flag; // 是否是在界面上 点击的补发通知
	private String notifyUrl; // 回调下游URL
	
	
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
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}
