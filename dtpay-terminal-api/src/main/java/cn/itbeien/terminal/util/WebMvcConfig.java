package cn.itbeien.terminal.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorityInterceptor authorityInterceptor;




    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] noCheck = new String[]{"/api/send.do","/api/login","/api/initBank","/api/area","/api/mcc","/api/upload"};
        String[] checkSettleAccounts = new String[]{"/user/login.html","/user/confirmCheckout.html","/goods/uploadFile.html", "/user/checkout.html","/scr/sw/doLoadSwMenu.html","/scr/sw/doLoadSwLeftMenuCategory.html","/scr/sw/doLoadSwCategoryGoods.html"};

        //registry.addInterceptor(authorityInterceptor).addPathPatterns("/**").excludePathPatterns("/system/uploadFile.html") ; //不需要拦截的
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**").excludePathPatterns(noCheck) ; //不需要拦截的
    }


/*    *//*
     * @descript 绑定自定义参数
     *//*
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(swResolver());
    }

    @Bean
    public ManagerParamResolver swResolver() {
        return new ManagerParamResolver();
    }*/


    /*
     * @descript  CORS ,只对POST,OPTIONS请求
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("POST");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
