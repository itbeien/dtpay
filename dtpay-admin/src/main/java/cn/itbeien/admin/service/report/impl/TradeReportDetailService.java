package cn.itbeien.admin.service.report.impl;

import cn.itbeien.admin.mapper.report.TradeReportDetailMapper;
import cn.itbeien.admin.service.report.ITradeReportDetailService;
import cn.itbeien.admin.vo.report.TradeReportQryPar;
import cn.itbeien.common.entity.trade.TradeReportDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TradeReportDetailService implements ITradeReportDetailService {
    @Autowired
    private TradeReportDetailMapper tradeReportDetailMapper;

    @Override
    public List<TradeReportDetail> qryTradeReportDetailByPage(TradeReportQryPar tradeReportQryPar) throws Exception {
        List<TradeReportDetail> tradeReportDetails = this.tradeReportDetailMapper.qryTradeReportDetailByPage(tradeReportQryPar);
        return tradeReportDetails;
    }

    @Override
    public List<TradeReportDetail> qryTradeReportDetail(TradeReportQryPar tradeReportQryPar) throws Exception {
        List<TradeReportDetail> tradeReportDetails = this.tradeReportDetailMapper.qryTradeReportDetailByPage(tradeReportQryPar);
        return tradeReportDetails;
    }
}
