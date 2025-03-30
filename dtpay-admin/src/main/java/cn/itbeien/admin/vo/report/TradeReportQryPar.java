package cn.itbeien.admin.vo.report;


import cn.itbeien.common.vo.BaseBean;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public class TradeReportQryPar extends BaseBean {

    private String mercName;//商户名称
    private String sceneCode;//场景编号
    private String paywayCode;//支付方式编号
    private String reportCode;//报表编号
    private String reportDetailCode;//报表明细编号
    private String orderTime;//交易日期
    private String channelCode;//渠道编号
    private String orderTimeEnd;//交易结束时间


    public String getMercName() {
        return mercName;
    }

    public void setMercName(String mercName) {
        this.mercName = mercName;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getPaywayCode() {
        return paywayCode;
    }

    public void setPaywayCode(String paywayCode) {
        this.paywayCode = paywayCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportDetailCode() {
        return reportDetailCode;
    }

    public void setReportDetailCode(String reportDetailCode) {
        this.reportDetailCode = reportDetailCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(String orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }
}
