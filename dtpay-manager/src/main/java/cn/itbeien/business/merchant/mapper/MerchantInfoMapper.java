package cn.itbeien.business.merchant.mapper;

import cn.itbeien.business.merchant.domain.MerchantInfo;

import java.util.List;

public interface MerchantInfoMapper {

    int deleteByPrimaryKey(String mercNo);

    int insert(MerchantInfo row);

    int insertSelective(MerchantInfo row);

    MerchantInfo selectByPrimaryKey(String mercNo);

    int updateByPrimaryKeySelective(MerchantInfo row);

    int updateByPrimaryKey(MerchantInfo row);

    List<MerchantInfo> selectMerchantInfoList(MerchantInfo merchantInfo);

    int deleteMercByNos(String []mercNos);

}