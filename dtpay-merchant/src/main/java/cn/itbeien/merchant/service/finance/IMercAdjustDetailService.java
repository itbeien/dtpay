package cn.itbeien.merchant.service.finance;

import cn.itbeien.common.entity.merchant.MercAdjustDetail;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.merchant.MercAdjustDetailVO;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMercAdjustDetailService {
	
	/**
	 * 查询资金记录
	 * @param param
	 * @return
	 */
	public BootTable<MercAdjustDetail> getMercAdjustDetailListByPage(MercAdjustDetailVO param) throws Exception;
	
}
