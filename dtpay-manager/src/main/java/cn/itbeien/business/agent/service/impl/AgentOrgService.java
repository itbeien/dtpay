package cn.itbeien.business.agent.service.impl;

import cn.itbeien.business.agent.domain.AgentOrg;
import cn.itbeien.business.agent.mapper.AgentOrgMapper;
import cn.itbeien.business.agent.service.IAgentOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
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
