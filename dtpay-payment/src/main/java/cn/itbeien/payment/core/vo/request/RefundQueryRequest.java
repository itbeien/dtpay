package cn.itbeien.payment.core.vo.request;

import cn.itbeien.payment.core.vo.TradeRequest;
import jakarta.validation.constraints.NotBlank;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class RefundQueryRequest extends TradeRequest {

	private static final long serialVersionUID = 1L;
	
	private String refundOrderId;

	@NotBlank(message = "mercRefundNo不能为空")
	private String mercRefundNo;
	
	public String getRefundOrderId() {
		return refundOrderId;
	}


	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
	}


	public String getMercRefundNo() {
		return mercRefundNo;
	}


	public void setMercRefundNo(String mercRefundNo) {
		this.mercRefundNo = mercRefundNo;
	}

}
