package cn.itbeien.terminal.exception;

import cn.itbeien.terminal.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvisor {
    /**
     * 系统异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e){
        log.error("GlobalExceptionAdvisor.handleException",e);
        ResponseData responseData = new ResponseData("code","msg");
        return responseData;
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseData handleBusinessException(BusinessException e){
        log.error("GlobalExceptionAdvisor.handleOrderException",e);
        ResponseData responseData = new ResponseData(e.getCode(),e.getMessage());
        return responseData;
    }
}
