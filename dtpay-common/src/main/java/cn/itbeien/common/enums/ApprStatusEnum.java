package cn.itbeien.common.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public enum ApprStatusEnum {
     //00-保存未提交  01-待审批 02-审批通过 03-审批拒绝
	
	SAVE_UNSUBMIT("00","保存未提交"),
	PENDING_APPRV("01","待审批"),
	APPRV_PASSD("02","审批通过"),
	APPRV_REJUST("03","审批拒绝");
	
	ApprStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (ApprStatusEnum temp : ApprStatusEnum.values()) {
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
