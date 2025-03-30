package cn.itbeien.payment.service.report;

import cn.itbeien.common.entity.merchant.MerchantChannelMapping;
import cn.itbeien.common.entity.trade.TradeReport;
import cn.itbeien.common.entity.trade.TradeReportDetail;
import cn.itbeien.common.mapper.trade.TradeReportDetailMapper;
import cn.itbeien.common.mapper.trade.TradeReportMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.SnowflakeIdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TradeReportService {

    @Autowired
    private TradeReportMapper tradeReportMapper;
    @Autowired
    private TradeReportDetailMapper tradeReportDetailMapper;

    private SnowflakeIdFactory idWorker = new SnowflakeIdFactory(2, 3);

    @Autowired
    private RedisCache redisCache;

    @Transactional
    public void createReport(){
            //step 1 获取交易记录表（rx_trade_order_report）,并生成报表数据(日报表)
            List<TradeReport> tradeReports = this.tradeReportMapper.createReport(null,null);

            //step 4 获取交易记录表(rx_trade_order_report),并生成报表明细数据(日报表)
            List<TradeReportDetail> tradeReportDetails = this.tradeReportDetailMapper.createReport(null,null);


            for(TradeReport tradeReport:tradeReports){
                //step 2 从缓存中获取渠道商户编号及渠道商户名称
                //获取商户支付渠道管理表中缓存(下游上传商户号，支付方式，支付场景)
                MerchantChannelMapping merchantChannelMapping = (MerchantChannelMapping) redisCache.getCacheObject("channelMapping"+tradeReport.getMercNo()+tradeReport.getPaywayCode()+tradeReport.getSceneCode());
                String reportCode = String.valueOf(idWorker.nextId());
                tradeReport.setReportCode(reportCode);//报表编号
                tradeReport.setPayingMercName(merchantChannelMapping.getPayingMercName());
                tradeReport.setPayingMercNo(merchantChannelMapping.getPayingMercNo());
                //step 3 保存交易订单报表
                this.tradeReportMapper.insert(tradeReport);

                    for(TradeReportDetail tradeReportDetail : tradeReportDetails){
                        if(tradeReport.getMercNo().equals(tradeReportDetail.getMercNo())){
                            //设置报表编号
                            tradeReportDetail.setReportCode(reportCode);
                            tradeReportDetail.setReportDetailCode(String.valueOf(idWorker.nextId()));//报表明细编号
                            tradeReportDetail.setPayingMercName(merchantChannelMapping.getPayingMercName());
                            tradeReportDetail.setPayingMercNo(merchantChannelMapping.getPayingMercNo());
                            //step5 保存交易订单报表明细
                            this.tradeReportDetailMapper.insert(tradeReportDetail);
                            //tradeReportDetails.remove(tradeReportDetail);
                        }else {
                            break;
                        }
                    }


            }

    }
}
