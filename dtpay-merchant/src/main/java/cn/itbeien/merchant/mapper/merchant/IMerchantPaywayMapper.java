package cn.itbeien.merchant.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMerchantPaywayMapper {
	
	/**
	 * 获取商户对应上游的产品列表
	 * @param mercNo
	 * @return
	 */
	public List<MerchantPaywayMapping> getMerchantPaywayList(String mercNo);

}
