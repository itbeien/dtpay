package cn.itbeien.web.controller.order;

import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.common.utils.poi.ExcelUtil;
import cn.itbeien.common.utils.sign.RSAUtil;
import cn.itbeien.business.enums.TradeEnums;
import cn.itbeien.business.merchant.domain.ExtMerchantInfo;
import cn.itbeien.business.merchant.service.IMerchantService;
import cn.itbeien.business.order.domain.TradeOrder;
import cn.itbeien.business.order.domain.vo.RefundReq;
import cn.itbeien.business.order.domain.vo.RefundReqBizData;
import cn.itbeien.business.order.service.IOrderService;
import cn.itbeien.business.util.HttpClient;
import cn.itbeien.web.controller.tool.AmountUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static cn.itbeien.common.utils.sign.RSAUtil.getReqStr;

/**
 * @author beien
 * @date 2024-04-18 9:23
 * Copyright© 2024 beien
 */
@RestController
@RequestMapping("/dt/order")
@Slf4j
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMerchantService merchantService;

    //@Value("${yscardapi.refund}")
    private String refundUrl;

    //@Value("${yscardapi.query}")
    private String payUrl;

    /**
     * 获取订单列表
     */
    @PreAuthorize("@dss.hasPermi('dt:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(TradeOrder tradeOrder)
    {
        startPage();
        List<TradeOrder> list = orderService.selectTradeOrderList(tradeOrder);
        return getDataTable(list);
    }

    /**
     * 删除订单信息
     */
    @PreAuthorize("@dss.hasPermi('dt:order:remove')")
    @Log(title = "删除订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderId}")
    public AjaxResult remove(@PathVariable String[] orderId)
    {
        return toAjax(orderService.deleteOrderByIds(orderId));
    }

    /**
     * 修改订单状态
     */
    @PreAuthorize("@dss.hasPermi('dt:order:edit')")
    @Log(title = "修改订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody TradeOrder tradeOrder)
    {
        return toAjax(orderService.update0rder(tradeOrder));
    }



    /**
     * 根据订单Id获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable String orderId)
    {
        return success(orderService.findTradeOrderByOrderId(orderId));
    }

    @Log(title = "订单导出", businessType = BusinessType.EXPORT)
    @PreAuthorize("@dss.hasPermi('dt:order:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, TradeOrder tradeOrder){
        List<TradeOrder> list = orderService.selectTradeOrderList(tradeOrder);
        ExcelUtil<TradeOrder> util = new ExcelUtil<TradeOrder>(TradeOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 退款
     */
    @PreAuthorize("@dss.hasPermi('dt:order:refund')")
    @PostMapping("/refund")
    public AjaxResult refund(@RequestBody RefundReq refundReq) throws Exception {


        String url = this.refundUrl+ TradeEnums.REDUND_URL.getCode();
        //根据dtpay商户号获取商户支付渠道信息
        ExtMerchantInfo extMerchantInfo = new ExtMerchantInfo();
        extMerchantInfo.setOrgMerCode(refundReq.getOrgMercode());
        List<ExtMerchantInfo> extMerchantInfos = this.merchantService.queryExtMerchantInfo(extMerchantInfo);
        refundReq.setOrgId(extMerchantInfos.get(0).getOrgId());
        refundReq.setOrgTermno(extMerchantInfos.get(0).getOrgTermNo());

        RefundReqBizData bizData = new RefundReqBizData();
        bizData.setOriOrgTrace(refundReq.getOrgTrace());//机构流水号
        bizData.setTransAmt(String.valueOf(AmountUtil.yuanToFen(Double.parseDouble(refundReq.getPayAmount()))));
        refundReq.setBizData(bizData);

        JSONObject signData  = JSON.parseObject(JSON.toJSONString(bizData));

        refundReq.setSign(RSAUtil.sign(getReqStr(signData),extMerchantInfos.get(0).getPrivateKey()));
        refundReq.setSignType("RSA");

        String data  = JSON.toJSONString(refundReq);
        log.info("请求报文:{}",data);
        String returnData =  HttpClient.send(url,data,"UTF-8","UTF-8",10);
        log.info("响应报文:{}",returnData);
        //更新订单状态为已经退款
        return toAjax(1);
    }

}
