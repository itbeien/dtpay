package cn.itbeien.api.service.agent.impl;


import cn.itbeien.api.dao.AgentOrgMapper;
import cn.itbeien.api.entity.agent.AgentOrg;
import cn.itbeien.api.service.agent.IAgentOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author beien
 * @date 2024-05-26 17:49
 * Copyright© 2024 beien
 */
@Service
public class AgentOrgService implements IAgentOrgService {
    @Resource
    private AgentOrgMapper agentOrgMapper;
    @Override
    public List<AgentOrg> selectAgentOrgList(AgentOrg agentOrg) {
        return this.agentOrgMapper.selectAgentOrgList(agentOrg);
    }

    @Override
    public int saveAgent(AgentOrg agentOrg) {
        return this.agentOrgMapper.insertSelective(agentOrg);
    }

    @Override
    public AgentOrg selectAgentOrg(Long id) {
        return this.agentOrgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteAgentByIds(Long[] ids) {
        return this.agentOrgMapper.deleteAgentByIds(ids);
    }

    @Override
    public int updateAgent(AgentOrg agentOrg) {
        return this.agentOrgMapper.updateByPrimaryKeySelective(agentOrg);
    }

    @Override
    public AgentOrg selectAgentByOrgAccount(String orgAccount) {
        return this.agentOrgMapper.selectAgentByOrgAccount(orgAccount);
    }
}
