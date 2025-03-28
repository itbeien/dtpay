package cn.itbeien.merchant.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(Exception e, HttpServletRequest request) {
		log.error("Exception", e);
		log.error("message:{}", "系统异常,请联系管理员!");
		return "error";
	}
}
