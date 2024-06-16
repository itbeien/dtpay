package cn.itbeien.business.report.service.impl;

import cn.itbeien.business.report.domain.AgentReportDetail;
import cn.itbeien.business.report.domain.MerchantReport;
import cn.itbeien.business.report.mapper.AgentReportDetailMapper;
import cn.itbeien.business.report.mapper.MerchantReportMapper;
import cn.itbeien.business.report.service.IReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author beien
 * @date 2024-05-31 7:01
 * Copyright© 2024 beien
 */
@Service
public class ReportService implements IReportService {
    @Resource
    private MerchantReportMapper merchantReportMapper;

    @Resource
    private AgentReportDetailMapper agentReportDetailMapper;


    @Override
    public List<MerchantReport> selectMerchantReportList(MerchantReport row) {
        return this.merchantReportMapper.selectMerchantReportList(row);
    }

    @Override
    public List<AgentReportDetail> selectAgentReportList(AgentReportDetail row) {
        return this.agentReportDetailMapper.selectAgentReportList(row);
    }
}
