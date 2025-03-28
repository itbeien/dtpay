package cn.itbeien.merchant.mapper.trade;


import cn.itbeien.common.vo.trade.PlatPayDetailQryPar;

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
public interface IPlatPayDetailDao {
	
	/**
	 * 保存用户申请提现的信息
	 * @param param
	 * @return
	 */
	public int saveSettlementInfo(Map<String, Object> param);
	
	/**
	 * 财务管理->结算记录
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getSettlementList(Map<String, Object> param);
	
	
	/**
	 * 交易管理->代付订单
	 * 商户代付流水
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getPlatPayDetailList(Map<String, Object> param);
	
	/**
	 * 财务管理->资金记录
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getMerchantCapitalChangeList(Map<String, Object> param); 
	
	
	Map<String,Object>  getpayForSumAmtAndCount(PlatPayDetailQryPar param);
	
}
