package cn.itbeien.payment.mapper.trade;

import cn.itbeien.common.entity.trade.TradeReport;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface TradeReportMapper {
    int deleteByPrimaryKey(String reportCode);

    int insert(TradeReport record);

    int insertSelective(TradeReport record);

    TradeReport selectByPrimaryKey(String reportCode);

    int updateByPrimaryKeySelective(TradeReport record);

    int updateByPrimaryKey(TradeReport record);

    /**
     * 生成订单报表
     * @param timeBegin 交易开始时间
     * @param timeEnd 交易结束时间
     * @return
     */
    List<TradeReport> createReport(String timeBegin, String timeEnd);
}