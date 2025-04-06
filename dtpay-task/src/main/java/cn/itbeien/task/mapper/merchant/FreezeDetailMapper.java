package cn.itbeien.task.mapper.merchant;

import cn.itbeien.common.entity.FreezeDetail;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface FreezeDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreezeDetail record);

    int insertSelective(FreezeDetail record);

    FreezeDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreezeDetail record);

    int updateByPrimaryKey(FreezeDetail record);
    /**
     * 获取当日需要解冻商户冻结资金(状态为冻结)
     * 方法用途: <br>
     * @return
     */
    List<FreezeDetail> selectUnfreeze();
}