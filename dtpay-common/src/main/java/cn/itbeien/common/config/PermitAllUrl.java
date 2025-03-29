package cn.itbeien.common.config;

import cn.itbeien.common.annotation.Anonymous;
import cn.itbeien.common.util.SpringUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * Copyright© 2025 itbeien
 */
@Configuration
public class PermitAllUrl implements InitializingBean {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private static final String[] DEFAULT_IGNORE_URLS = new String[] { "/actuator/**", "/error", "/v3/api-docs" };

    @Getter
    @Setter
    private List<String> urls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        urls.addAll(Arrays.asList(DEFAULT_IGNORE_URLS));
        RequestMappingHandlerMapping mapping = SpringUtils.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解 替代path variable 为 *
            Anonymous method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Anonymous.class);
            Optional.ofNullable(method)
                    .ifPresent(inner -> Objects.requireNonNull(info.getPathPatternsCondition())
                            .getPatternValues()
                            .forEach(url -> urls.add(RegExUtils.replaceAll(url, PATTERN, "*"))));

            // 获取类上边的注解, 替代path variable 为 *
            Anonymous controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Anonymous.class);
            Optional.ofNullable(controller)
                    .ifPresent(inner -> Objects.requireNonNull(info.getPathPatternsCondition())
                            .getPatternValues()
                            .forEach(url -> urls.add(RegExUtils.replaceAll(url, PATTERN, "*"))));
        });
    }
}
