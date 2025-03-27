package cn.itbeien.business.agent.service;

import cn.itbeien.business.agent.domain.AgentOrg;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IAgentOrgService {
    List<AgentOrg> selectAgentOrgList(AgentOrg agentOrg);

    int saveAgent(AgentOrg agentOrg);

    AgentOrg selectAgentOrg(Long id);

    int deleteAgentByIds(Long[] ids);

    int updateAgent(AgentOrg agentOrg);

    AgentOrg selectAgentByOrgAccount(String orgAccount);
}
