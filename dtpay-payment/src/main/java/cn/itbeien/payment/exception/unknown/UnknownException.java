package cn.itbeien.payment.exception.unknown;

import cn.itbeien.payment.exception.TradeException;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class UnknownException extends TradeException {

	private static final long serialVersionUID = -2822556663409826132L;

	
	public UnknownException()
	{
		super();
	}
	
	public UnknownException(String errorMsg)
	{
		super(errorMsg);
	}
	
	
	public UnknownException(String errorCode ,String errorMsg)
	{
		super(errorCode ,errorMsg);
	}

}
