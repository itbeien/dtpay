package cn.itbeien.common.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum TradeRefundStatus {
	
	SUBMIT("p0001", "提交订单"), RUNNING("p0002", "支付中"),
	SUCCESS("p0000", "支付成功"), FAILURE("r0004", "支付失败"), CANCEL("p0005", "已取消");

	TradeRefundStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	// 普通方法
	public static String getDescByCode(String code) {
		for (TradeRefundStatus temp : TradeRefundStatus.values()) {
			if (code.equals(temp.getCode())) {
				return temp.getDesc();
			}
		}
		return null;
	}

	private final String code;

	private final String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
