package cn.itbeien.common.mapper;

import cn.itbeien.common.entity.FreezeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreezeDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreezeDetail record);

    int insertSelective(FreezeDetail record);

    FreezeDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreezeDetail record);

    int updateByPrimaryKey(FreezeDetail record);

    
    int batchInsert(@Param("records") List<FreezeDetail> records);
    
    List<FreezeDetail> selectFreezeDetailByBatchNo(@Param("platBatchNo") String platBatchNo);
    
    int updateBatchFreeDetials(@Param("fzDetails") List<String> fzDetails);
}