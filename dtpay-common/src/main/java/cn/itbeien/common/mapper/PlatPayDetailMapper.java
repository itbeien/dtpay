package cn.itbeien.common.mapper;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.PlatPayDetailExt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PlatPayDetailMapper {
    int deleteByPrimaryKey(String payId);

    int insert(PlatPayDetail record);

    int insertSelective(PlatPayDetail record);

    PlatPayDetail selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(PlatPayDetail record);

    int updateByPrimaryKey(PlatPayDetail record);
    

    List<PlatPayDetailExt> selectPlatPayDetailExtsByMap(Map<String,Object> map);
    
    int updateAprPlatPayDetailByMap(Map<String,Object> map);

    PlatPayDetail selectPlatPayDetailByTradeSeq(@Param("bankTradeSeq")String bankTradeSeq);
    

    PlatPayDetailExt selectPlatPayDetailExtById(String id);
    
    PlatPayDetail selectByMap(Map<String,Object> map);

    Integer selectCountByMercBatchNo(Map<String,Object> map);

    Integer selectCountByMercNo(Map<String,Object> map);
    
    BigDecimal selectPayForSumAmt(PlatPayDetail record);
    
    Map<String, String> selectKeyByPrimaryKey(@Param("payId") String payId);
    
    
    List<PlatPayDetail> selectList(@Param("param") String param);

    
    int batchInsert(@Param("records") List<PlatPayDetail> records);

    /**
     * updateBatchByPrimaryKeySelective:(批量修改).  
     *
     * :2018年4月16日下午2:29:29
     *
     * @param record
     * @return
     */
    int updateBatchByPrimaryKeySelective(List<PlatPayDetail> record);
    
    List<PlatPayDetail> selectPlatDetailByPlatBatchNo(@Param("platBatchNo") String platBatchNo);

}