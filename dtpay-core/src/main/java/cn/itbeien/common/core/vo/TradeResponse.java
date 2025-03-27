package cn.itbeien.common.core.vo;

/**
 * @author Administrator
 *
 */
public class TradeResponse extends Trade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8809580537657818686L;
	
	/**
	 * 商户号
	 */
	private String mercNo;
	
	/**
	 * 返回状态码	000000 表示当前交易成功，例：如果是支付表示支付提交成功，如果是查询表示查询成功
	 */
	private String respCode;
	
	/**
	 * 状态码描述
	 */
	private String respDesc;
	
	
	public String getMercNo() {
		return mercNo;
	}

	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}


	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

}
