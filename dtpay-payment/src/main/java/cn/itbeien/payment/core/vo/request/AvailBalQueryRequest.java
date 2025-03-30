package cn.itbeien.payment.core.vo.request;


import cn.itbeien.payment.core.vo.TradeRequest;
import cn.itbeien.payment.enums.InterfaceCodeEnum;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.fail.InvalidParamException;

public class AvailBalQueryRequest extends TradeRequest {

	private static final long serialVersionUID = 1L;
	
	public void validata(){
		if(!InterfaceCodeEnum.balanceQuery.getCode().equals(this.getInterfaceCode())){
			throw new InvalidParamException(RespEnum.E00006.getCode(),RespEnum.E00006.getDesc());
		}
	}
	
}
