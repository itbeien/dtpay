package cn.itbeien.terminal.dao;



import cn.itbeien.terminal.entity.CnArea;
import cn.itbeien.terminal.vo.CnAreaVO;

import java.util.List;

public interface CnAreaMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CnArea row);

    int insertSelective(CnArea row);

    CnArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CnArea row);

    int updateByPrimaryKey(CnArea row);

    public List<CnAreaVO> selectCnArea(String parentCode);

}