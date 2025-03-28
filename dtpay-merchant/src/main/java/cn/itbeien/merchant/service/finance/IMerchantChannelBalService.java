package cn.itbeien.merchant.service.finance;

import cn.itbeien.common.entity.finance.MerchantChannelBal;
import cn.itbeien.common.entity.finance.MerchantChannelBalExt;
import cn.itbeien.common.entity.finance.MerchantChannelBalKey;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IMerchantChannelBalService {
    int deleteByPrimaryKey(MerchantChannelBalKey key);

    int insert(MerchantChannelBal record);

    int insertSelective(MerchantChannelBal record);

    MerchantChannelBal selectByPrimaryKey(MerchantChannelBalKey key);

    int updateByPrimaryKeySelective(MerchantChannelBal record);

    int updateByPrimaryKey(MerchantChannelBal record);
    
    List<MerchantChannelBalExt>  selectMerchantChannelBalExtList(MerchantChannelBal record);
    
    int updateFreezeAmtByPrimaryKey(MerchantChannelBal record);
    
    List<MerchantChannelBalExt> selectNoPayForMerchantChannelBalExtList(MerchantChannelBal record);
    
    int batchUpdateAvaiBal(List<MerchantChannelBalExt> chooseChns);
}