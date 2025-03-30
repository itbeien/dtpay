package cn.itbeien.payment.service.route.model;

import cn.itbeien.payment.core.IDtPayService;

import java.math.BigDecimal;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class RouteResultModel {
	
	private String channelCode;
	
	private String paywayCode;
	
	private String scenesCode;
	
	private String serviceBeanId;
	
	private IDtPayService rxService;
	
	/**
	 * 保存到记录，用于区分上游通知
	 */
	private String channelType;
	
	private BigDecimal payFeeValue;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getPaywayCode() {
		return paywayCode;
	}

	public void setPaywayCode(String paywayCode) {
		this.paywayCode = paywayCode;
	}

	public String getScenesCode() {
		return scenesCode;
	}

	public void setScenesCode(String scenesCode) {
		this.scenesCode = scenesCode;
	}
	
	public String getServiceBeanId() {
		return serviceBeanId;
	}

	public void setServiceBeanId(String serviceBeanId) {
		this.serviceBeanId = serviceBeanId;
	}
	
	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public IDtPayService getRxService() {
		return rxService;
	}

	public void setRxService(IDtPayService rxService) {
		this.rxService = rxService;
	}

	public BigDecimal getPayFeeValue() {
		return payFeeValue;
	}

	public void setPayFeeValue(BigDecimal payFeeValue) {
		this.payFeeValue = payFeeValue;
	}
	
}
