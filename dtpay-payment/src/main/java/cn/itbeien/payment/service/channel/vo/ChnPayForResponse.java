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
public class ChnPayForResponse extends ChannelVo {
	
	private String bankPayForTradeSeq;

    private String status;

    private Date completeTime;
    
    private String payId;
    
    private String payTradeSeq;//
    
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPayTradeSeq() {
		return payTradeSeq;
	}

	public void setPayTradeSeq(String payTradeSeq) {
		this.payTradeSeq = payTradeSeq;
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
		return "ChnPayForResponse [bankPayForTradeSeq=" + bankPayForTradeSeq + ", status=" + status + ", completeTime="
				+ completeTime + ", payId=" + payId + ", payTradeSeq=" + payTradeSeq + ", tradeAmt=" + tradeAmt
				+ ", feeValue=" + feeValue + "]";
	}

}
