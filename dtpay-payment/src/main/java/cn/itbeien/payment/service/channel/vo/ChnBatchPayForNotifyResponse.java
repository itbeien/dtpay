  
  
package cn.itbeien.payment.service.channel.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class ChnBatchPayForNotifyResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 平台批次号
	private String platBatchNo;
	// 银行批次号
	private String bankBatchNo;
	// 渠道商户号
    private String payingMercNo;
    // 交易总金额
    private BigDecimal batchAmt;
    // 交易总笔数
    private Integer batchCnt;
    // 成功金额
    private BigDecimal successBatchAmt;
    // 成功笔数
    private Integer successBatchCnt;
    // 失败金额
    private BigDecimal failBatchAmt;
    // 失败笔数
    private Integer failBatchCnt;
    // 回调地址
    private String notifyUrl;
    // 批次状态 
    private String batchStatus;
    // 银行流水号
    private String bankTradeSeq;
    // 结果
    private String result;
    // 代付明细
    List<ChnPayForNotifyResponse> payForRes;
    
    // 平台信息
    String respCode;
	String respDesc;
	// 上游信息
	String retCode;
	String retDesc;
    
	public String getPlatBatchNo() {
		return platBatchNo;
	}
	public void setPlatBatchNo(String platBatchNo) {
		this.platBatchNo = platBatchNo;
	}
	public String getBankBatchNo() {
		return bankBatchNo;
	}
	public void setBankBatchNo(String bankBatchNo) {
		this.bankBatchNo = bankBatchNo;
	}
	public String getPayingMercNo() {
		return payingMercNo;
	}
	public void setPayingMercNo(String payingMercNo) {
		this.payingMercNo = payingMercNo;
	}
	public BigDecimal getBatchAmt() {
		return batchAmt;
	}
	public void setBatchAmt(BigDecimal batchAmt) {
		this.batchAmt = batchAmt;
	}
	public Integer getBatchCnt() {
		return batchCnt;
	}
	public void setBatchCnt(Integer batchCnt) {
		this.batchCnt = batchCnt;
	}
	public BigDecimal getSuccessBatchAmt() {
		return successBatchAmt;
	}
	public void setSuccessBatchAmt(BigDecimal successBatchAmt) {
		this.successBatchAmt = successBatchAmt;
	}
	public Integer getSuccessBatchCnt() {
		return successBatchCnt;
	}
	public void setSuccessBatchCnt(Integer successBatchCnt) {
		this.successBatchCnt = successBatchCnt;
	}
	public BigDecimal getFailBatchAmt() {
		return failBatchAmt;
	}
	public void setFailBatchAmt(BigDecimal failBatchAmt) {
		this.failBatchAmt = failBatchAmt;
	}
	public Integer getFailBatchCnt() {
		return failBatchCnt;
	}
	public void setFailBatchCnt(Integer failBatchCnt) {
		this.failBatchCnt = failBatchCnt;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getBankTradeSeq() {
		return bankTradeSeq;
	}
	public void setBankTradeSeq(String bankTradeSeq) {
		this.bankTradeSeq = bankTradeSeq;
	}
	public List<ChnPayForNotifyResponse> getPayForRes() {
		return payForRes;
	}
	public void setPayForRes(List<ChnPayForNotifyResponse> payForRes) {
		this.payForRes = payForRes;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetDesc() {
		return retDesc;
	}
	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}
    
}
  
