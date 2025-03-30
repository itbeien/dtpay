package cn.itbeien.admin.service.report;


import cn.itbeien.admin.vo.report.TradeReportQryPar;
import cn.itbeien.common.entity.trade.TradeReportDetail;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface ITradeReportDetailService {
    public List<TradeReportDetail> qryTradeReportDetailByPage(TradeReportQryPar tradeReportQryPar)throws Exception;
    public List<TradeReportDetail> qryTradeReportDetail(TradeReportQryPar tradeReportQryPar)throws Exception;
}
