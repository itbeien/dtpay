package cn.itbeien.merchant.service.merchant;


import cn.itbeien.common.entity.merchant.MerchantInfo;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMerchantInfoService {
	
	/**
	 * 商户基本信息
	 * @param  mercNo
	 * @return
	 */
	public MerchantInfo getMerchantInfo(String mercNo) throws Exception;
	
	/**
	 * 获取商户支付密码
	 * @param mercNo
	 * @return
	 */
	public MerchantInfo getPasswd(String mercNo) throws Exception;
	
	/**
	 * 更新商户支付密码
	 * @param mercNo
	 * @return
	 */
	public boolean updatePasswd(String mercNo) throws Exception;
	
	/**
	 * 通过登陆账号ID获取商户身份证后六位初始化支付密码
	 * @param userId
	 * @return
	 */
	public MerchantInfo getMerchantInfoByUserId(String userId) throws Exception;
	
	/**
	 * 查询商户信息
	 * @param mercNo
	 * @return
	 */
	MerchantInfo getMerchantInfoByMercNo(String mercNo) throws Exception;
	
}
