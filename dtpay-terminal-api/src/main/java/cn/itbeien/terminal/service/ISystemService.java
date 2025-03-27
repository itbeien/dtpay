package cn.itbeien.terminal.service;



import cn.itbeien.terminal.vo.BankVO;
import cn.itbeien.terminal.vo.CnAreaVO;
import cn.itbeien.terminal.vo.MccVO;
import cn.itbeien.terminal.vo.WtZfxtInfoVO;

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

    List<MccVO> selectMccParent();

    /**
     * 获取行业子类目
     */
    List<MccVO> selectMccByParent(String mccParent);

    List<CnAreaVO> selectCnArea(String parentCode);

    List<WtZfxtInfoVO> selectWtZfxtInfo(String keyword, Integer page, Integer pageSize);

    Integer selectWtZfxtInfoCount(String keyword);

    List<BankVO> selectBankByHbdm(String hbdm);
}
