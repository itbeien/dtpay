package cn.itbeien.task.service.impl;

import cn.itbeien.task.dao.report.MerchantReportMapper;
import cn.itbeien.task.dao.merchant.MerchantInfoMapper;
import cn.itbeien.task.dao.order.TradeOrderMapper;
import cn.itbeien.task.entity.MerchantInfo;
import cn.itbeien.task.entity.MerchantReport;
import cn.itbeien.task.service.IMerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MerchantService implements IMerchantService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Resource
    private MerchantReportMapper merchantReportMapper;

    @Override
    public List<MerchantInfo> findMerchant() {
        return this.merchantInfoMapper.selectMerchantInfoListByStatus();
    }

    @Override
    public void generateMerchantReport(String merchantNo) throws Exception{
        //根据商户编号获取当前商户下的结算数据
        List<MerchantReport> merchantReports = this.tradeOrderMapper.selectTradeOrderByMerchantNo(merchantNo);
        merchantReportMapper.batchInsert(merchantReports);
    }
}
