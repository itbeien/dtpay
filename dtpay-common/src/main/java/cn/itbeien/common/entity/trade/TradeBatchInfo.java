package cn.itbeien.common.entity.trade;

import java.io.Serializable;
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
public class TradeBatchInfo implements Serializable {
    private String platBatchNo;

    private String tradeType;

    private String mercNo;

    private String mercBatchNo;

    private String interfaceCode;

    private BigDecimal batchAmt;

    private Integer batchCnt;

    private BigDecimal successBatchAmt;

    private Integer successBatchCnt;

    private BigDecimal failBatchAmt;

    private Integer failBatchCnt;

    private String notifyUrl;

    private String batchStatus;

    private String bankTradeSeq;

    private String retCode;

    private String retDesc;

    private String respCode;

    private String respDesc;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String attach;
    
    private BigDecimal batchFeeAmt;

    private static final long serialVersionUID = 1L;

    public String getPlatBatchNo() {
        return platBatchNo;
    }

    public void setPlatBatchNo(String platBatchNo) {
        this.platBatchNo = platBatchNo == null ? null : platBatchNo.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getMercBatchNo() {
        return mercBatchNo;
    }

    public void setMercBatchNo(String mercBatchNo) {
        this.mercBatchNo = mercBatchNo == null ? null : mercBatchNo.trim();
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode == null ? null : interfaceCode.trim();
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
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus == null ? null : batchStatus.trim();
    }

    public String getBankTradeSeq() {
        return bankTradeSeq;
    }

    public void setBankTradeSeq(String bankTradeSeq) {
        this.bankTradeSeq = bankTradeSeq == null ? null : bankTradeSeq.trim();
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode == null ? null : retCode.trim();
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc == null ? null : retDesc.trim();
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode == null ? null : respCode.trim();
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc == null ? null : respDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach == null ? null : attach.trim();
    }

	public BigDecimal getBatchFeeAmt() {
		return batchFeeAmt;
	}

	public void setBatchFeeAmt(BigDecimal batchFeeAmt) {
		this.batchFeeAmt = batchFeeAmt;
	}
    
}