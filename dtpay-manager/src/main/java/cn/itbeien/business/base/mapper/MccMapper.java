package cn.itbeien.business.base.mapper;


import cn.itbeien.business.base.domain.Mcc;

import java.util.List;

public interface MccMapper {

    int insert(Mcc row);

    int insertSelective(Mcc row);


    List<Mcc> selectMccParent();

    List<Mcc> selectMccByParent(String mccParent);

}