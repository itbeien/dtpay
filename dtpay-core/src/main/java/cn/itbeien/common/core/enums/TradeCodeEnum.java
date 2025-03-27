package cn.itbeien.common.core.enums;

public enum TradeCodeEnum {

	pay("pay","支付"),
	payQuery("payQuery","支付查询"),
	refund("refund","退款"),
	refundQuery("refundQuery","退款查询"),
	payFor("payFor","代付"),
	payForQuery("payForQuery","代付查询"),
	batchPayFor("batchPayFor","批量代付"),
	batchPayForQuery("batchPayForQuery","批量代付查询"),
	balanceQuery("balanceQuery", "余额查询")
	;

	TradeCodeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	// 普通方法
    public static String getDescByCode(String code) {
        for (TradeCodeEnum temp : TradeCodeEnum.values()) {
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
