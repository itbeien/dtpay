package cn.itbeien.common.vo;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Data
public class UserPwdVO {
    //"^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";// 校验字符串为8到16位数字和字母组成
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8到16位数字和字母组成")
    private String oldPass;
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8到16位数字和字母组成")
    private String password;
}
