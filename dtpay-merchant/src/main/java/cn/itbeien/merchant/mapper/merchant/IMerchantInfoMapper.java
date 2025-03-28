package cn.itbeien.merchant.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantInfo;

import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMerchantInfoMapper {
	
	/**
	 * 获取商户基本信息
	 * @param param
	 * @return
	 */
	public Map<String, Object> getMerchantInfo(Map<String, Object> param);
	
	/**
	 * 获取商户支付密碼
	 * @param mercNo
	 * @return
	 */
	public Map<String, Object> getPasswd(String mercNo);
	
	/**
	 * 更新支付密码
	 * @param param
	 * @return
	 */
	public int updatePasswd(Map<String, Object> param);
	
	/**
	 * 通过登陆账号ID获取商户身份证后六位初始化支付密码
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getMerchantInfoByUserId(String userId);
	
	
	/**
	 * 查询商户信息
	 * @param mercNo
	 * @return
	 */
	MerchantInfo selectByPrimaryKey(String mercNo);

}
