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
public class MerchantPaywayMapping implements Serializable {
    private String id;

    private String mercNo;

    private String paywayCode;

    private String sceneCode;

    private String status;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private BigDecimal singleLimitAmt;

    private BigDecimal dailyLimitAmt;

    private String mercFeeFlag;

    private BigDecimal mercFeeValue;

    private BigDecimal mercFeeMin;

    private BigDecimal mercFeeMax;
    
    private BigDecimal allowAmt;
    
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getPaywayCode() {
        return paywayCode;
    }

    public void setPaywayCode(String paywayCode) {
        this.paywayCode = paywayCode == null ? null : paywayCode.trim();
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode == null ? null : sceneCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getMercFeeFlag() {
        return mercFeeFlag;
    }

    public void setMercFeeFlag(String mercFeeFlag) {
        this.mercFeeFlag = mercFeeFlag == null ? null : mercFeeFlag.trim();
    }

    public BigDecimal getMercFeeValue() {
        return mercFeeValue;
    }

    public void setMercFeeValue(BigDecimal mercFeeValue) {
        this.mercFeeValue = mercFeeValue;
    }

    public BigDecimal getMercFeeMin() {
        return mercFeeMin;
    }

    public void setMercFeeMin(BigDecimal mercFeeMin) {
        this.mercFeeMin = mercFeeMin;
    }

    public BigDecimal getMercFeeMax() {
        return mercFeeMax;
    }

    public void setMercFeeMax(BigDecimal mercFeeMax) {
        this.mercFeeMax = mercFeeMax;
    }

	public BigDecimal getAllowAmt() {
		return allowAmt;
	}

	public void setAllowAmt(BigDecimal allowAmt) {
		this.allowAmt = allowAmt;
	}
    
}