package cn.itbeien.merchant.service.merchant;


import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;
import java.util.List;

public interface IMerchantPaywayService {
	
	/**
	 * 商户基本信息
	 * @param param
	 * @return
	 */
	public List<MerchantPaywayMapping> getMerchantPaywayListByPage(String param) throws Exception;
	
}
