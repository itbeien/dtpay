package cn.itbeien.payment.service.channel.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class ChnBatchPayForResponse extends ChannelVo {
	
	private String bankPayForTradeSeq;

    private String batStatus;

    private Date completeTime;
    
    private String mercBatchNo;
    
    private String platBatchNo;
    
    private BigDecimal tradeAmt;
    
    private BigDecimal feeValue;

    private String resultHtml;

	public String getResultHtml() {
		return resultHtml;
	}

	public void setResultHtml(String resultHtml) {
		this.resultHtml = resultHtml;
	}

	public String getBankPayForTradeSeq() {
		return bankPayForTradeSeq;
	}

	public void setBankPayForTradeSeq(String bankPayForTradeSeq) {
		this.bankPayForTradeSeq = bankPayForTradeSeq;
	}

	public String getBatStatus() {
		return batStatus;
	}

	public void setBatStatus(String batStatus) {
		this.batStatus = batStatus;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getMercBatchNo() {
		return mercBatchNo;
	}

	public void setMercBatchNo(String mercBatchNo) {
		this.mercBatchNo = mercBatchNo;
	}

	public String getPlatBatchNo() {
		return platBatchNo;
	}

	public void setPlatBatchNo(String platBatchNo) {
		this.platBatchNo = platBatchNo;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(BigDecimal feeValue) {
		this.feeValue = feeValue;
	}

	@Override
	public String toString() {
		return "ChnBatchPayForResponse [bankPayForTradeSeq=" + bankPayForTradeSeq + ", batStatus=" + batStatus
				+ ", completeTime=" + completeTime + ", mercBatchNo=" + mercBatchNo + ", platBatchNo=" + platBatchNo
				+ ", tradeAmt=" + tradeAmt + ", feeValue=" + feeValue + "]";
	}

}
