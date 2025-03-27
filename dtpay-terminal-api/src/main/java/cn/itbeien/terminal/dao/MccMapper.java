package cn.itbeien.terminal.dao;


import cn.itbeien.terminal.entity.Mcc;
import cn.itbeien.terminal.vo.MccVO;

import java.util.List;

public interface MccMapper {

    int insert(Mcc row);

    int insertSelective(Mcc row);


    List<MccVO> selectMccParent();

    List<MccVO> selectMccByParent(String mccParent);

}