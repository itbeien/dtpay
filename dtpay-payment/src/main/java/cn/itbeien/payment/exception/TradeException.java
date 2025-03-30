package cn.itbeien.payment.exception;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class TradeException extends RuntimeException{
	
	
	private static final long serialVersionUID = -7301874675422939924L;
	
	
	private Exception targetException;
	
	private String errorCode;
	private String errorMsg;
	
	public TradeException()
	{
		super();
	}
	
	public TradeException(String errorMsg)
	{
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	
	public TradeException(String errorCode ,String errorMsg)
	{
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the targetException
	 */
	public Exception getTargetException() {
		return targetException;
	}

	/**
	 * @param targetException the targetException to set
	 */
	public void setTargetException(Exception targetException) {
		this.targetException = targetException;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


    
}
