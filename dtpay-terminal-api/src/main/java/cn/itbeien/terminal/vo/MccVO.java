package cn.itbeien.terminal.vo;

import lombok.Data;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Data
public class MccVO {
    private String value;

    private String text;

    private List<MccVO> children;
}
