package cn.itbeien.common.vo.trade;

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
public class PlatPayDetailQryPar implements Serializable {

	private static final long serialVersionUID = 1L;
	
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


	private String mercName;
	
	private BigDecimal startTransAmt;
	
	private BigDecimal endTransAmt;
	
	private String  startDate;
	
	private String  endDate;
	
	private String jsStartDate;
	
	private String  jsEndDate;

	private String stamDate;
	private String stamDateEnd;
	private String payForStatus;
	
	private String qryType; 
	private String sellerName;
	
	public String getMercName() {
		return mercName;
	}
	public void setMercName(String mercName) {
		this.mercName = mercName;
	}
	
	public BigDecimal getStartTransAmt() {
		return startTransAmt;
	}
	public void setStartTransAmt(BigDecimal startTransAmt) {
		this.startTransAmt = startTransAmt;
	}
	
	public BigDecimal getEndTransAmt() {
		return endTransAmt;
	}
	public void setEndTransAmt(BigDecimal endTransAmt) {
		this.endTransAmt = endTransAmt;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getJsStartDate() {
		return jsStartDate;
	}
	public void setJsStartDate(String jsStartDate) {
		this.jsStartDate = jsStartDate;
	}
	public String getJsEndDate() {
		return jsEndDate;
	}
	public void setJsEndDate(String jsEndDate) {
		this.jsEndDate = jsEndDate;
	}
	
	public String getQryType() {
		return qryType;
	}
	public void setQryType(String qryType) {
		this.qryType = qryType;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}


	private int pageNum;	// 第几页
	private int pageSize;   // 每页显示几条
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStamDate() {
		return stamDate;
	}

	public void setStamDate(String stamDate) {
		this.stamDate = stamDate;
	}

	public String getStamDateEnd() {
		return stamDateEnd;
	}

	public void setStamDateEnd(String stamDateEnd) {
		this.stamDateEnd = stamDateEnd;
	}

	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getMercNo() {
		return mercNo;
	}
	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}
	public String getPayingMercNo() {
		return payingMercNo;
	}
	public void setPayingMercNo(String payingMercNo) {
		this.payingMercNo = payingMercNo;
	}
	public String getPayingMercName() {
		return payingMercName;
	}
	public void setPayingMercName(String payingMercName) {
		this.payingMercName = payingMercName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getSubMercNo() {
		return subMercNo;
	}
	public void setSubMercNo(String subMercNo) {
		this.subMercNo = subMercNo;
	}
	public String getMercOrderNo() {
		return mercOrderNo;
	}
	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}
	public String getToBankNo() {
		return toBankNo;
	}
	public void setToBankNo(String toBankNo) {
		this.toBankNo = toBankNo;
	}
	public String getToAcctNo() {
		return toAcctNo;
	}
	public void setToAcctNo(String toAcctNo) {
		this.toAcctNo = toAcctNo;
	}
	public String getToAcctName() {
		return toAcctName;
	}
	public void setToAcctName(String toAcctName) {
		this.toAcctName = toAcctName;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
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
		this.creator = creator;
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
		this.updater = updater;
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
		this.transUsage = transUsage;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getApprStatus() {
		return apprStatus;
	}
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getToBankName() {
		return toBankName;
	}
	public void setToBankName(String toBankName) {
		this.toBankName = toBankName;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
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
		this.checkFlag = checkFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRsfld1() {
		return rsfld1;
	}
	public void setRsfld1(String rsfld1) {
		this.rsfld1 = rsfld1;
	}
	public String getRsfld2() {
		return rsfld2;
	}
	public void setRsfld2(String rsfld2) {
		this.rsfld2 = rsfld2;
	}
	public String getRsfld3() {
		return rsfld3;
	}
	public void setRsfld3(String rsfld3) {
		this.rsfld3 = rsfld3;
	}
	public String getRsfld4() {
		return rsfld4;
	}
	public void setRsfld4(String rsfld4) {
		this.rsfld4 = rsfld4;
	}
	public String getRsfld5() {
		return rsfld5;
	}
	public void setRsfld5(String rsfld5) {
		this.rsfld5 = rsfld5;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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
		this.approvers = approvers;
	}
	public String getBankTradeSeq() {
		return bankTradeSeq;
	}
	public void setBankTradeSeq(String bankTradeSeq) {
		this.bankTradeSeq = bankTradeSeq;
	}
	public BigDecimal getFeeValue() {
		return feeValue;
	}
	public void setFeeValue(BigDecimal feeValue) {
		this.feeValue = feeValue;
	}
	public String getPayForStatus() {
		return payForStatus;
	}

	public void setPayForStatus(String payForStatus) {
		this.payForStatus = payForStatus;
	}
}