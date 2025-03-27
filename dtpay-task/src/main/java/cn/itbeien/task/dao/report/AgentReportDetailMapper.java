package cn.itbeien.task.dao.report;

import cn.itbeien.task.entity.AgentReportDetail;
import java.util.List;

public interface AgentReportDetailMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AgentReportDetail row);

    int batchInsert(List<AgentReportDetail> rows);

    int insertSelective(AgentReportDetail row);

    AgentReportDetail selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(AgentReportDetail row);

    int updateByPrimaryKey(AgentReportDetail row);
}