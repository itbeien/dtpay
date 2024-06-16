package cn.itbeien.business.base.service;


import cn.itbeien.business.base.domain.CnAreaVO;
import cn.itbeien.business.base.domain.Mcc;
import cn.itbeien.business.base.domain.YlAreaVO;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-21 11:24
 * Copyright© 2024 beien
 */
public interface ISystemService {
    /**
     * 获取行业父类目
     */

    List<Mcc> selectMccParent();

    /**
     * 获取行业子类目
     */
    List<Mcc> selectMccByParent(String mccParent);

    List<CnAreaVO> selectCnArea(String parentCode);

    public List<YlAreaVO> selectYlArea(String parentCode);

    public String selectYlIdAreaByCode(String ylCode);
}
