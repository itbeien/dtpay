package cn.itbeien.payment.core.vo.response;

import cn.itbeien.payment.core.vo.TradeResponse;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class AvailBalQueryResponse extends TradeResponse {
	
	private static final long serialVersionUID = 1L;
	
	private String acctBal;
	
	private String acctAvaiBal;
	
	private String freezeBal;

	public String getAcctBal() {
		return acctBal;
	}

	public void setAcctBal(String acctBal) {
		this.acctBal = acctBal;
	}

	public String getAcctAvaiBal() {
		return acctAvaiBal;
	}

	public void setAcctAvaiBal(String acctAvaiBal) {
		this.acctAvaiBal = acctAvaiBal;
	}

	public String getFreezeBal() {
		return freezeBal;
	}

	public void setFreezeBal(String freezeBal) {
		this.freezeBal = freezeBal;
	}
	
}
