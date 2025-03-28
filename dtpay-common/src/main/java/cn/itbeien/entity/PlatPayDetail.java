package cn.itbeien.entity;

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
public class PlatPayDetail implements Serializable {
    private String payId;

    private String mercNo;

    private String payingMercNo;

    private String payingMercName;

    private String service;

    private String subMercNo;

    private String mercOrderNo;

    private String toBankNo;

    private String toAcctNo;

    private String toAcctName;

    private String acctType;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private BigDecimal transAmt;

    private String transUsage;

    private String signType;

    private String charset;

    private String version;

    private String locale;

    private String currency;

    private String apprStatus;

    private String status;

    private String retCode;

    private String retDesc;

    private String respCode;

    private String respDesc;

    private String tradeType;

    private String toBankName;

    private String interfaceCode;

    private Date tradeTime;

    private String checkFlag;

    private String remark;

    private String rsfld1;

    private String rsfld2;

    private String rsfld3;

    private String rsfld4;

    private String rsfld5;

    private String channelCode;

    private Date settleTime;

    private String approvers;

    private String bankTradeSeq;

    private BigDecimal feeValue;
    
    private BigDecimal costValue;
    
    private String extField;
    
    private String platBatchNo;
    
    private String mercBatchNo;
    
    private String mobilePhone;
    
    private String noticeStatus;

    private String bankNum;

    private String idCard;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private static final long serialVersionUID = 1L;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getPayingMercNo() {
        return payingMercNo;
    }

    public void setPayingMercNo(String payingMercNo) {
        this.payingMercNo = payingMercNo == null ? null : payingMercNo.trim();
    }

    public String getPayingMercName() {
        return payingMercName;
    }

    public void setPayingMercName(String payingMercName) {
        this.payingMercName = payingMercName == null ? null : payingMercName.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    public String getSubMercNo() {
        return subMercNo;
    }

    public void setSubMercNo(String subMercNo) {
        this.subMercNo = subMercNo == null ? null : subMercNo.trim();
    }

    public String getMercOrderNo() {
        return mercOrderNo;
    }

    public void setMercOrderNo(String mercOrderNo) {
        this.mercOrderNo = mercOrderNo == null ? null : mercOrderNo.trim();
    }

    public String getToBankNo() {
        return toBankNo;
    }

    public void setToBankNo(String toBankNo) {
        this.toBankNo = toBankNo == null ? null : toBankNo.trim();
    }

    public String getToAcctNo() {
        return toAcctNo;
    }

    public void setToAcctNo(String toAcctNo) {
        this.toAcctNo = toAcctNo == null ? null : toAcctNo.trim();
    }

    public String getToAcctName() {
        return toAcctName;
    }

    public void setToAcctName(String toAcctName) {
        this.toAcctName = toAcctName == null ? null : toAcctName.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
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

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public String getTransUsage() {
        return transUsage;
    }

    public void setTransUsage(String transUsage) {
        this.transUsage = transUsage == null ? null : transUsage.trim();
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType == null ? null : signType.trim();
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset == null ? null : charset.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale == null ? null : locale.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(String apprStatus) {
        this.apprStatus = apprStatus == null ? null : apprStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName == null ? null : toBankName.trim();
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode == null ? null : interfaceCode.trim();
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRsfld1() {
        return rsfld1;
    }

    public void setRsfld1(String rsfld1) {
        this.rsfld1 = rsfld1 == null ? null : rsfld1.trim();
    }

    public String getRsfld2() {
        return rsfld2;
    }

    public void setRsfld2(String rsfld2) {
        this.rsfld2 = rsfld2 == null ? null : rsfld2.trim();
    }

    public String getRsfld3() {
        return rsfld3;
    }

    public void setRsfld3(String rsfld3) {
        this.rsfld3 = rsfld3 == null ? null : rsfld3.trim();
    }

    public String getRsfld4() {
        return rsfld4;
    }

    public void setRsfld4(String rsfld4) {
        this.rsfld4 = rsfld4 == null ? null : rsfld4.trim();
    }

    public String getRsfld5() {
        return rsfld5;
    }

    public void setRsfld5(String rsfld5) {
        this.rsfld5 = rsfld5 == null ? null : rsfld5.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers == null ? null : approvers.trim();
    }

    public String getBankTradeSeq() {
        return bankTradeSeq;
    }

    public void setBankTradeSeq(String bankTradeSeq) {
        this.bankTradeSeq = bankTradeSeq == null ? null : bankTradeSeq.trim();
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

	public BigDecimal getCostValue() {
		return costValue;
	}

	public void setCostValue(BigDecimal costValue) {
		this.costValue = costValue;
	}

	public String getExtField() {
		return extField;
	}

	public void setExtField(String extField) {
		this.extField = extField;
	}

	public String getPlatBatchNo() {
		return platBatchNo;
	}

	public void setPlatBatchNo(String platBatchNo) {
		this.platBatchNo = platBatchNo;
	}

	public String getMercBatchNo() {
		return mercBatchNo;
	}

	public void setMercBatchNo(String mercBatchNo) {
		this.mercBatchNo = mercBatchNo;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}