package cn.itbeien.api.service.merchant.impl;

import cn.itbeien.api.dao.MerchantInfoMapper;
import cn.itbeien.api.entity.merchant.MerchantInfo;
import cn.itbeien.api.service.merchant.IMerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MerchantService implements IMerchantService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    public int saveMerchant(MerchantInfo merchantInfo) {
        return merchantInfoMapper.insertSelective(merchantInfo);
    }
}
