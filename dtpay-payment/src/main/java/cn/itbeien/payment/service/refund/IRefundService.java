package cn.itbeien.payment.service.refund;

import cn.itbeien.payment.core.vo.request.RefundRequest;
import cn.itbeien.payment.core.vo.response.RefundResponse;

/**
 * @author Administrator
 * 退款发起交易
 * 退款查询交易
 */
public interface IRefundService {

	/**
	 * 退款交易
	 * @param refundRequest
	 * @param refundResponse
	 */
	public void refund(RefundRequest refundRequest, RefundResponse refundResponse);
	
}
