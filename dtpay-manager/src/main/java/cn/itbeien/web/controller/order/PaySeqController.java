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
import cn.itbeien.business.order.domain.TradeOrderSeq;
import cn.itbeien.business.order.domain.vo.CommonReqVO;
import cn.itbeien.business.order.domain.vo.RefundReq;
import cn.itbeien.business.order.domain.vo.RefundReqBizData;
import cn.itbeien.business.order.domain.vo.ScanPay;
import cn.itbeien.business.order.service.IPaySeqService;
import cn.itbeien.business.util.HttpClient;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/dt/payseq")
@Slf4j
public class PaySeqController extends BaseController {

    @Autowired
    private IPaySeqService paySeqService;

    @Autowired
    private IMerchantService merchantService;

    //@Value("${yscardapi.query}")
    private String payUrl;

    //@Value("${yscardapi.refund}")
    private String refundUrl;

    /**
     * 获取订单列表
     */
    @PreAuthorize("@dss.hasPermi('dt:payseq:list')")
    @GetMapping("/list")
    public TableDataInfo list(TradeOrderSeq tradeOrderSeq)
    {
        startPage();
        List<TradeOrderSeq> list = paySeqService.selectTradeOrderSeqList(tradeOrderSeq);
        return getDataTable(list);
    }

    /**
     * 根据订单Id获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:payseq:query')")
    @GetMapping(value = "/{tradeSeq}")
    public AjaxResult getInfo(@PathVariable String tradeSeq)
    {
        return success(paySeqService.findTradeOrderSeqBySeq(tradeSeq));
    }

    /**
     * 删除订单信息
     */
    @PreAuthorize("@dss.hasPermi('dt:payseq:remove')")
    @Log(title = "删除订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tradeSeq}")
    public AjaxResult remove(@PathVariable String[] tradeSeq)
    {
        return toAjax(paySeqService.deleteOrderSeqByIds(tradeSeq));
    }

    @Log(title = "导出订单流水", businessType = BusinessType.EXPORT)
    @PreAuthorize("@dss.hasPermi('dt:payseq:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, TradeOrderSeq tradeOrderSeq){
        List<TradeOrderSeq> list = paySeqService.selectTradeOrderSeqList(tradeOrderSeq);
        ExcelUtil<TradeOrderSeq> util = new ExcelUtil<TradeOrderSeq>(TradeOrderSeq.class);
        util.exportExcel(response, list, "订单流水数据");
    }

    @Log(title = "支付结果查询", businessType = BusinessType.OTHER)
    @PreAuthorize("@dss.hasPermi('dt:payseq:payQuery')")
    @PostMapping("/payQuery")
    public AjaxResult query(@RequestBody CommonReqVO commonReqVO) throws Exception {
        String url = this.payUrl+ TradeEnums.PAY_QUERY_URL.getCode();
        commonReqVO.setOrgMercode(commonReqVO.getOrgMercode());//必须上传，机构商户号
        commonReqVO.setOrgTrace(commonReqVO.getOrgTrace());//聚合支付系统的流水号

        //根据dtpay商户号获取商户支付渠道信息
        ExtMerchantInfo extMerchantInfo = new ExtMerchantInfo();
        extMerchantInfo.setOrgMerCode(commonReqVO.getOrgMercode());
        List<ExtMerchantInfo> extMerchantInfos = this.merchantService.queryExtMerchantInfo(extMerchantInfo);
        commonReqVO.setOrgId(extMerchantInfos.get(0).getOrgId());
        commonReqVO.setOrgTermno(extMerchantInfos.get(0).getOrgTermNo());

        ScanPay scanPay = new ScanPay();
        //scanPay.setOriOutTrace();//支付机构的流水号
        scanPay.setOriOrgTrace(commonReqVO.getOrgTrace());//聚合支付系统支付流水号
        commonReqVO.setData(scanPay);
        commonReqVO.setSignType("RSA");
        JSONObject signData  = JSON.parseObject(JSON.toJSONString(scanPay));
        commonReqVO.setSign(RSAUtil.sign(getReqStr(signData),extMerchantInfos.get(0).getPrivateKey()));


        String data  = JSON.toJSONString(commonReqVO);
        log.info("支付查询请求报文:{}",data);
        String returnData =  HttpClient.send(url,data,"UTF-8","UTF-8",10);
        log.info("支付查询响应报文:{}",returnData);

        return toAjax(1);
    }

    @Log(title = "退款结果查询", businessType = BusinessType.OTHER)
    @PreAuthorize("@dss.hasPermi('dt:payseq:payQuery')")
    @PostMapping("/refundQuery")
    public AjaxResult refundQuery(RefundReq refundReq) throws Exception {
        String url = this.refundUrl+ TradeEnums.REDUND_QUERY_URL.getCode();

        refundReq.setOrgMercode(refundReq.getOrgMercode());//必须上传，机构商户号
        refundReq.setOrgTrace(refundReq.getOrgTrace());//机构商户流水号
        //根据dtpay商户号获取商户支付渠道信息
        ExtMerchantInfo extMerchantInfo = new ExtMerchantInfo();
        extMerchantInfo.setOrgMerCode(refundReq.getOrgMercode());
        List<ExtMerchantInfo> extMerchantInfos = this.merchantService.queryExtMerchantInfo(extMerchantInfo);
        refundReq.setOrgId(extMerchantInfos.get(0).getOrgId());
        refundReq.setOrgTermno(extMerchantInfos.get(0).getOrgTermNo());

        RefundReqBizData bizData = new RefundReqBizData();
        bizData.setOriOrgTrace(refundReq.getOrgTrace());//机构商户流水号
        refundReq.setBizData(bizData);

        JSONObject signData  = JSON.parseObject(JSON.toJSONString(bizData));

        refundReq.setSign(RSAUtil.sign(getReqStr(signData),extMerchantInfos.get(0).getPrivateKey()));
        refundReq.setSignType("RSA");

        String data  = JSON.toJSONString(refundReq);
        log.info("请求报文:{}",data);
        String returnData =  HttpClient.send(url,data,"UTF-8","UTF-8",10);
        log.info("响应报文:{}",returnData);

        return toAjax(1);
    }
}
