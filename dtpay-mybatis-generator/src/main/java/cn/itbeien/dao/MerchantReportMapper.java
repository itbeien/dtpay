package cn.itbeien.dao;

import cn.itbeien.entity.MerchantReport;
import cn.itbeien.entity.MerchantReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantReportMapper {
    long countByExample(MerchantReportExample example);

    int deleteByExample(MerchantReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MerchantReport row);

    int insertSelective(MerchantReport row);

    List<MerchantReport> selectByExample(MerchantReportExample example);

    MerchantReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MerchantReport row, @Param("example") MerchantReportExample example);

    int updateByExample(@Param("row") MerchantReport row, @Param("example") MerchantReportExample example);

    int updateByPrimaryKeySelective(MerchantReport row);

    int updateByPrimaryKey(MerchantReport row);
}