package cn.itbeien.common.entity.merchant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MercAdjustDetail implements Serializable {
    private String id;

    private String mercNo;

    private String mercName;

    private String mercOrderNo;
    
    private String orderId;
    private String bankTradeSeq;

    private String channelCode;

    private BigDecimal adjustAmt;

    private String remark;

    private BigDecimal beforeAvaiBal;

    private BigDecimal accAvaiBal;

    private String oldStatus;

    private String newStatus;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String approveRemark;

    private Date approveTime;

    private String approver;

    private String apprStatus;
    
    private String dynamic;

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

    public String getMercName() {
        return mercName;
    }

    public void setMercName(String mercName) {
        this.mercName = mercName == null ? null : mercName.trim();
    }

    public String getMercOrderNo() {
        return mercOrderNo;
    }

    public void setMercOrderNo(String mercOrderNo) {
        this.mercOrderNo = mercOrderNo == null ? null : mercOrderNo.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getBeforeAvaiBal() {
        return beforeAvaiBal;
    }

    public void setBeforeAvaiBal(BigDecimal beforeAvaiBal) {
        this.beforeAvaiBal = beforeAvaiBal;
    }

    public BigDecimal getAccAvaiBal() {
        return accAvaiBal;
    }

    public void setAccAvaiBal(BigDecimal accAvaiBal) {
        this.accAvaiBal = accAvaiBal;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus == null ? null : oldStatus.trim();
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus == null ? null : newStatus.trim();
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

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark == null ? null : approveRemark.trim();
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getApprStatus() {
        return apprStatus;
    }

    public void setApprStatus(String apprStatus) {
        this.apprStatus = apprStatus == null ? null : apprStatus.trim();
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getBankTradeSeq() {
		return bankTradeSeq;
	}

	public void setBankTradeSeq(String bankTradeSeq) {
		this.bankTradeSeq = bankTradeSeq == null ? null : bankTradeSeq.trim();
	}

	public String getDynamic() {
		return dynamic;
	}

	public void setDynamic(String dynamic) {
		this.dynamic = dynamic == null ? null : dynamic.trim();
	}
}