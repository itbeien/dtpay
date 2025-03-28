package cn.itbeien.common.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum ZeroOneEnum {

	zero("0","否"),
	one("1","是");
	
	ZeroOneEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (ZeroOneEnum temp : ZeroOneEnum.values()) {
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
