package cn.itbeien.task.mapper.common;


import cn.itbeien.common.entity.common.MngLog;
import cn.itbeien.task.vo.MngLogQryPar;

import java.util.List;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface MngLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(MngLog record);

    int insertSelective(MngLog record);

    MngLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MngLog record);

    int updateByPrimaryKey(MngLog record);
    
    List<MngLog> selectMngLogList(MngLogQryPar record);
}