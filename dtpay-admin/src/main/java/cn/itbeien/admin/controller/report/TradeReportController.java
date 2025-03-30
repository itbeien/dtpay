package cn.itbeien.admin.controller.report;

import cn.itbeien.admin.service.report.ITradeReportDetailService;
import cn.itbeien.admin.service.report.ITradeReportService;
import cn.itbeien.admin.vo.report.TradeReportQryPar;
import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.trade.TradeReport;
import cn.itbeien.common.entity.trade.TradeReportDetail;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Controller
@RequestMapping("/report")
@Slf4j
public class TradeReportController extends BaseController {

    @Autowired
    private ITradeReportService tradeReportService;

    @Autowired
    private ITradeReportDetailService tradeReportDetailService;


    @RequestMapping("/index")
    public AjaxResult index(){
        return success();
    }

    @RequestMapping("/detailIndex")
    public AjaxResult detailIndex(){
        return success();
    }

    /**
     * 获取交易报表
     * @return
     */
    @RequestMapping("/reportList")
    public Object reportList(TradeReportQryPar tradeReportQryPar){
        List<TradeReport> tradeReportList = null;
        try {
            tradeReportList = tradeReportService.qryTradeReportByPage(tradeReportQryPar);
        } catch (Exception e) {
            log.error("获取交易报表异常{}",e);
        }
        return tradeReportList;
    }


    /**
     * 根据reportCode获取交易报表明细
     * @return
     */
    @RequestMapping("/reportDetailList")
    public TableDataInfo reportDetailList(TradeReportQryPar tradeReportQryPar){
        List<TradeReportDetail> list = null;
        try {
            list = this.tradeReportDetailService.qryTradeReportDetailByPage(tradeReportQryPar);
        } catch (Exception e) {
            log.error("获取交易报表明细异常{}",e);
        }
        return getDataTable(list);
    }


    /**
     * export:(导出交易报表).
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> export(TradeReportQryPar tradeReportQryPar){

        return null;
    }



    /**
     * export:(导出交易报表明细).
     */
    @GetMapping("/exportDetail")
    public ResponseEntity<byte[]> exportDetail(TradeReportQryPar tradeReportQryPar){
        List<TradeReportDetail> dataList = null;
        try {
            dataList = this.tradeReportDetailService.qryTradeReportDetail(tradeReportQryPar);
        } catch (Exception e) {
            log.error("导出交易报表明细异常:{}",e);
        }


        String fileName = "交易报表明细-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xls";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/x-msdownload"));
        try {
            httpHeaders.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
         return null;
    }

}
