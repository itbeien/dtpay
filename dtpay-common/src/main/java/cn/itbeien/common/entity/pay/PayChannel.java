package cn.itbeien.common.entity.pay;

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
public class PayChannel implements Serializable {
    private String channelCode;

    private String channelName;

    private String chnNickName;

    private String status;

    private String connectSwitch;

    private String sceneCodes;

    private String serviceBeanId;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String chnScaleRate;

    private BigDecimal allowAmt;

    private String payFlag;

    private String payFeeFlag;

    private BigDecimal payFeeValue;

    private BigDecimal singleLimitAmt;

    private BigDecimal dailyLimitAmt;

    private String triFlag;

    private String rsfld1;

    private String rsfld2;

    private String rsfld3;

    private String rsfld4;

    private String rsfld5;

    private String payType;
    
    private BigDecimal minFeeThreshold;

    private BigDecimal otherFeeThreshold;

    private static final long serialVersionUID = 1L;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChnNickName() {
        return chnNickName;
    }

    public void setChnNickName(String chnNickName) {
        this.chnNickName = chnNickName == null ? null : chnNickName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getConnectSwitch() {
        return connectSwitch;
    }

    public void setConnectSwitch(String connectSwitch) {
        this.connectSwitch = connectSwitch == null ? null : connectSwitch.trim();
    }

    public String getSceneCodes() {
        return sceneCodes;
    }

    public void setSceneCodes(String sceneCodes) {
        this.sceneCodes = sceneCodes == null ? null : sceneCodes.trim();
    }

    public String getServiceBeanId() {
        return serviceBeanId;
    }

    public void setServiceBeanId(String serviceBeanId) {
        this.serviceBeanId = serviceBeanId == null ? null : serviceBeanId.trim();
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

    public String getChnScaleRate() {
        return chnScaleRate;
    }

    public void setChnScaleRate(String chnScaleRate) {
        this.chnScaleRate = chnScaleRate == null ? null : chnScaleRate.trim();
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

	public BigDecimal getMinFeeThreshold() {
		return minFeeThreshold;
	}

	public void setMinFeeThreshold(BigDecimal minFeeThreshold) {
		this.minFeeThreshold = minFeeThreshold;
	}

	public BigDecimal getOtherFeeThreshold() {
		return otherFeeThreshold;
	}

	public void setOtherFeeThreshold(BigDecimal otherFeeThreshold) {
		this.otherFeeThreshold = otherFeeThreshold;
	}
    
    
}