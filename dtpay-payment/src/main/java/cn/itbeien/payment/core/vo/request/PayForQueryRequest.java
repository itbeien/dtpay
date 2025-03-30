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
public class PayForQueryRequest extends TradeRequest {
	
	/**
	 * 代付查询
	 */
	private static final long serialVersionUID = 1L;
	
	private String payId;
	
	private String mercOrderNo;
	
	public String getPayId() {
		return payId;
	}


	public void setPayId(String payId) {
		this.payId = payId;
	}


	public String getMercOrderNo() {
		return mercOrderNo;
	}


	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}


}
