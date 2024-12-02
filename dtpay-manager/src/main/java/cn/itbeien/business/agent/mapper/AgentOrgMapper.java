package cn.itbeien.business.agent.mapper;


import cn.itbeien.business.agent.domain.AgentOrg;

import java.util.List;

public interface AgentOrgMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AgentOrg row);

    int insertSelective(AgentOrg row);

    AgentOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentOrg row);

    int updateByPrimaryKey(AgentOrg row);

    List<AgentOrg> selectAgentOrgList(AgentOrg row);

    int deleteAgentByIds(Long[] ids);

    public AgentOrg selectAgentByOrgAccount(String orgAccount);
}