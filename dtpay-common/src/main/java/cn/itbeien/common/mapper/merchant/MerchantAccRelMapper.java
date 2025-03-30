package cn.itbeien.common.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantAccRel;
import org.apache.ibatis.annotations.Param;

public interface MerchantAccRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantAccRel record);

    int insertSelective(MerchantAccRel record);

    MerchantAccRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantAccRel record);

    int updateByPrimaryKey(MerchantAccRel record);
        
    int updateBymercNoSelective(MerchantAccRel record);
    
    MerchantAccRel selectByCorePayAcc(String corePayAcc);
    /**
     * 
     * 方法用途:根据账号类型及商户编号获取商户虚拟账户表信息 <br>
     * @param mercNo商户编号
     * @param coreAcctType 账号类型(00虚拟账户)
     * @return
     */
    MerchantAccRel selectByMercAcct(@Param("mercNo")String mercNo,@Param("coreAcctType")String coreAcctType);
    
    /**
     * 根据账号类型及商户编号更新商户账户表虚拟余额
     * @param record
     * @return
     */
    int updateAccBalByCondition(MerchantAccRel record);
    
    /**
     * 根据账号类型及商户编号冻结商户账户表虚户可用余额
     * @param record
     * @return
     */
    int freezeAccAvailBalByCondition(MerchantAccRel record);
    
    
    /**
     * 根据账号类型及商户编号解冻商户账户表虚户可用余额
     * @param record
     * @return
     */
    int unfreezeAccAvailBalByCondition(MerchantAccRel record);
    
    /**
     * updateAccBalByMercNo:(根据商户号 更新金额).  
     *
     * :2018年4月16日下午10:50:13
     *
     * @param record
     * @return
     */
    int updateAccBalByMercNo(MerchantAccRel record);
}