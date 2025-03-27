package cn.itbeien.business.merchant.mapper;
import cn.itbeien.business.merchant.domain.ExtMerchantInfo;

import java.util.List;

public interface ExtMerchantInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ExtMerchantInfo row);

    int insertSelective(ExtMerchantInfo row);


    ExtMerchantInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExtMerchantInfo row);

    int updateByPrimaryKey(ExtMerchantInfo row);

    int deleteMercByIds(Long[] ids);

    List<ExtMerchantInfo> selectChannelMerchantList(ExtMerchantInfo extMerchantInfo);
}