package cn.itbeien.common.entity.merchant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantChannelBal extends MerchantChannelBalKey implements Serializable {
    private String payingMercName;

    private BigDecimal chnBal;

    private BigDecimal chnAvaiBal;

    private BigDecimal chnFreezeBal;

    private String ccy;

    private Date createTime;

    private BigDecimal chnFeeBal;

    private BigDecimal chnInAmt;

    private BigDecimal chnOutAmt;

    private Integer sort;

    private static final long serialVersionUID = 1L;
    
    private BigDecimal failChnAvaiBal;
    
    private BigDecimal failChnFreezeBal;

    public String getPayingMercName() {
        return payingMercName;
    }

    public void setPayingMercName(String payingMercName) {
        this.payingMercName = payingMercName == null ? null : payingMercName.trim();
    }

    public BigDecimal getChnBal() {
        return chnBal;
    }

    public void setChnBal(BigDecimal chnBal) {
        this.chnBal = chnBal;
    }

    public BigDecimal getChnAvaiBal() {
        return chnAvaiBal;
    }

    public void setChnAvaiBal(BigDecimal chnAvaiBal) {
        this.chnAvaiBal = chnAvaiBal;
    }

    public BigDecimal getChnFreezeBal() {
        return chnFreezeBal;
    }

    public void setChnFreezeBal(BigDecimal chnFreezeBal) {
        this.chnFreezeBal = chnFreezeBal;
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

    public BigDecimal getChnFeeBal() {
        return chnFeeBal;
    }

    public void setChnFeeBal(BigDecimal chnFeeBal) {
        this.chnFeeBal = chnFeeBal;
    }

    public BigDecimal getChnInAmt() {
        return chnInAmt;
    }

    public void setChnInAmt(BigDecimal chnInAmt) {
        this.chnInAmt = chnInAmt;
    }

    public BigDecimal getChnOutAmt() {
        return chnOutAmt;
    }

    public void setChnOutAmt(BigDecimal chnOutAmt) {
        this.chnOutAmt = chnOutAmt;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public BigDecimal getFailChnAvaiBal() {
		return failChnAvaiBal;
	}

	public void setFailChnAvaiBal(BigDecimal failChnAvaiBal) {
		this.failChnAvaiBal = failChnAvaiBal;
	}

	public BigDecimal getFailChnFreezeBal() {
		return failChnFreezeBal;
	}

	public void setFailChnFreezeBal(BigDecimal failChnFreezeBal) {
		this.failChnFreezeBal = failChnFreezeBal;
	}
    
}