package cn.itbeien.common.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum RefundStatus {

	// r0000:退款成功 r0001:退款中   r0003:退款超时    r0004:退款失败
	r0000("r0000","退款成功 "),
	r0001("r0001","退款中 "),
	r0003("r0003","退款超时"),
	r0004("r0004","退款失败");
	
	RefundStatus(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (RefundStatus temp : RefundStatus.values()) {
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
