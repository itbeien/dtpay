package cn.itbeien.task.service;

import cn.itbeien.task.entity.AgentOrg;

import java.util.List;

public interface IAgentService {
    /**
     * 获取所有状态正常的代理商数据
     */
    public List<AgentOrg> findAgent();

    /**
     * 根据代理商ID,获取当前代理商下所有的商户和商户下的订单数据，最后将数据写入到代理商分润报表
     */
    public void generateAgentReport(Long agentId,Double rate)throws Exception;
}
