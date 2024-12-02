package cn.itbeien.task.dao.report;

import cn.itbeien.task.entity.MerchantReport;

import java.util.List;

public interface MerchantReportMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MerchantReport row);

    int batchInsert(List<MerchantReport> rows);

    int insertSelective(MerchantReport row);


    MerchantReport selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(MerchantReport row);

    int updateByPrimaryKey(MerchantReport row);
}