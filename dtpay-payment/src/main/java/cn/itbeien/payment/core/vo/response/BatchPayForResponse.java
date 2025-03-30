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
public class BatchPayForResponse extends TradeResponse {
	
	private static final long serialVersionUID = 1L;
	
	private String mercBatchNo;
	
	private String batchStatus;

	public String getMercBatchNo() {
		return mercBatchNo;
	}

	public void setMercBatchNo(String mercBatchNo) {
		this.mercBatchNo = mercBatchNo;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	
}
