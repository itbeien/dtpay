package cn.itbeien.terminal.util;

import cn.itbeien.terminal.enums.SystemEnum;
import cn.itbeien.terminal.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Component
@Slf4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 header 中的 Authorization 信息
        String token = request.getHeader("Authorization");

        if (StringUtils.isEmpty(token)){
            throw new BusinessException(SystemEnum.TOKEN_PARAMS_NULL.getCode(), SystemEnum.TOKEN_PARAMS_NULL.getMessage());
        }
        //通过token获取uid
        String uid  = JwtUtils.getUserByToken(token);
        if (StringUtils.isEmpty(uid)){
            throw new BusinessException(SystemEnum.TOKEN_PARAMS_ERROR.getCode(), SystemEnum.TOKEN_PARAMS_ERROR.getMessage());
        }

        request.setAttribute("uid",uid);

        boolean isSuccess = super.preHandle(request, response, handler);
        return isSuccess;
    }
}