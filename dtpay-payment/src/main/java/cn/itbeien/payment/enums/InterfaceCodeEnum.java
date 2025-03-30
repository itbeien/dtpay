package cn.itbeien.payment.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum InterfaceCodeEnum {
	
	pay("pay","支付"),
	payQuery("payQuery","支付查询"),
	refund("refund","退款"),
	refundQuery("refundQuery","退款查询"),
	payFor("payFor","代付"),
	payForQuery("payForQuery","代付查询"),
	payForWithAppr("payForWithAppr","审批通过代付"),
	batchPayFor("batchPayFor","批量代付"),
	batchPayForQuery("batchPayForQuery","批量代付查询"),
	balanceQuery("balanceQuery", "余额查询")
	;
	
	InterfaceCodeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (InterfaceCodeEnum temp : InterfaceCodeEnum.values()) {
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
