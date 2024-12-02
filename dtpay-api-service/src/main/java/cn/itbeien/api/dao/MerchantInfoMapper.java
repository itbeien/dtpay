package cn.itbeien.api.dao;


import cn.itbeien.api.entity.merchant.MerchantInfo;

import java.util.List;

public interface MerchantInfoMapper {

    int deleteByPrimaryKey(String mercNo);

    int insert(MerchantInfo row);

    int insertSelective(MerchantInfo row);

    MerchantInfo selectByPrimaryKey(String mercNo);

    int updateByPrimaryKeySelective(MerchantInfo row);

    int updateByPrimaryKey(MerchantInfo row);

    MerchantInfo selectSmallByCreator(String creator);

    MerchantInfo selectBaseByCreator(String creator);

    List<MerchantInfo> selectMerchantAllCreator(String creator);
}