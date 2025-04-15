package cn.itbeien.task.mapper.pay;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.PlatPayDetailExt;
import cn.itbeien.common.vo.trade.PlatPayDetailQryPar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface PlatPayDetailMapper {
    int deleteByPrimaryKey(String payId);

    int insert(PlatPayDetail record);

    int insertSelective(PlatPayDetail record);

    PlatPayDetail selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(PlatPayDetail record);

    int updateByPrimaryKey(PlatPayDetail record);
    
    List<PlatPayDetailExt> selectPlatPayDetailExtList(PlatPayDetailQryPar record);

    List<PlatPayDetailExt> selectPlatPayDetailExtsByMap(Map<String,Object> map);
    
    int updateAprPlatPayDetailByMap(Map<String,Object> map);

    PlatPayDetail selectPlatPayDetailByTradeSeq(@Param("bankTradeSeq")String bankTradeSeq);
    
    List<PlatPayDetail> selectPlatPayDetailByDate(PlatPayDetailQryPar record);
    
    PlatPayDetailExt selectPlatPayDetailExtById(String id);
    
    List<PlatPayDetail> selectPlatPayDetails(@Param("status") String status);
    
    String selectServiceByPayId(@Param("payId") String payId);

}