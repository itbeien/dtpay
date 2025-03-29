package cn.itbeien.common.config;

import cn.itbeien.common.security.filter.JwtAuthenticationTokenFilter;
import cn.itbeien.common.security.handle.AuthenticationEntryPointImpl;
import cn.itbeien.common.security.handle.LogoutSuccessHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * spring security 配置类
 * Copyright© 2025 itbeien
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * 允许匿名访问的地址
     */
   private final PermitAllUrl permitAllUrl;
    /**
     * 认证失败处理类
     */
   private final AuthenticationEntryPointImpl unauthorizedHandler;

   private final LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * token认证过滤器
     */
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 跨域过滤器
     */
    private final CorsFilter corsFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF禁用，因为不使用session
        http.csrf(AbstractHttpConfigurer::disable)
                // 禁用HTTP响应标头
                // 合并headers配置
                .headers(headers -> {
                    headers.cacheControl(HeadersConfigurer.CacheControlConfig::disable);
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                // 认证失败处理类
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
                // 基于token，所以不需要session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        // 注解标记允许匿名访问的url
        http.authorizeHttpRequests(authorize -> {
            permitAllUrl.getUrls().forEach(url -> authorize.requestMatchers(url).permitAll());

            authorize.requestMatchers("/login", "/register", "/captchaImage").permitAll()
                    .requestMatchers(HttpMethod.GET, "/", "/*.html", "/*/*.html", "/*/*.css", "/*/*.js", "/profile/**").permitAll()
                    .requestMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                    .anyRequest().authenticated();
        });

        // 添加Logout filter
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler));

        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加CORS filter
        http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        http.addFilterBefore(corsFilter, LogoutFilter.class);

        return http.build();
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
