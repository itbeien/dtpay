package cn.itbeien.merchant.service.merchant.impl;

import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;
import cn.itbeien.merchant.mapper.merchant.IMerchantPaywayMapper;
import cn.itbeien.merchant.service.merchant.IMerchantPaywayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantPaywayServiceImpl implements IMerchantPaywayService {
	
	private final IMerchantPaywayMapper merchantPaywayMapper;

	@Override
	public List<MerchantPaywayMapping> getMerchantPaywayListByPage(String param) {
		List<MerchantPaywayMapping> list = merchantPaywayMapper.getMerchantPaywayList(param);
		return list;
	}

}
