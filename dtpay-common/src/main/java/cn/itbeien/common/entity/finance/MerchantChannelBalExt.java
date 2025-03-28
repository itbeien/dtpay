package cn.itbeien.common.entity.finance;

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
public class MerchantChannelBalExt extends MerchantChannelBalKey implements Serializable {
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
    
    private String payType;

    private static final long serialVersionUID = 1L;

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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}