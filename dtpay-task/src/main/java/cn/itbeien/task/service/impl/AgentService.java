package cn.itbeien.task.service.impl;

import cn.itbeien.task.dao.report.AgentReportDetailMapper;
import cn.itbeien.task.dao.agent.AgentOrgMapper;
import cn.itbeien.task.dao.merchant.MerchantInfoMapper;
import cn.itbeien.task.dao.order.TradeOrderMapper;
import cn.itbeien.task.entity.AgentOrg;
import cn.itbeien.task.entity.AgentReportDetail;
import cn.itbeien.task.service.IAgentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代理商模块服务类
 */
@Service
public class AgentService implements IAgentService {
    @Resource
    private AgentOrgMapper agentOrgMapper;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Resource
    private TradeOrderMapper tradeOrderMapper;

    @Resource
    private AgentReportDetailMapper reportDetailMapper;

    @Override
    public List<AgentOrg> findAgent() {
        return this.agentOrgMapper.selectAgentOrgListByStatus();
    }

    @Override
    public void generateAgentReport(Long agentId,Double rate) throws Exception{
        //根据代理商编号从商户信息表获取订单数据
        List<AgentReportDetail> agentReportDetails = this.tradeOrderMapper.selectTradeOrderByAgentId(agentId,rate);
        if(agentReportDetails == null || agentReportDetails.isEmpty()) return;
        reportDetailMapper.batchInsert(agentReportDetails);
    }


}
