package cn.itbeien.business.merchant.service;

import cn.itbeien.business.merchant.domain.easypay.ChannelMerchantInfo;
import cn.itbeien.business.merchant.domain.easypay.ImageInfo;

import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IMerchantManagement {

    public String merchantImgUpload(ImageInfo imageInfo);
    public String merchantInfoEntering();

    public String initSmallMerchant(ChannelMerchantInfo merchantInfo, Map<String,String> picMap);

    public String initBaseMerchant(ChannelMerchantInfo merchantInfo, Map<String,String> picMap);
}
