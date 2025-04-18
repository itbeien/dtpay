package cn.itbeien.task.mapper.pay;

import cn.itbeien.common.entity.pay.Payway;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

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
public interface PaywayMapper {
    int deleteByPrimaryKey(String paywayCode);

    boolean insert(Payway record);

    int insertSelective(Payway record);

    Payway selectByPrimaryKey(String paywayCode);
    
    List<String> selectByPaywayName(@Param("paywayCode") String paywayCode, @Param("paywayName") String paywayName);
    
    List<String> selectByPaywayCode(@Param("paywayCode") String paywayCode);

    boolean updateByPrimaryKeySelective(Payway record);

    int updateByPrimaryKey(Payway record);
    
    /**
     * 支付方式列表
     * @param param
     * @return
     * @throws DataAccessException
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);
}