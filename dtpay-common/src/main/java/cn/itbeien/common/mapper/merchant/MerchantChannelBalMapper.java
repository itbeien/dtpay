package cn.itbeien.common.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantChannelBal;
import cn.itbeien.common.entity.merchant.MerchantChannelBalExt;
import cn.itbeien.common.entity.merchant.MerchantChannelBalKey;

import java.util.List;

public interface MerchantChannelBalMapper {
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
    
    /**
     * 批量冻结各渠道交易金额
     * @param chooseChns
     * @return
     */
    int batchFreezeAvaiBal(List<MerchantChannelBalExt> chooseChns);
    
    /**
     * 批量冻结各渠道交易金额
     * @param chooseChns
     * @return
     */
    int batchUnfreezeAvaiBal(List<MerchantChannelBalExt> chooseChns);
    
    /**
     * batchUpdateChaBal:(批量更新商户对应渠道 金额).  
     *
     * :2018年4月17日上午9:52:26
     *
     * @param chooseChns
     * @return
     */
    int batchUpdateChaBal(List<MerchantChannelBal> chooseChns);
    
    /**
     * batchUpdateFailChaBal:(批量更新商户对应渠道 金额).  
     *
     * :2018年4月17日上午9:52:26
     *
     * @param chooseChns
     * @return
     */
//    int batchUpdateFailChaBal(List<MerchantChannelBal> chooseChns);
}