package cn.itbeien.terminal.service.impl;



import cn.itbeien.terminal.dao.CnAreaMapper;
import cn.itbeien.terminal.dao.MccMapper;
import cn.itbeien.terminal.dao.WtZfxtInfoMapper;
import cn.itbeien.terminal.dao.YlAreaMapper;
import cn.itbeien.terminal.service.ISystemService;
import cn.itbeien.terminal.vo.BankVO;
import cn.itbeien.terminal.vo.CnAreaVO;
import cn.itbeien.terminal.vo.MccVO;
import cn.itbeien.terminal.vo.WtZfxtInfoVO;
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
    private WtZfxtInfoMapper wtZfxtInfoMapper;

    @Resource
    private YlAreaMapper ylAreaMapper;

    @Override
    public List<MccVO> selectMccParent() {
        return this.mccMapper.selectMccParent();
    }

    @Override
    public List<MccVO> selectMccByParent(String mccParent) {
        return this.mccMapper.selectMccByParent(mccParent);
    }

    /**
     * 根据父区域编号查询省市区数据
     * @param parentCode
     * @return
     */
    @Override
    public List<CnAreaVO> selectCnArea(String parentCode) {
        return this.ylAreaMapper.selectCnArea(parentCode);
    }

    @Override
    public List<WtZfxtInfoVO> selectWtZfxtInfo(String keyword, Integer page, Integer pageSize) {
        pageSize = pageSize*page;
        return this.wtZfxtInfoMapper.selectWtZfxtInfo(keyword,page,pageSize);
//        return this.wtZfxtInfoMapper.selectWtZfxtInfo(keyword);
    }

    @Override
    public Integer selectWtZfxtInfoCount(String keyword) {
        return this.wtZfxtInfoMapper.selectWtZfxtInfoCount(keyword);
    }

    @Override
    public List<BankVO> selectBankByHbdm(String hbdm) {
        return this.wtZfxtInfoMapper.selectBankByHbdm(hbdm);
    }
}
