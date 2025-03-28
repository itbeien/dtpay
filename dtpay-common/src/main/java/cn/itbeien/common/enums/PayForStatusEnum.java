package cn.itbeien.common.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum PayForStatusEnum {

	// pf0001:提现成功 pf0001:提现中   pf0003:交易超时    pf0004:提现失败
	pf0001("pf0001","提现中 "),
	pf0002("pf0002","提交成功待返回通知"),
	pf0000("pf0000","提现成功 "),
	pf0003("pf0003","交易超时"),
	pf0004("pf0004","提现失败");
	
	PayForStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (PayForStatusEnum temp : PayForStatusEnum.values()) {
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
