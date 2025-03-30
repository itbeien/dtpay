package cn.itbeien.admin.vo.order;


import cn.itbeien.common.vo.BaseBean;

public class TradeOrderQryPar extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 支付订单号
	 */
	private String orderId;
	private String mercOrderNo;
	private String start;
	private String end;
	
	private String qryType; 
	private String sellerName; // 商务登录名
	
	private String stamDate;
	private String stamDateEnd;
	private String channelCode;
	private String payStatus;
	private String mercNo;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getMercOrderNo() {
		return mercOrderNo;
	}

	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getStamDate() {
		return stamDate;
	}

	public void setStamDate(String stamDate) {
		this.stamDate = stamDate;
	}

	public String getStamDateEnd() {
		return stamDateEnd;
	}

	public void setStamDateEnd(String stamDateEnd) {
		this.stamDateEnd = stamDateEnd;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getMercNo() {
		return mercNo;
	}

	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}
}
