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
public class ChnRefundQueryResponse extends ChannelVo {
	
	private String bankRefundTradeSeq;

    private String refundStatus;

    private Date refundCompleteTime;
    
    private int refundCount;
    
    private String refundOrderId;
    
    private String refundTradeSeq;
    
    private BigDecimal tradeAmt;
    
    private BigDecimal refundAmt;

	public String getBankRefundTradeSeq() {
		return bankRefundTradeSeq;
	}

	public void setBankRefundTradeSeq(String bankRefundTradeSeq) {
		this.bankRefundTradeSeq = bankRefundTradeSeq;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getRefundCompleteTime() {
		return refundCompleteTime;
	}

	public void setRefundCompleteTime(Date refundCompleteTime) {
		this.refundCompleteTime = refundCompleteTime;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public int getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}

	public String getRefundOrderId() {
		return refundOrderId;
	}

	public void setRefundOrderId(String refundOrderId) {
		this.refundOrderId = refundOrderId;
	}

	public String getRefundTradeSeq() {
		return refundTradeSeq;
	}

	public void setRefundTradeSeq(String refundTradeSeq) {
		this.refundTradeSeq = refundTradeSeq;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	@Override
	public String toString() {
		return "ChnRefundQueryResponse [bankRefundTradeSeq=" + bankRefundTradeSeq + ", refundStatus=" + refundStatus
				+ ", refundCompleteTime=" + refundCompleteTime + ", refundCount=" + refundCount + ", refundOrderId="
				+ refundOrderId + ", refundTradeSeq=" + refundTradeSeq + ", tradeAmt=" + tradeAmt + ", refundAmt="
				+ refundAmt + "]";
	}
}
