package cn.itbeien.common.entity.merchant;

import lombok.Data;

import java.util.Date;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Data
public class CusLoginRecord {
    private Long id;
    private Long userName;
    private String loginIp;
    private Date createTime;
}
