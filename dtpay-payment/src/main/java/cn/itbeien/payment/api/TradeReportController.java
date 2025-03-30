package cn.itbeien.payment.api;

import cn.itbeien.payment.service.report.TradeReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 生成报表接口类
 * Copyright© 2025 itbeien
 */
@RestController
@Slf4j
public class TradeReportController {
    @Autowired
    private TradeReportService tradeReportService;

    @RequestMapping(value = "/gateway/report.do", method = RequestMethod.POST)
    public String trade() {
        try{
            this.tradeReportService.createReport();
            return "success";
        }catch(Exception e){
            log.error("生成报表交易失败：{}",e);
            return "error";
        }
    }
}
