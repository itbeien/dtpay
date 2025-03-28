package cn.itbeien.common.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum OrderStatusEnum {
	ORDER_SUBMIT("p0001", "提交订单"),
	ORDER_PAYING("p0002", "支付中"),
	ORDER_SUCCESS("p0000", "支付成功"),
	ORDER_FAIL("p0004", "支付失败"),
	ORDER_CANCEL("p0005", "已取消"),
	ORDER_DELETE("p0006", "过期已作废");
	
	OrderStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (OrderStatusEnum temp : OrderStatusEnum.values()) {
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
