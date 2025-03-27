package cn.itbeien.dao;

import cn.itbeien.entity.AgentReportDetail;
import cn.itbeien.entity.AgentReportDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentReportDetailMapper {
    long countByExample(AgentReportDetailExample example);

    int deleteByExample(AgentReportDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgentReportDetail row);

    int insertSelective(AgentReportDetail row);

    List<AgentReportDetail> selectByExample(AgentReportDetailExample example);

    AgentReportDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AgentReportDetail row, @Param("example") AgentReportDetailExample example);

    int updateByExample(@Param("row") AgentReportDetail row, @Param("example") AgentReportDetailExample example);

    int updateByPrimaryKeySelective(AgentReportDetail row);

    int updateByPrimaryKey(AgentReportDetail row);
}