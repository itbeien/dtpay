package cn.itbeien.business.merchant.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantInfo {
    private String mercNo;

    private String mercName;

    private String mercNickName;

    private String publicKeyPath;

    private String mercSignType;

    private String mercPrivateKey;

    private String status;

    private String linkman;

    private String telphone;

    private String email;

    private String address;

    private String mccId;

    private String merArea;

    private String employeeNum;

    private String contractNo;

    private String contractPath;

    private String mercBusiLic;

    private String licValiDate;

    private String busiLicFileName;

    private String legalValidity;

    private String corpReptName;

    private String corpReptId;

    private String postCode;

    private String fixedPhone;

    private String province;

    private String city;

    private String corpAddr;

    private String oriPasswd;

    private String passwd;

    private String passwdFlag;

    private Integer wrongCounts;

    private Date lockedTime;

    private String sendBillFlag;

    private String mercSettAcctType;

    private String mercSettAcct;

    private String mercSettAcctNm;

    private String mercSettBank;

    private String mercSettBankNm;

    private String mercSettBankPro;

    private String mercSettBankCity;

    private String clearCycle;

    private String clearDate;

    //费率
    private String clearRate;

    private String clearFlag;

    private String agentNo;

    private String manager;

    private String servicePhone;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String mercPicsPath;

    private String remark;

    private BigDecimal creditAmt;

    private String merMode;

    private Integer capital;

    private String busiLicPath;

    private String merIdRxm;

    private String merIdGhm;

    private String merSyt;

    private String merHjz;

    private String merMtz;

    private BigDecimal allowAmt;

    private BigDecimal paySingleLimitAmt;

    private BigDecimal payDailyLimitAmt;

    private String payFlag;

    private BigDecimal singleLimitAmt;

    private BigDecimal dailyLimitAmt;

    private String businForm;

    private String approvers;

    private String merLegal;

    private String legalType;

    private String legalCode;

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getMercName() {
        return mercName;
    }

    public void setMercName(String mercName) {
        this.mercName = mercName == null ? null : mercName.trim();
    }

    public String getMercNickName() {
        return mercNickName;
    }

    public void setMercNickName(String mercNickName) {
        this.mercNickName = mercNickName == null ? null : mercNickName.trim();
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath == null ? null : publicKeyPath.trim();
    }

    public String getMercSignType() {
        return mercSignType;
    }

    public void setMercSignType(String mercSignType) {
        this.mercSignType = mercSignType == null ? null : mercSignType.trim();
    }

    public String getMercPrivateKey() {
        return mercPrivateKey;
    }

    public void setMercPrivateKey(String mercPrivateKey) {
        this.mercPrivateKey = mercPrivateKey == null ? null : mercPrivateKey.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMccId() {
        return mccId;
    }

    public void setMccId(String mccId) {
        this.mccId = mccId == null ? null : mccId.trim();
    }

    public String getMerArea() {
        return merArea;
    }

    public void setMerArea(String merArea) {
        this.merArea = merArea == null ? null : merArea.trim();
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getContractPath() {
        return contractPath;
    }

    public void setContractPath(String contractPath) {
        this.contractPath = contractPath == null ? null : contractPath.trim();
    }

    public String getMercBusiLic() {
        return mercBusiLic;
    }

    public void setMercBusiLic(String mercBusiLic) {
        this.mercBusiLic = mercBusiLic == null ? null : mercBusiLic.trim();
    }

    public String getLicValiDate() {
        return licValiDate;
    }

    public void setLicValiDate(String licValiDate) {
        this.licValiDate = licValiDate;
    }

    public String getBusiLicFileName() {
        return busiLicFileName;
    }

    public void setBusiLicFileName(String busiLicFileName) {
        this.busiLicFileName = busiLicFileName == null ? null : busiLicFileName.trim();
    }

    public String getLegalValidity() {
        return legalValidity;
    }

    public void setLegalValidity(String legalValidity) {
        this.legalValidity = legalValidity == null ? null : legalValidity.trim();
    }

    public String getCorpReptName() {
        return corpReptName;
    }

    public void setCorpReptName(String corpReptName) {
        this.corpReptName = corpReptName == null ? null : corpReptName.trim();
    }

    public String getCorpReptId() {
        return corpReptId;
    }

    public void setCorpReptId(String corpReptId) {
        this.corpReptId = corpReptId == null ? null : corpReptId.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone == null ? null : fixedPhone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCorpAddr() {
        return corpAddr;
    }

    public void setCorpAddr(String corpAddr) {
        this.corpAddr = corpAddr == null ? null : corpAddr.trim();
    }

    public String getOriPasswd() {
        return oriPasswd;
    }

    public void setOriPasswd(String oriPasswd) {
        this.oriPasswd = oriPasswd == null ? null : oriPasswd.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getPasswdFlag() {
        return passwdFlag;
    }

    public void setPasswdFlag(String passwdFlag) {
        this.passwdFlag = passwdFlag == null ? null : passwdFlag.trim();
    }

    public Integer getWrongCounts() {
        return wrongCounts;
    }

    public void setWrongCounts(Integer wrongCounts) {
        this.wrongCounts = wrongCounts;
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getSendBillFlag() {
        return sendBillFlag;
    }

    public void setSendBillFlag(String sendBillFlag) {
        this.sendBillFlag = sendBillFlag == null ? null : sendBillFlag.trim();
    }

    public String getMercSettAcctType() {
        return mercSettAcctType;
    }

    public void setMercSettAcctType(String mercSettAcctType) {
        this.mercSettAcctType = mercSettAcctType == null ? null : mercSettAcctType.trim();
    }

    public String getMercSettAcct() {
        return mercSettAcct;
    }

    public void setMercSettAcct(String mercSettAcct) {
        this.mercSettAcct = mercSettAcct == null ? null : mercSettAcct.trim();
    }

    public String getMercSettAcctNm() {
        return mercSettAcctNm;
    }

    public void setMercSettAcctNm(String mercSettAcctNm) {
        this.mercSettAcctNm = mercSettAcctNm == null ? null : mercSettAcctNm.trim();
    }

    public String getMercSettBank() {
        return mercSettBank;
    }

    public void setMercSettBank(String mercSettBank) {
        this.mercSettBank = mercSettBank == null ? null : mercSettBank.trim();
    }

    public String getMercSettBankNm() {
        return mercSettBankNm;
    }

    public void setMercSettBankNm(String mercSettBankNm) {
        this.mercSettBankNm = mercSettBankNm == null ? null : mercSettBankNm.trim();
    }

    public String getMercSettBankPro() {
        return mercSettBankPro;
    }

    public void setMercSettBankPro(String mercSettBankPro) {
        this.mercSettBankPro = mercSettBankPro == null ? null : mercSettBankPro.trim();
    }

    public String getMercSettBankCity() {
        return mercSettBankCity;
    }

    public void setMercSettBankCity(String mercSettBankCity) {
        this.mercSettBankCity = mercSettBankCity == null ? null : mercSettBankCity.trim();
    }

    public String getClearCycle() {
        return clearCycle;
    }

    public void setClearCycle(String clearCycle) {
        this.clearCycle = clearCycle == null ? null : clearCycle.trim();
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate == null ? null : clearDate.trim();
    }

    public String getClearRate() {
        return clearRate;
    }

    public void setClearRate(String clearRate) {
        this.clearRate = clearRate;
    }

    public String getClearFlag() {
        return clearFlag;
    }

    public void setClearFlag(String clearFlag) {
        this.clearFlag = clearFlag == null ? null : clearFlag.trim();
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo == null ? null : agentNo.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone == null ? null : servicePhone.trim();
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

    public String getMercPicsPath() {
        return mercPicsPath;
    }

    public void setMercPicsPath(String mercPicsPath) {
        this.mercPicsPath = mercPicsPath == null ? null : mercPicsPath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getMerMode() {
        return merMode;
    }

    public void setMerMode(String merMode) {
        this.merMode = merMode == null ? null : merMode.trim();
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getBusiLicPath() {
        return busiLicPath;
    }

    public void setBusiLicPath(String busiLicPath) {
        this.busiLicPath = busiLicPath == null ? null : busiLicPath.trim();
    }

    public String getMerIdRxm() {
        return merIdRxm;
    }

    public void setMerIdRxm(String merIdRxm) {
        this.merIdRxm = merIdRxm == null ? null : merIdRxm.trim();
    }

    public String getMerIdGhm() {
        return merIdGhm;
    }

    public void setMerIdGhm(String merIdGhm) {
        this.merIdGhm = merIdGhm == null ? null : merIdGhm.trim();
    }

    public String getMerSyt() {
        return merSyt;
    }

    public void setMerSyt(String merSyt) {
        this.merSyt = merSyt == null ? null : merSyt.trim();
    }

    public String getMerHjz() {
        return merHjz;
    }

    public void setMerHjz(String merHjz) {
        this.merHjz = merHjz == null ? null : merHjz.trim();
    }

    public String getMerMtz() {
        return merMtz;
    }

    public void setMerMtz(String merMtz) {
        this.merMtz = merMtz == null ? null : merMtz.trim();
    }

    public BigDecimal getAllowAmt() {
        return allowAmt;
    }

    public void setAllowAmt(BigDecimal allowAmt) {
        this.allowAmt = allowAmt;
    }

    public BigDecimal getPaySingleLimitAmt() {
        return paySingleLimitAmt;
    }

    public void setPaySingleLimitAmt(BigDecimal paySingleLimitAmt) {
        this.paySingleLimitAmt = paySingleLimitAmt;
    }

    public BigDecimal getPayDailyLimitAmt() {
        return payDailyLimitAmt;
    }

    public void setPayDailyLimitAmt(BigDecimal payDailyLimitAmt) {
        this.payDailyLimitAmt = payDailyLimitAmt;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag == null ? null : payFlag.trim();
    }

    public BigDecimal getSingleLimitAmt() {
        return singleLimitAmt;
    }

    public void setSingleLimitAmt(BigDecimal singleLimitAmt) {
        this.singleLimitAmt = singleLimitAmt;
    }

    public BigDecimal getDailyLimitAmt() {
        return dailyLimitAmt;
    }

    public void setDailyLimitAmt(BigDecimal dailyLimitAmt) {
        this.dailyLimitAmt = dailyLimitAmt;
    }

    public String getBusinForm() {
        return businForm;
    }

    public void setBusinForm(String businForm) {
        this.businForm = businForm == null ? null : businForm.trim();
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers == null ? null : approvers.trim();
    }

    public String getMerLegal() {
        return merLegal;
    }

    public void setMerLegal(String merLegal) {
        this.merLegal = merLegal == null ? null : merLegal.trim();
    }

    public String getLegalType() {
        return legalType;
    }

    public void setLegalType(String legalType) {
        this.legalType = legalType == null ? null : legalType.trim();
    }

    public String getLegalCode() {
        return legalCode;
    }

    public void setLegalCode(String legalCode) {
        this.legalCode = legalCode == null ? null : legalCode.trim();
    }
}