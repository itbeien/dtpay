package cn.itbeien.payment.exception.unknown;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class TradeTimeoutException extends UnknownException{

	private static final long serialVersionUID = -4778515638630709767L;
	
	public TradeTimeoutException()
	{
		super();
	}
	
	public TradeTimeoutException(String errorMsg)
	{
		super(errorMsg);
	}
	
	
	public TradeTimeoutException(String errorCode ,String errorMsg)
	{
		super(errorCode ,errorMsg);
	}

}
