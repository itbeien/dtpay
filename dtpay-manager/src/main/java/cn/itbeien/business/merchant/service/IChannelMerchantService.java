package cn.itbeien.business.merchant.service;

import cn.itbeien.business.merchant.domain.ExtMerchantInfo;
import cn.itbeien.business.merchant.domain.MerchantInfo;
import cn.itbeien.business.merchant.domain.easypay.ChannelMerchantInfo;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 10:20
 * Copyright© 2024 beien
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
