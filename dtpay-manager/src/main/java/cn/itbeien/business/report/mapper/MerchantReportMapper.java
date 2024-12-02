package cn.itbeien.business.report.mapper;

import cn.itbeien.business.report.domain.MerchantReport;

import java.util.List;

public interface MerchantReportMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MerchantReport row);

    int insertSelective(MerchantReport row);


    MerchantReport selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(MerchantReport row);

    int updateByPrimaryKey(MerchantReport row);

    List<MerchantReport> selectMerchantReportList(MerchantReport row);
}