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
public class MerchantRate implements Serializable {
    private String rateId;

    private String mercNo;

    private String paywayCode;

    private String sceneCode;

    private BigDecimal floorAmount;

    private BigDecimal upperAmount;

    private String feeFlag;

    private BigDecimal feeValue;

    private BigDecimal feeMin;

    private BigDecimal feeMax;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private static final long serialVersionUID = 1L;

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId == null ? null : rateId.trim();
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

    public BigDecimal getFloorAmount() {
        return floorAmount;
    }

    public void setFloorAmount(BigDecimal floorAmount) {
        this.floorAmount = floorAmount;
    }

    public BigDecimal getUpperAmount() {
        return upperAmount;
    }

    public void setUpperAmount(BigDecimal upperAmount) {
        this.upperAmount = upperAmount;
    }

    public String getFeeFlag() {
        return feeFlag;
    }

    public void setFeeFlag(String feeFlag) {
        this.feeFlag = feeFlag == null ? null : feeFlag.trim();
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public BigDecimal getFeeMin() {
        return feeMin;
    }

    public void setFeeMin(BigDecimal feeMin) {
        this.feeMin = feeMin;
    }

    public BigDecimal getFeeMax() {
        return feeMax;
    }

    public void setFeeMax(BigDecimal feeMax) {
        this.feeMax = feeMax;
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
}