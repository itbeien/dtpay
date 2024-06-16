package cn.itbeien.business.report.service;

import cn.itbeien.business.report.domain.AgentReportDetail;
import cn.itbeien.business.report.domain.MerchantReport;

import java.util.List;

/**
 * @author beien
 * @date 2024-05-31 7:00
 * Copyright© 2024 beien
 */
public interface IReportService {
    public List<MerchantReport> selectMerchantReportList(MerchantReport row);

    public List<AgentReportDetail> selectAgentReportList(AgentReportDetail row);
}
