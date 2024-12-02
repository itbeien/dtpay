package cn.itbeien.terminal.dao;



import cn.itbeien.terminal.entity.YlArea;
import cn.itbeien.terminal.vo.CnAreaVO;

import java.util.List;

public interface YlAreaMapper {

    int insert(YlArea row);

    int insertSelective(YlArea row);
    public List<CnAreaVO> selectCnArea(String parentCode);

}