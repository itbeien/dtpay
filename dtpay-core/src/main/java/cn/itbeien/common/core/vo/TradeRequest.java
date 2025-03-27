package cn.itbeien.common.core.vo;


import javax.validation.constraints.NotBlank;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public abstract class TradeRequest extends Trade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商户编号
	 */
	@NotBlank(message="商户号不能为空")
	private String mercNo;
	
	/**
	 * 标识，判断是 定时器请求的     还是 下游请求的 ，定时器请求的  参数值为 timerPayFor;  
	 */
	private String flag;
	

	public String getMercNo() {
		return mercNo;
	}

	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
