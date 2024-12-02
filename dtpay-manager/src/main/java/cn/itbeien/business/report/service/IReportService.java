package cn.itbeien.business.report.service;

import cn.itbeien.business.report.domain.AgentReportDetail;
import cn.itbeien.business.report.domain.MerchantReport;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IReportService {
    public List<MerchantReport> selectMerchantReportList(MerchantReport row);

    public List<AgentReportDetail> selectAgentReportList(AgentReportDetail row);
}
