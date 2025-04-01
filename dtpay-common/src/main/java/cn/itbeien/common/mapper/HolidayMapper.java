package cn.itbeien.common.mapper;


import cn.itbeien.common.entity.common.Holiday;

import java.util.Date;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface HolidayMapper {
    int deleteByPrimaryKey(Date hyDate);

    int insert(Holiday record);

    int insertSelective(Holiday record);
    /**
     * 
     * 方法用途: <br>
     * @param hyDate  yyyyMMdd
     * @return
     */
    Holiday selectByPrimaryKey(Date hyDate);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);
    
    /**
     * 
     * 方法用途: 获取下一个工作日<br>
     * @return
     */
    public Date nextBusinessDay();
    /**
     * 
     * 方法用途:是否为节假日(包含周六日) <br>
     * @return
     */
    public Date isHolidays();
}