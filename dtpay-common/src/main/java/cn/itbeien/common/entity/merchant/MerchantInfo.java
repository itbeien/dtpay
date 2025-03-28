package cn.itbeien.common.entity.merchant;

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
public class MerchantInfo implements Serializable {
    private String mercNo;

    private String mercName;

    private String mercNickName;

    private String publicKeyPath;

    private String upMercNo;

    private String status;

    private String linkman;

    private String telphone;

    private String email;

    private String address;

    private String busiFirstScope;

    private String busiSecondScope;

    private String busiThirdScope;

    private String contractNo;

    private String contractPath;

    private String mercBusiLic;
    
    private String mercSignType;
    private String mercPrivateKey;

    private Date licValiDate;

    private String busiLicFileName;

    private String busiLicPath;

    private String mercOrgCode;

    private String orgCodeFileName;

    private String orgCodePath;

    private String mercTaxCode;

    private String taxCodeFileName;

    private String taxCodePath;

    private String mercType;

    private String corpReptName;

    private String corpReptId;

    private String corpReptPath;

    private String postCode;

    private String fixedPhone;

    private String province;

    private String city;

    private String corpAddr;

    private String oriPasswd;

    private String defaultFlag;

    private String passwd;

    private String passwdFlag;

    private Integer wrongCounts;

    private Date lockedTime;

    private String sendBillFlag;

    private String settleType;

    private String mercPayAcctType;

    private String mercPayAcct;

    private String mercPayAcctNm;

    private String mercPayBank;

    private String mercPayBankNm;

    private String mercPayBankPro;

    private String mercPayBankCity;

    private String mercSettAcctType;

    private String mercSettAcct;

    private String mercSettAcctNm;

    private String mercSettBank;

    private String mercSettBankNm;

    private String mercSettBankPro;

    private String mercSettBankCity;

    private String corePayAcct;

    private String jsType;

    private String clearCycle;

    private String clearDate;

    private BigDecimal clearRate;

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

    private String custType;

    private String mercPayBankInf;

    private String mercSettBankInf;

    private String seller;

    private String mercProduct;

    private String mercUrl;

    private String payType;

    private String mercScaleRate;

    private BigDecimal allowAmt;

    private String payFlag;

    private String payFeeFlag;

    private BigDecimal payFeeValue;

    private BigDecimal singleLimitAmt;

    private BigDecimal dailyLimitAmt;

    private String triFlag;

    private String approvers;

    private String rsfld1;

    private String rsfld2;

    private String rsfld3;
    
    private String id;
    private String sceneName;
    private String paywayName;
    private BigDecimal mercFeeValue;
    private String rate;
    private BigDecimal t0;
    private BigDecimal t1;
    private BigDecimal d0;
    private BigDecimal d1;
    private String paywayCode;
    private String sceneCode;
    
    private String staFlag;
    private String mercNoFlag;
    
    private String proId;
    
    private String cusUserName;
    
    private static final long serialVersionUID = 1L;

