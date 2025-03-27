package cn.itbeien.api.service.order.impl;

import cn.itbeien.api.dao.TradeOrderMapper;
import cn.itbeien.api.dao.TradeOrderSeqMapper;
import cn.itbeien.api.entity.order.TradeOrder;
import cn.itbeien.api.entity.order.TradeOrderSeq;
import cn.itbeien.api.enums.PayStatus;
import cn.itbeien.api.enums.TradeTypeEnum;
import cn.itbeien.api.service.order.ITradeService;
import cn.itbeien.api.util.SnowFlake;
import cn.itbeien.api.vo.CommonReqVO;
import cn.itbeien.api.vo.PaymentVO;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class OrderService implements ITradeService {

    private static SnowFlake worker=new SnowFlake(1,1,1);

    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Resource
    private TradeOrderSeqMapper tradeOrderSeqMapper;

    @Transactional
    @Override
    public void prepay(PaymentVO paymentVO) throws Exception{
     try{
        CommonReqVO commonReqVO = new CommonReqVO();
        commonReqVO.setOrgTrace(String.valueOf(worker.nextId()));//支付流水号
        //记录订单和订单流水表
        createOrder(commonReqVO,paymentVO);

        }catch (Exception e){
            log.error("prepay error:{}",e);
            throw e;
        }
    }


    /**
     * 构建订单和订单流水
     * @param
     * @throws Exception
     */
    @Transactional
    public void createOrder(CommonReqVO commonReqVO, PaymentVO paymentVO){
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setOrderId(commonReqVO.getOrgTrace());//聚合支付系统订单号
        tradeOrder.setMercNo(paymentVO.getMerchantCode());//聚合支付系统商户编号
        tradeOrder.setTradeType(TradeTypeEnum.PAY.getPayType());//支付
        tradeOrder.setSubject("测试商品");//商品
        tradeOrder.setOrderAmount(new BigDecimal(paymentVO.getAmount()));//支付金额，单位元
        tradeOrder.setOrderTime(new Date());
        tradeOrder.setCreateTime(new Date());//订单创建时间
        tradeOrder.setChannelCode(paymentVO.getChannelCode());//支付渠道编号
        tradeOrder.setPaywayCode(paymentVO.getProductCode());//支付方式编号
        tradeOrder.setSceneCode("js");//支付场景
        tradeOrder.setPayStatus(PayStatus.pay_paying.getCode());//支付状态为待支付
        tradeOrder.setCostValue(BigDecimal.valueOf(Double.parseDouble(paymentVO.getAmount())).multiply(BigDecimal.valueOf(0.0020)));//支付渠道成本手续费
        tradeOrder.setCostRatio(BigDecimal.valueOf(0.0020));//支付渠道成本比例
        tradeOrder.setFeeRatio(BigDecimal.valueOf(0.0028));//商户手续费比例

        TradeOrderSeq tradeOrderSeq = new TradeOrderSeq();
        tradeOrderSeq.setOrderId(commonReqVO.getOrgTrace());//订单号
        tradeOrderSeq.setMercNo(paymentVO.getMerchantCode());
        tradeOrderSeq.setTradeType(TradeTypeEnum.PAY.getPayType());//支付
        tradeOrderSeq.setSubject("测试商品");//商品
        String tradeSeq = String.valueOf(worker.nextId());//支付流水号
        tradeOrderSeq.setTradeSeq(tradeSeq);
        tradeOrderSeq.setOrderAmount(new BigDecimal(paymentVO.getAmount()));
        tradeOrderSeq.setChannelCode(paymentVO.getChannelCode());//支付渠道编号
        tradeOrderSeq.setStatus(PayStatus.pay_paying.getCode());//支付状态为待支付
        tradeOrderSeq.setPaywayCode(paymentVO.getProductCode());//支付方式编号
        tradeOrderSeq.setPayingMercNo(paymentVO.getPayingMercNo());//渠道商户号
        tradeOrderSeq.setCreationTime(new Date());
        tradeOrder.setSceneCode("js");//支付场景

        this.tradeOrderMapper.insertSelective(tradeOrder);
        this.tradeOrderSeqMapper.insertSelective(tradeOrderSeq);

    }

    public static void main(String[] args) {
        PaymentVO  paymentVO = new PaymentVO();
        paymentVO.setMerchantCode("100000001");
        paymentVO.setTradeType("01");//支付
        paymentVO.setGoodsName("测试商品");
        paymentVO.setAmount("100");
        paymentVO.setChannelCode("1001");//支付渠道编号
        paymentVO.setProductCode("wx");//支付方式编号
        paymentVO.setSceneCode("js");//支付场景
        paymentVO.setPayingMercNo("99999");//渠道商户号


        System.out.println(JSON.toJSONString(paymentVO));
    }
}
