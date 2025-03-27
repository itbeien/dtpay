package cn.itbeien.business.report.mapper;

import cn.itbeien.business.report.domain.AgentReportDetail;

import java.util.List;

public interface AgentReportDetailMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AgentReportDetail row);

    int insertSelective(AgentReportDetail row);

    AgentReportDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentReportDetail row);

    int updateByPrimaryKey(AgentReportDetail row);

    List<AgentReportDetail> selectAgentReportList(AgentReportDetail row);
}