package cn.itbeien.terminal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author beien
 * @date 2024-05-09 9:41
 * Copyright© 2024 beien
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    public static final String RESOURCE_PREFIX = "/profile";

    @Value("${dtpay.merchant.upload}")
    private String profile;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler(RESOURCE_PREFIX + "/**")
                .addResourceLocations("file:" + profile + "/");
    }
}
