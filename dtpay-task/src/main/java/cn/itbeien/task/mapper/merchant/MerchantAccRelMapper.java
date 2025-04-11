package cn.itbeien.task.mapper.merchant;

import cn.itbeien.common.entity.merchant.MerchantAccRel;
import org.apache.ibatis.annotations.Param;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface MerchantAccRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantAccRel record);

    int insertSelective(MerchantAccRel record);

    MerchantAccRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantAccRel record);

    int updateByPrimaryKey(MerchantAccRel record);
        
    int updateBymercNoSelective(MerchantAccRel record);
    
    /**
     * 
     * 方法用途:根据账号类型及商户编号获取商户虚拟账户表信息 <br>
     * @param mercNo商户编号
     * @param coreAcctType 账号类型(00虚拟账户)
     * @return
     */
    MerchantAccRel selectByMercAcct(@Param("mercNo")String mercNo,@Param("coreAcctType")String coreAcctType);
}