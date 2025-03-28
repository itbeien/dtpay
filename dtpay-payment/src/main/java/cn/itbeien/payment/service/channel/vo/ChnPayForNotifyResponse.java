  
  
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
public class ChnPayForNotifyResponse extends ChannelVo{

	// 返回给上游的结果
	private String result;
	// 上游订单号
	private String bankPayForTradeSeq;
	// 交易状态
    private String status;
    // 平台订单号
    private String payId;
    // 金额
    private BigDecimal tradeAmt;
    // 手续费
    private BigDecimal feeValue;
    // 代付完成时间
    private Date tradeEndTime;
    // 代付提交时间
    private Date tradeTime;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
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
	public Date getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(Date tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
}
  
