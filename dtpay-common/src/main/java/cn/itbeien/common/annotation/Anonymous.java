package cn.itbeien.common.annotation;

import java.lang.annotation.*;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * 匿名访问不需要进行鉴权注解
 * Copyright© 2025 itbeien
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
}
