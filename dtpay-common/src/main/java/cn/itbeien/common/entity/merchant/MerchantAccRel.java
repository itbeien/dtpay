package cn.itbeien.common.entity.merchant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantAccRel implements Serializable {
    private String id;

    private String mercNo;

    private String mercSettAcct;

    private String corePayAcct;

    private String coreAcctName;

    private String coreAcctStatus;

    private String coreAcctType;

    private BigDecimal acctBal;

    private BigDecimal acctAvaiBal;

    private BigDecimal freezeBal;

    private String ccy;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private BigDecimal feeBal;

    private BigDecimal inAmt;

    private BigDecimal outAmt;
    
    private BigDecimal freezeAmt;
    
    private BigDecimal freezeTotalAmt;

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

    public String getMercSettAcct() {
        return mercSettAcct;
    }

    public void setMercSettAcct(String mercSettAcct) {
        this.mercSettAcct = mercSettAcct == null ? null : mercSettAcct.trim();
    }

    public String getCorePayAcct() {
        return corePayAcct;
    }

    public void setCorePayAcct(String corePayAcct) {
        this.corePayAcct = corePayAcct == null ? null : corePayAcct.trim();
    }

    public String getCoreAcctName() {
        return coreAcctName;
    }

    public void setCoreAcctName(String coreAcctName) {
        this.coreAcctName = coreAcctName == null ? null : coreAcctName.trim();
    }

    public String getCoreAcctStatus() {
        return coreAcctStatus;
    }

    public void setCoreAcctStatus(String coreAcctStatus) {
        this.coreAcctStatus = coreAcctStatus == null ? null : coreAcctStatus.trim();
    }

    public String getCoreAcctType() {
        return coreAcctType;
    }

    public void setCoreAcctType(String coreAcctType) {
        this.coreAcctType = coreAcctType == null ? null : coreAcctType.trim();
    }

    public BigDecimal getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(BigDecimal acctBal) {
        this.acctBal = acctBal;
    }

    public BigDecimal getAcctAvaiBal() {
        return acctAvaiBal;
    }

    public void setAcctAvaiBal(BigDecimal acctAvaiBal) {
        this.acctAvaiBal = acctAvaiBal;
    }

    public BigDecimal getFreezeBal() {
        return freezeBal;
    }

    public void setFreezeBal(BigDecimal freezeBal) {
        this.freezeBal = freezeBal;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
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

    public BigDecimal getFeeBal() {
        return feeBal;
    }

    public void setFeeBal(BigDecimal feeBal) {
        this.feeBal = feeBal;
    }

    public BigDecimal getInAmt() {
        return inAmt;
    }

    public void setInAmt(BigDecimal inAmt) {
        this.inAmt = inAmt;
    }

    public BigDecimal getOutAmt() {
        return outAmt;
    }

    public void setOutAmt(BigDecimal outAmt) {
        this.outAmt = outAmt;
    }

	public BigDecimal getFreezeAmt() {
		return freezeAmt;
	}

	public void setFreezeAmt(BigDecimal freezeAmt) {
		this.freezeAmt = freezeAmt;
	}

	public BigDecimal getFreezeTotalAmt() {
		return freezeTotalAmt;
	}

	public void setFreezeTotalAmt(BigDecimal freezeTotalAmt) {
		this.freezeTotalAmt = freezeTotalAmt;
	}
    
}