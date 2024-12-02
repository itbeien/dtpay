package cn.itbeien.business.base.service;


import cn.itbeien.business.base.domain.CnAreaVO;
import cn.itbeien.business.base.domain.Mcc;
import cn.itbeien.business.base.domain.YlAreaVO;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
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
