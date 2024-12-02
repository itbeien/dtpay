package cn.itbeien.api.service.order.impl;

import cn.itbeien.api.dao.TradeOrderMapper;
import cn.itbeien.api.dao.TradeOrderSeqMapper;
import cn.itbeien.api.entity.order.TradeOrder;
import cn.itbeien.api.enums.PayStatus;
import cn.itbeien.api.enums.SystemEnum;
import cn.itbeien.api.service.order.IPayService;
import cn.itbeien.api.vo.CallBackResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author beien
 * @date 2024-03-29 15:39
 * Copyright© 2024 beien
 */
@Service
@Slf4j
public class PayService implements IPayService {
    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Resource
    private TradeOrderSeqMapper tradeOrderSeqMapper;

    @Transactional
    @Override
    public int updateTradeInfo(CallBackResp callBackResp) {
        int result = 0;
        String orderId = null;
        try{
            orderId = callBackResp.getData().getOriOrgTrace();
            String sysRetcode = callBackResp.getSysRetcode();
            String transactionId = callBackResp.getData().getOutTrace();//支付机构流水号
            String payStatus = null;
            if(SystemEnum.YS_PAY_CALLBACKSUCCESS.getCode().equals(sysRetcode)){
                payStatus = PayStatus.pay_success.getCode();
            }else{
                payStatus = PayStatus.pay_paying.getCode();
            }
            int tradeResult  = tradeOrderMapper.updateTradeOrderByOrderId(payStatus,orderId);
            int seqResult = tradeOrderSeqMapper.updateTradeOrderSeqByOrderId(payStatus,orderId,transactionId);
            if(tradeResult>0 && seqResult>0){
                result = 1;
            }
        }catch(Exception e){
            log.error("修改订单:{}信息异常:{}",orderId,e);
            throw e;
        }
        return result;
    }

    @Override
    public TradeOrder findTradeOrderByOrderId(String orderId) {
        return this.tradeOrderMapper.selectTradeOrderByPrimaryKey(orderId);
    }
}
