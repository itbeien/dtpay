package cn.itbeien.payment.core.vo.request;


import cn.itbeien.payment.core.vo.TradeRequest;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 退款接口请求对象
 * Copyright© 2025 itbeien
 */
public class RefundRequest extends TradeRequest {

	private static final long serialVersionUID = 1L;
	
	private String mercRefundNo;
	
	private String orgMercOrderNo;
	
	private String orgOrderId;
	
	private String refundAmt;
	
	private String refundTime;
	
	public String getMercRefundNo() {
		return mercRefundNo;
	}
	public void setMercRefundNo(String mercRefundNo) {
		this.mercRefundNo = mercRefundNo;
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
	

}
