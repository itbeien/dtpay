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
public interface IMerchantService {
    public List<MerchantInfo> selectMerchantInfoList(MerchantInfo merchantInfo);


    public MerchantInfo findMercInfoByMercNo(String mercNo);

    /**
     * 审核并发起进件
     * @param merchantInfo
     * @return
     */
    public int auditMerchant(MerchantInfo merchantInfo);

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

    int deleteMercByNos(String [] mercNos);

    List<ExtMerchantInfo> queryExtMerchantInfo(ExtMerchantInfo extMerchantInfo);
}
