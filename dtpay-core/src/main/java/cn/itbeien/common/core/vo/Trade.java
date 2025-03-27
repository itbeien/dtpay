package cn.itbeien.common.core.vo;

import cn.itbeien.common.core.validator.EnumValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Data
public class Trade implements Serializable{
	private static final long serialVersionUID = -210178238529834310L;
	
	/**
	 * 版本号  目前固定1.0
	 */
	@NotBlank(message="版本号不能为空")
	@EnumValue(strValues = {"1.0"}, message = "版本号只能是1.0")
	private String version;  
	
	/**
	 * 语言    目前固定 CN
	 */
	@NotBlank(message="语言不能为空")
	@EnumValue(strValues = {"CN"}, message = "语言只能为CN")
	private String locale;
	
	/**
	 * 签名类型    支持MD5、RSA  目前支持“MD5”
	 */
	@NotBlank(message="签名类型不能为空")
	@EnumValue(strValues = {"MD5","RSA"}, message = "签名类型只能是MD5或RSA")
	private String signType;
	
	/**
	 * 签名
	 */
	@NotBlank(message="签名不能为空")
	private String signature;
	
	/**
	 * 字符集	目前UTF-8
	 */
	@NotBlank(message="字符集不能为空")
	private String charset;
	
	/**
	 * 交易码
	 */
	@NotBlank(message="交易码不能为空")
	@EnumValue(strValues = {"pay","payQuery","refund","refundQuery"}, message = "交易码只能为pay,payQuery,refund,refundQuery")
	private String tradeCode;
	
	/**
	 * 随机数
	 */
	private String noticeStr;


}
