package cn.itbeien.common.core.vo.request;


import cn.itbeien.common.core.validator.EnumValue;
import cn.itbeien.common.core.vo.TradeRequest;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Data
public class PayRequest extends TradeRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mercOrderNo;
	
	private String subject;
	
	private String body;
	
	private String tradeType;
	/**
	 *支付金额 单位：分
	 */
	@NotNull(message="支付金额不能为空")
	@Min(value = 1, message = "支付金额不能为空")
	private Long tradeAmt;
	/**
	 *币种，默认cny
	 */
	private String feeType;
	/**
	 * 提交支付时间
	 */
	private String tradeTime;
	/**
	 * 后台通知地址
	 */
	private String notifyUrl;
	/**
	 * 前台通知地址
	 */
	private String returnUrl;
	
	private String refererUrl;
	/**
	 * 终端接入类型 wap,h5,pc,app
	 */
	@EnumValue(strValues = {"wap","h5","pc","app"}, message = "终端接入类型不能为空")
	private String termType;
	/**
	 * 支付方式 @see
	 */
	private String paywayCode;

	/**
	 * 支付场景
	 */
	private String sceneCode;
	/**
	 * 客户端IP
	 */
	private String ip;
	/*
	商户编号
	 */
	private String merchantId;
	/**
	 * 门店编号
	 */
	private String storeId;
	/**
	 * 收银员编号
	 */
	private String payeeId;
	/**
	 * 附加信息
	 */
	private String attach;
	
	private String deviceInfo;
	
	private String mchAppName;
	
	private String mchAppId;
	
	private String quickPayAttach;
	
	private String remark;
	
	private String orderPeriod;
	
	private String noticeStr;
	
	private String payingMercNo;
	
	private String bankCode;
	
	private String orderId;
	
	private String payType;
	


}
