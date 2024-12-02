package cn.itbeien.dao;

import cn.itbeien.entity.AgentOrg;

import java.util.List;

public interface AgentOrgMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AgentOrg row);

    int insertSelective(AgentOrg row);

    AgentOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentOrg row);

    int updateByPrimaryKey(AgentOrg row);

    List<AgentOrg> selectAgentOrgList(AgentOrg row);
}