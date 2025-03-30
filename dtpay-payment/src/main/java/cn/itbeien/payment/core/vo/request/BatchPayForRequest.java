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
public class BatchPayForRequest extends TradeRequest {
	
	/**
	 * 批量代付  请求对象
	 */
	private static final long serialVersionUID = 1L;
	
	private String mercBatchNo;
	
	private String tradeType;
	
	private String batchAmt;
	
	private String batchCnt;
	
	private String detailData;
	
	private String notifyUrl;
	
	private String attach;

	private String mobilePhone;
	
	public String getMercBatchNo() {
		return mercBatchNo;
	}



	public void setMercBatchNo(String mercBatchNo) {
		this.mercBatchNo = mercBatchNo;
	}



	public String getTradeType() {
		return tradeType;
	}



	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}



	public String getBatchAmt() {
		return batchAmt;
	}



	public void setBatchAmt(String batchAmt) {
		this.batchAmt = batchAmt;
	}



	public String getBatchCnt() {
		return batchCnt;
	}



	public void setBatchCnt(String batchCnt) {
		this.batchCnt = batchCnt;
	}



	public String getDetailData() {
		return detailData;
	}



	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}



	public String getNotifyUrl() {
		return notifyUrl;
	}



	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAttach() {
		return attach;
	}



	public void setAttach(String attach) {
		this.attach = attach;
	}

	
}
