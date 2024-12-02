package cn.itbeien.web.controller.report;

import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.business.report.domain.AgentReportDetail;
import cn.itbeien.business.report.domain.MerchantReport;
import cn.itbeien.business.report.service.IReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author beien
 * @date 2024-05-31 7:04
 * Copyright© 2024 beien
 */
@RestController
@Slf4j
@RequestMapping("/dt/report")
public class ReportController extends BaseController {
    @Autowired
    private IReportService reportService;


    /**
     * 获取代理商分润报表
     */
    @PreAuthorize("@dss.hasPermi('dt:report:agentReport')")
    @GetMapping("/agentReport")
    public TableDataInfo list(AgentReportDetail agentReportDetail)
    {
        startPage();
        List<AgentReportDetail> list = reportService.selectAgentReportList(agentReportDetail);
        return getDataTable(list);
    }

    /**
     * 获取商户结算报表
     */
    @PreAuthorize("@dss.hasPermi('dt:report:merchantReport')")
    @GetMapping("/merchantReport")
    public TableDataInfo list(MerchantReport merchantReport)
    {
        startPage();
        List<MerchantReport> list = reportService.selectMerchantReportList(merchantReport);
        return getDataTable(list);
    }
}
