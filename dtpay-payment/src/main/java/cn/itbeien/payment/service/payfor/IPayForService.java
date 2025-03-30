package cn.itbeien.payment.service.payfor;


import cn.itbeien.payment.core.vo.request.BatchPayForRequest;
import cn.itbeien.payment.core.vo.request.PayForRequest;
import cn.itbeien.payment.core.vo.response.BatchPayForResponse;
import cn.itbeien.payment.core.vo.response.PayForResponse;

public interface IPayForService {

	public void payFor(PayForRequest tradeRequest, PayForResponse tradeResponse);
	
	public void payForWithAppr(PayForRequest tradeRequest, PayForResponse tradeResponse);
	
	public void batchPayFor(BatchPayForRequest tradeRequest, BatchPayForResponse tradeResponse);
}
