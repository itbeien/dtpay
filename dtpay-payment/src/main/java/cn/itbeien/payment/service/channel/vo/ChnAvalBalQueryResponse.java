package cn.itbeien.payment.service.channel.vo;

import java.math.BigDecimal;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class ChnAvalBalQueryResponse extends ChannelVo {
	
    private BigDecimal avalBal;

	public BigDecimal getAvalBal() {
		return avalBal;
	}

	public void setAvalBal(BigDecimal avalBal) {
		this.avalBal = avalBal;
	}

	@Override
	public String toString() {
		return "ChnAvalBalQueryResponse [avalBal=" + avalBal + "]";
	}
	
}
