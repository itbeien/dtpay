package cn.itbeien.merchant.mapper.finance;

import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMerchantAccRelMapper {
	
	/**
	 * 查询商户资金
	 * @param param
	 * @return
	 */
	public Map<String, Object> getMerchantAccRel(Map<String, Object> param);
	
	/**
	 * 获取结算信息核对用户提交的申请结算方便保存数据到平台代付明细表
	 * @param param
	 * @return
	 */
	public Map<String, Object> getSettlementInfo(Map<String, Object> param);
	
	
}
