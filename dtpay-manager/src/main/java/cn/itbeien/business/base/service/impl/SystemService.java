package cn.itbeien.business.base.service.impl;

import cn.itbeien.business.base.domain.CnAreaVO;
import cn.itbeien.business.base.domain.Mcc;
import cn.itbeien.business.base.domain.YlAreaVO;
import cn.itbeien.business.base.mapper.CnAreaMapper;
import cn.itbeien.business.base.mapper.MccMapper;
import cn.itbeien.business.base.mapper.YlAreaMapper;
import cn.itbeien.business.base.service.ISystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
public class SystemService implements ISystemService {
    @Resource
    private MccMapper mccMapper;

    @Resource
    private CnAreaMapper cnAreaMapper;

    @Resource
    private YlAreaMapper ylAreaMapper;

    @Override
    public List<Mcc> selectMccParent() {
        return this.mccMapper.selectMccParent();
    }

    @Override
    public List<Mcc> selectMccByParent(String mccParent) {
        return this.mccMapper.selectMccByParent(mccParent);
    }

    /**
     * 根据父区域编号查询省市区数据
     * @param parentCode
     * @return
     */
    @Override
    public List<CnAreaVO> selectCnArea(String parentCode) {
        return this.cnAreaMapper.selectCnArea(parentCode);
    }

    public List<YlAreaVO> selectYlArea(String parentCode) {
        return this.ylAreaMapper.selectCnArea(parentCode);
    }

    @Override
    public String selectYlIdAreaByCode(String ylCode) {
        return this.ylAreaMapper.selectYlIdAreaByCode(ylCode);
    }
}