    public String getMercNo() {
        return mercNo;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    public String getUpMercNo() {
        return upMercNo;
    }

    public void setUpMercNo(String upMercNo) {
        this.upMercNo = upMercNo == null ? null : upMercNo.trim();
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

    public String getBusiFirstScope() {
        return busiFirstScope;
    }

    public void setBusiFirstScope(String busiFirstScope) {
        this.busiFirstScope = busiFirstScope == null ? null : busiFirstScope.trim();
    }

    public String getBusiSecondScope() {
        return busiSecondScope;
    }

    public void setBusiSecondScope(String busiSecondScope) {
        this.busiSecondScope = busiSecondScope == null ? null : busiSecondScope.trim();
    }

    public String getBusiThirdScope() {
        return busiThirdScope;
    }

    public void setBusiThirdScope(String busiThirdScope) {
        this.busiThirdScope = busiThirdScope == null ? null : busiThirdScope.trim();
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

    public Date getLicValiDate() {
        return licValiDate;
    }

    public void setLicValiDate(Date licValiDate) {
        this.licValiDate = licValiDate;
    }

    public String getBusiLicFileName() {
        return busiLicFileName;
    }

    public void setBusiLicFileName(String busiLicFileName) {
        this.busiLicFileName = busiLicFileName == null ? null : busiLicFileName.trim();
    }

    public String getBusiLicPath() {
        return busiLicPath;
    }

    public void setBusiLicPath(String busiLicPath) {
        this.busiLicPath = busiLicPath == null ? null : busiLicPath.trim();
    }

    public String getMercOrgCode() {
        return mercOrgCode;
    }

    public void setMercOrgCode(String mercOrgCode) {
        this.mercOrgCode = mercOrgCode == null ? null : mercOrgCode.trim();
    }

    public String getOrgCodeFileName() {
        return orgCodeFileName;
    }

    public void setOrgCodeFileName(String orgCodeFileName) {
        this.orgCodeFileName = orgCodeFileName == null ? null : orgCodeFileName.trim();
    }

    public String getOrgCodePath() {
        return orgCodePath;
    }

    public void setOrgCodePath(String orgCodePath) {
        this.orgCodePath = orgCodePath == null ? null : orgCodePath.trim();
    }

    public String getMercTaxCode() {
        return mercTaxCode;
    }

    public void setMercTaxCode(String mercTaxCode) {
        this.mercTaxCode = mercTaxCode == null ? null : mercTaxCode.trim();
    }

    public String getTaxCodeFileName() {
        return taxCodeFileName;
    }

    public void setTaxCodeFileName(String taxCodeFileName) {
        this.taxCodeFileName = taxCodeFileName == null ? null : taxCodeFileName.trim();
    }

    public String getTaxCodePath() {
        return taxCodePath;
    }

    public void setTaxCodePath(String taxCodePath) {
        this.taxCodePath = taxCodePath == null ? null : taxCodePath.trim();
    }

    public String getMercType() {
        return mercType;
    }

    public void setMercType(String mercType) {
        this.mercType = mercType == null ? null : mercType.trim();
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

    public String getCorpReptPath() {
        return corpReptPath;
    }

    public void setCorpReptPath(String corpReptPath) {
        this.corpReptPath = corpReptPath == null ? null : corpReptPath.trim();
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

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag == null ? null : defaultFlag.trim();
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

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType == null ? null : settleType.trim();
    }

    public String getMercPayAcctType() {
        return mercPayAcctType;
    }

    public void setMercPayAcctType(String mercPayAcctType) {
        this.mercPayAcctType = mercPayAcctType == null ? null : mercPayAcctType.trim();
    }

    public String getMercPayAcct() {
        return mercPayAcct;
    }

    public void setMercPayAcct(String mercPayAcct) {
        this.mercPayAcct = mercPayAcct == null ? null : mercPayAcct.trim();
    }

    public String getMercPayAcctNm() {
        return mercPayAcctNm;
    }

    public void setMercPayAcctNm(String mercPayAcctNm) {
        this.mercPayAcctNm = mercPayAcctNm == null ? null : mercPayAcctNm.trim();
    }

    public String getMercPayBank() {
        return mercPayBank;
    }

    public void setMercPayBank(String mercPayBank) {
        this.mercPayBank = mercPayBank == null ? null : mercPayBank.trim();
    }

    public String getMercPayBankNm() {
        return mercPayBankNm;
    }

    public void setMercPayBankNm(String mercPayBankNm) {
        this.mercPayBankNm = mercPayBankNm == null ? null : mercPayBankNm.trim();
    }

    public String getMercPayBankPro() {
        return mercPayBankPro;
    }

    public void setMercPayBankPro(String mercPayBankPro) {
        this.mercPayBankPro = mercPayBankPro == null ? null : mercPayBankPro.trim();
    }

    public String getMercPayBankCity() {
        return mercPayBankCity;
    }

    public void setMercPayBankCity(String mercPayBankCity) {
        this.mercPayBankCity = mercPayBankCity == null ? null : mercPayBankCity.trim();
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

    public String getCorePayAcct() {
        return corePayAcct;
    }

    public void setCorePayAcct(String corePayAcct) {
        this.corePayAcct = corePayAcct == null ? null : corePayAcct.trim();
    }

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType == null ? null : jsType.trim();
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

    public BigDecimal getClearRate() {
        return clearRate;
    }

    public void setClearRate(BigDecimal clearRate) {
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

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType == null ? null : custType.trim();
    }

    public String getMercPayBankInf() {
        return mercPayBankInf;
    }

    public void setMercPayBankInf(String mercPayBankInf) {
        this.mercPayBankInf = mercPayBankInf == null ? null : mercPayBankInf.trim();
    }

    public String getMercSettBankInf() {
        return mercSettBankInf;
    }

    public void setMercSettBankInf(String mercSettBankInf) {
        this.mercSettBankInf = mercSettBankInf == null ? null : mercSettBankInf.trim();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getMercProduct() {
        return mercProduct;
    }

    public void setMercProduct(String mercProduct) {
        this.mercProduct = mercProduct == null ? null : mercProduct.trim();
    }

    public String getMercUrl() {
        return mercUrl;
    }

    public void setMercUrl(String mercUrl) {
        this.mercUrl = mercUrl == null ? null : mercUrl.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getMercScaleRate() {
        return mercScaleRate;
    }

    public void setMercScaleRate(String mercScaleRate) {
        this.mercScaleRate = mercScaleRate == null ? null : mercScaleRate.trim();
    }

    public BigDecimal getAllowAmt() {
        return allowAmt;
    }

    public void setAllowAmt(BigDecimal allowAmt) {
        this.allowAmt = allowAmt;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag == null ? null : payFlag.trim();
    }

    public String getPayFeeFlag() {
        return payFeeFlag;
    }

    public void setPayFeeFlag(String payFeeFlag) {
        this.payFeeFlag = payFeeFlag == null ? null : payFeeFlag.trim();
    }

    public BigDecimal getPayFeeValue() {
        return payFeeValue;
    }

    public void setPayFeeValue(BigDecimal payFeeValue) {
        this.payFeeValue = payFeeValue;
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

    public String getTriFlag() {
        return triFlag;
    }

    public void setTriFlag(String triFlag) {
        this.triFlag = triFlag == null ? null : triFlag.trim();
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers == null ? null : approvers.trim();
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

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getPaywayName() {
		return paywayName;
	}

	public void setPaywayName(String paywayName) {
		this.paywayName = paywayName;
	}

	public BigDecimal getMercFeeValue() {
		return mercFeeValue;
	}

	public void setMercFeeValue(BigDecimal mercFeeValue) {
		this.mercFeeValue = mercFeeValue;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public BigDecimal getT0() {
		return t0;
	}

	public void setT0(BigDecimal t0) {
		this.t0 = t0;
	}

	public BigDecimal getT1() {
		return t1;
	}

	public void setT1(BigDecimal t1) {
		this.t1 = t1;
	}

	public BigDecimal getD0() {
		return d0;
	}

	public void setD0(BigDecimal d0) {
		this.d0 = d0;
	}

	public BigDecimal getD1() {
		return d1;
	}

	public void setD1(BigDecimal d1) {
		this.d1 = d1;
	}

	public String getPaywayCode() {
		return paywayCode;
	}

	public void setPaywayCode(String paywayCode) {
		this.paywayCode = paywayCode;
	}

	public String getSceneCode() {
		return sceneCode;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}

	public String getMercSignType() {
		return mercSignType;
	}

	public void setMercSignType(String mercSignType) {
		this.mercSignType = mercSignType;
	}

	public String getMercPrivateKey() {
		return mercPrivateKey;
	}

	public void setMercPrivateKey(String mercPrivateKey) {
		this.mercPrivateKey = mercPrivateKey;
	}

	public String getStaFlag() {
		return staFlag;
	}

	public void setStaFlag(String staFlag) {
		this.staFlag = staFlag;
	}

	public String getMercNoFlag() {
		return mercNoFlag;
	}

	public void setMercNoFlag(String mercNoFlag) {
		this.mercNoFlag = mercNoFlag;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getCusUserName() {
		return cusUserName;
	}

	public void setCusUserName(String cusUserName) {
		this.cusUserName = cusUserName;
	}

}