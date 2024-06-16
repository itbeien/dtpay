package cn.itbeien.business.agent.service;

import cn.itbeien.business.agent.domain.AgentOrg;

import java.util.List;

/**
 * @author beien
 * @date 2024-05-26 17:45
 * Copyright© 2024 beien
 */
public interface IAgentOrgService {
    List<AgentOrg> selectAgentOrgList(AgentOrg agentOrg);

    int saveAgent(AgentOrg agentOrg);

    AgentOrg selectAgentOrg(Long id);

    int deleteAgentByIds(Long[] ids);

    int updateAgent(AgentOrg agentOrg);

    AgentOrg selectAgentByOrgAccount(String orgAccount);
}
