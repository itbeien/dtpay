  
  
package cn.itbeien.payment.service.balance.impl;

import cn.itbeien.common.entity.merchant.MerchantAccRel;
import cn.itbeien.common.mapper.merchant.MerchantAccRelMapper;
import cn.itbeien.payment.core.vo.request.AvailBalQueryRequest;
import cn.itbeien.payment.core.vo.response.AvailBalQueryResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.service.balance.IBalanceQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceQueryServiceImpl implements IBalanceQueryService {
	
	@Autowired
	private MerchantAccRelMapper merchantAccRelMapper;


	@Override
	public void balanceQuery(AvailBalQueryRequest tradeRequest, AvailBalQueryResponse tradeResponse) {
		String mercNo = tradeRequest.getMercNo();
		MerchantAccRel accRel = merchantAccRelMapper.selectByMercAcct(mercNo, "00");
		if(accRel == null) {
			throw new TradeException(RespEnum.E00031.getCode(), RespEnum.E00031.getDesc());
		}
		BeanUtils.copyProperties(tradeRequest, tradeResponse);
		tradeResponse.setAcctBal(accRel.getAcctBal().toString()); 
		tradeResponse.setAcctAvaiBal(accRel.getAcctAvaiBal().toString());
		tradeResponse.setFreezeBal(accRel.getFreezeBal().toString());
		tradeResponse.setRespCode(RespEnum.S00000.getCode());
    	tradeResponse.setRespDesc(RespEnum.S00000.getDesc());
	}

}
  
