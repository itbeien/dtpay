package cn.itbeien.merchant.service.finance;

import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.vo.merchant.MerchantAccRelVO;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMerchantAccRelService {
	
	/**
	 * 查询商户资金
	 * @param mercNo
	 * @return
	 */
	public MerchantAccRelVO getMerchantAccRel(String mercNo);
	
	/**
	 * 申请提现
	 * @param platPayDetail
	 */
	public boolean settlementApply(PlatPayDetail platPayDetail);
	
	/**
	 * 查询可用余额金额
	 * @param mercNo
	 * @return
	 */
	public MerchantAccRelVO getSettlementInfo(String mercNo) throws Exception;
	
}
