package cn.itbeien.task.mapper.common;

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
public interface CommonMapper {
	
	/**
	 * 功能：根据数据库的序列名称获取序列值
	 * @param seqName 序列名称
	 * @return
	 */
	public String getSeqNextvalByName(@Param("seqName")String seqName);
	
	/**
	 * 功能：根据数据库的序列名称获取序列值
	 * @param seqName 序列名称
	 * @return
	 */
	public String getSeqNoByName(@Param("seqName")String seqName, @Param("addNumber")int addNumber);
	
	/**
	 * 功能：根据数据库的序列名称获取序列值
	 * @param seqName 序列名称
	 * @return
	 */
	public  List<Map<?,?>> getSeqNextnalSysdateByName(@Param("seqName")String seqName ,@Param("format")String format);
	
	/**
	 * 
	 * 方法用途: 获取mysql数据库的当前时间<br>
	 * 实现步骤: <br>
	 * @return
	 */
	public String getMysqlDbDate();
	
	
}
