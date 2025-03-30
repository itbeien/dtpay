package cn.itbeien.common.entity.trade;

public class PlatPayDetailExt extends PlatPayDetail {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mercName;
	
	private String channelName;

	public String getMercName() {
		return mercName;
	}

	public void setMercName(String mercName) {
		this.mercName = mercName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
}