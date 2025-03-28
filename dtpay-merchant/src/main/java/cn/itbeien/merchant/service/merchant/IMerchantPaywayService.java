package cn.itbeien.merchant.service.merchant;


import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;
import cn.itbeien.common.vo.BootTable;

public interface IMerchantPaywayService {
	
	/**
	 * 商户基本信息
	 * @param param
	 * @return
	 */
	public BootTable<MerchantPaywayMapping> getMerchantPaywayListByPage(String param) throws Exception;
	
}
