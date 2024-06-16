package cn.itbeien.business.merchant.service;

import cn.itbeien.business.merchant.domain.easypay.ChannelMerchantInfo;
import cn.itbeien.business.merchant.domain.easypay.ImageInfo;

import java.util.Map;

/**
 * @author beien
 * @date 2024-03-26 11:50
 * Copyright© 2024 beien
 * 商户入网进件管理
 * https://mtest.eycard.cn:4443/AG_MerchantManagementSystem_Core/agent/api/gen
 */
public interface IMerchantManagement {

    public String merchantImgUpload(ImageInfo imageInfo);
    public String merchantInfoEntering();

    public String initSmallMerchant(ChannelMerchantInfo merchantInfo, Map<String,String> picMap);

    public String initBaseMerchant(ChannelMerchantInfo merchantInfo, Map<String,String> picMap);
}
