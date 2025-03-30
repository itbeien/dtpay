package cn.itbeien.admin.service.report.impl;

import cn.itbeien.admin.service.report.ITradeReportService;
import cn.itbeien.admin.vo.report.TradeReportQryPar;
import cn.itbeien.common.entity.trade.TradeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.itbeien.admin.mapper.report.TradeReportMapper;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Service
public class TradeReportService implements ITradeReportService {
    @Autowired
    private TradeReportMapper tradeReportMapper;
    @Override
    public List<TradeReport> qryTradeReportByPage(TradeReportQryPar tradeReportQryPar) {
        List<TradeReport> tradeReport = this.tradeReportMapper.qryTradeReportByPage(tradeReportQryPar);
        return tradeReport;
    }

    @Override
    public List<TradeReport> qryTradeReport(TradeReportQryPar tradeReportQryPar) throws Exception {
        List<TradeReport> tradeReport = this.tradeReportMapper.qryTradeReportByPage(tradeReportQryPar);
        return tradeReport;
    }
}
