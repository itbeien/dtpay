package cn.itbeien.api.controller;

import cn.itbeien.api.entity.order.TradeOrder;
import cn.itbeien.api.enums.PayTypeEnum;
import cn.itbeien.api.enums.SystemEnum;
import cn.itbeien.api.service.order.IPayService;
import cn.itbeien.api.service.order.ITradeService;
import cn.itbeien.api.util.AmountUtil;
import cn.itbeien.api.vo.CallBackData;
import cn.itbeien.api.vo.CallBackResp;
import cn.itbeien.api.vo.PaymentVO;
import cn.itbeien.api.vo.ResultVO;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class TradeOrderController {

    @Autowired
    private ITradeService orderService;

    @Autowired
    private IPayService payService;

    @PostMapping("/pay/prepay")
    public ResultVO prepay(@RequestBody String data){
        PaymentVO paymentVO = JSONObject.parseObject(data,PaymentVO.class);
        ResultVO model = new  ResultVO();
        try{
            orderService.prepay(paymentVO);
            model.setCode(SystemEnum.SUCCESS.getCode());
        }catch (Exception e){
            model.setCode(SystemEnum.ERROR.getCode());
            model.setCode(SystemEnum.ERROR.getMessage());
            log.error("预支付下单异常:{}",e);
        }
        return model;
    }


    @PostMapping("/pay/callback")
    @ResponseBody
    public String callback(@RequestBody String data){
        String result = "ok";
        CallBackResp callBackResp = JSON.parseObject(data,CallBackResp.class);
        log.info("callback响应报文:{}",data);
        //异步发送支付结果到mqtt服务器
        //获取商户编号，作为topic
        //String orderId = callBackResp.getData().getOriOrgTrace();
        //String tradeCode =  callBackResp.getData().getTradeCode();
        //TradeOrder tradeOrder = this.payService.findTradeOrderByOrderId(orderId);
        //Double amount = AmountUtil.fenToYuan(Long.parseLong(callBackResp.getData().getTradeAmt()));
        //根据回调方法中的订单号修改订单状态,并且把上游的流水号记录到数据库表
        payService.updateTradeInfo(callBackResp);//先更新系统支付结果再通知设备

        return result;
    }

    public static void main(String[] args) {
        CallBackResp callBackResp = new CallBackResp();
        callBackResp.setSysRetcode("000000");//支付状态
        CallBackData data = new CallBackData();
        data.setOriOrgTrace("10000");//预支付订单号
        data.setTradeCode("js");//支付方式
        data.setTradeAmt("100");//交易金额
        data.setOutTrace("1111111");//支付机构流水号
        callBackResp.setData(data);
        System.out.println(JSON.toJSONString(callBackResp));
    }

}
