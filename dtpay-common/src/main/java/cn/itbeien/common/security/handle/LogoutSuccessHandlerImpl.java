package cn.itbeien.common.security.handle;

import cn.itbeien.common.component.AsyncManager;
import cn.itbeien.common.component.factory.AsyncFactory;
import cn.itbeien.common.constant.Constants;
import cn.itbeien.common.service.TokenService;
import cn.itbeien.common.util.MessageUtils;
import cn.itbeien.common.util.ServletUtils;
import cn.itbeien.common.util.StringUtils;
import cn.itbeien.common.vo.AjaxResult;
import cn.itbeien.common.vo.LoginUser;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * 自定义退出处理类 返回成功
 * Copyright© 2025 itbeien
 */
@Configuration
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    private final TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.am().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success(MessageUtils.message("user.logout.success"))));
    }
}
