package cn.itbeien.payment.service.holiday;

import cn.itbeien.common.mapper.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Service
public class HolidayService {
	
	@Autowired
	private HolidayMapper holidayMapper;
	
	/**
	 * 
	 * 方法用途:获取下一个工作日 <br>
	 * @return
	 */
	public Date nextBusinessDay(){
		
		return this.holidayMapper.nextBusinessDay();
	}
	
	/**
	 * 是否为节假日，返回true当天为节假日。false当天为工作日
	 * 方法用途: <br>
	 * @return
	 */
	public boolean isHolidays(){
		Date date  = this.holidayMapper.isHolidays();
		if(date == null){
			return false;
		}else {
			return true;
		}
	}
}
