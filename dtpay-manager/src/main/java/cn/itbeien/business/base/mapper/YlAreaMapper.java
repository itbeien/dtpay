package cn.itbeien.business.base.mapper;


import cn.itbeien.business.base.domain.YlArea;
import cn.itbeien.business.base.domain.YlAreaVO;

import java.util.List;

public interface YlAreaMapper {

    int insert(YlArea row);

    int insertSelective(YlArea row);
    public List<YlAreaVO> selectCnArea(String parentCode);
    public String selectYlIdAreaByCode(String ylCode);
}