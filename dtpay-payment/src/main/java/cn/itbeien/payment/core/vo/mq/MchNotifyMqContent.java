package cn.itbeien.payment.core.vo.mq;


public class MchNotifyMqContent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2682720671714410228L;
	private String version;//版本号
	private String locale;
	private String interfaceCode;
	private String charset;
	private String signType;
	private String mercNo;
	private String mercOrderNo;//商户订单号
	private String tradeType;//交易类型
	private String tradeAmt;//交易金额
	private String tradeTime;//支付提交时间
	private String feeType;//币种
	private String termType;//终端接入类型
	private String orderId;//平台订单号
	private String tradeEndTime;//交易完成时间
	private String payStatus;//支付状态
	private String bankType;//付款银行
	
	private String flag;// 页面点击的补发通知
	private String notifyUrl; // 回调下游URL
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getMercNo() {
		return mercNo;
	}
	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}
	public String getMercOrderNo() {
		return mercOrderNo;
	}
	public void setMercOrderNo(String mercOrderNo) {
		this.mercOrderNo = mercOrderNo;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}
