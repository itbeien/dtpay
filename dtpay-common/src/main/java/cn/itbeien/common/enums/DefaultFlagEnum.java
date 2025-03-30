package cn.itbeien.common.enums;


public enum DefaultFlagEnum {
	S1("1","默认通道"),
	S0("0","非默认通道");
	
	DefaultFlagEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (DefaultFlagEnum temp : DefaultFlagEnum.values()) {
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
