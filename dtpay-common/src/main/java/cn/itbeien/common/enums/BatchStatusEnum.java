package cn.itbeien.common.enums;

public enum BatchStatusEnum {

	b0001("1","批量处理中"),
	b0002("2","受理成功待返回通知"),
	b0000("0","批量提现成功 "),
	b0003("4","交易超时"),
	b0004("5","批量提现失败");
	
	BatchStatusEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (BatchStatusEnum temp : BatchStatusEnum.values()) {
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
