package cn.itbeien.merchant.controller;

import cn.itbeien.common.config.ProjectConfig;
import cn.itbeien.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * Copyright© 2025 itbeien
 */
@RestController
@RequiredArgsConstructor
public class SysIndexController
{
    /** 系统基础配置 */
    private final ProjectConfig projectConfig;

    private final RedisTemplate redisTemplate;

    //@PostConstruct
    public void init() {
        redisTemplate.opsForValue().set("test", "This is a test");
    }

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("欢迎使用{}系统API，当前版本：v{}，请通过前端系统访问。", projectConfig.getName(), projectConfig.getVersion());
    }
}
