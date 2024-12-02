package cn.itbeien.business.merchant.service;

import cn.itbeien.business.merchant.domain.ExtMerchantInfo;
import cn.itbeien.business.merchant.domain.MerchantInfo;
import cn.itbeien.business.merchant.domain.easypay.ChannelMerchantInfo;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IChannelMerchantService {
    public List<ExtMerchantInfo> selectChannelMerchantList(ExtMerchantInfo channelMerchantInfo);


    public ExtMerchantInfo findMercInfoById(Long id);

    /**
     * 审核并发起进件
     * @param channelMerchantInfo
     * @return
     */
    public int auditMerchant(ExtMerchantInfo channelMerchantInfo);

    /**
     * 小微商户进件---调用支付机构进件接口
     * @param merchantInfo
     * @return
     */
    void merchantReg(MerchantInfo merchantInfo, ChannelMerchantInfo channelMerchantInfo) throws Exception;

    /**
     * 标准商户(个体户和企业)---调用支付机构进件接口
     * @param merchantInfo
     * @return
     */
    void baseMerchantReg(MerchantInfo merchantInfo, ChannelMerchantInfo channelMerchantInfo) throws Exception;

    int deleteMercByIds(Long [] ids);
}
