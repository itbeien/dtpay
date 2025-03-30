package cn.itbeien.admin.vo.order;


import cn.itbeien.common.vo.BaseBean;

public class TradeRefundQryPar extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	private String refundOrderId;
	private String mercRefundNo;//商户退款订单号
	private String start;
	private String end;
	
	private String qryType; 
	private String sellerName; // 商务登录名

	public String getRefundOrderId() {
		return refundOrderId;
	}

	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
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

	public String getMercRefundNo() {
		return mercRefundNo;
	}

	public void setMercRefundNo(String mercRefundNo) {
		this.mercRefundNo = mercRefundNo;
	}

}
