package cn.itbeien.admin.mapper.report;

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
public interface TradeReportDetailMapper {
    int deleteByPrimaryKey(String reportDetailCode);

    int insert(TradeReportDetail record);

    int insertSelective(TradeReportDetail record);

    TradeReportDetail selectByPrimaryKey(String reportDetailCode);

    int updateByPrimaryKeySelective(TradeReportDetail record);

    int updateByPrimaryKey(TradeReportDetail record);

    List<TradeReportDetail> qryTradeReportDetailByPage(TradeReportQryPar reportDetailCode);

}