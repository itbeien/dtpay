package cn.itbeien.payment.service.core.vo.request;


import cn.itbeien.payment.service.core.vo.TradeRequest;

public class PayQueryRequest extends TradeRequest {

	private static final long serialVersionUID = 1L;
	
	private String tradeType;
	
	private String orderId;
	
	private String mercOrderNo;
	
	private String payingMercNo;
	
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMercOrderNo() {
		return mercOrderNo;
	}
	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}
	
	public String getPayingMercNo() {
		return payingMercNo;
	}
	public void setPayingMercNo(String payingMercNo) {
		this.payingMercNo = payingMercNo;
	}
	
}
