package cn.itbeien.common.core.enums;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public enum MercStatusEnum {

    // 00-待配  01-待审核  02-已通过（待启用）  03-驳回  ey-启动  en-停用
	SAVE_UNSUBMIT("00","待配"),
	PENDING_APPRV("01","待审核"),
	APPRV_PASSD("02","已通过（待启用）"),
	APPRV_REJUST("03","驳回 "),
	ENABLED("ey","启动"),
	DISABLED("en","停用");
	
	MercStatusEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (MercStatusEnum temp : MercStatusEnum.values()) {
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
