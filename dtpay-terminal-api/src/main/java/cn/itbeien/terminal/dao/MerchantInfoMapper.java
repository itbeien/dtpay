package cn.itbeien.terminal.dao;


import cn.itbeien.terminal.entity.MerchantInfo;
import cn.itbeien.terminal.vo.BaseMerchantVO;
import cn.itbeien.terminal.vo.SmallMerchantVO;

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

    SmallMerchantVO selectEditSmallMerchantAllCreator(String creator);

    BaseMerchantVO selectEditBaseMerchantAllCreator(String creator);
}