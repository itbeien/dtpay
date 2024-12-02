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
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
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
