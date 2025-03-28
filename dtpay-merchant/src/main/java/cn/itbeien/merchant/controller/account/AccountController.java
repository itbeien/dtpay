package cn.itbeien.merchant.controller.account;

import cn.itbeien.common.entity.merchant.CusLoginRecord;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.merchant.service.common.IUserLoginRecordService;
import cn.itbeien.merchant.service.merchant.IMerchantInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
	
	private final IUserLoginRecordService userLoginRecordServiceImpl;

	private final IMerchantInfoService merchantInfoServiceImpl;
	
	/**
	 * 账号对应商户号信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/accountInfo")
	public String accountInfo(HttpServletRequest request) {
		return "account/accountInfo";
	}
	
	/**
	 * 账号登陆记录
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/accountList")
	public Object accountList(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		int pageNumber1 = null != pageNumber ?  pageNumber.intValue() : 1;
		int pageSize1 = null != pageSize ? pageSize.intValue() : 10;
		Map<String, Object> param = new HashMap<>();
		param.put("pageNumber", pageNumber1);
		param.put("pageSize", pageSize1);
		BootTable<CusLoginRecord> bootTable = userLoginRecordServiceImpl.getUserLoginRecordList(null);
		return bootTable;
	}
	
	/**
	 * 修改支付密码
	 * @param oldPass
	 * @param password
	 * @return
	 */
	@RequestMapping("/updatePass")
	public String updatePass(@RequestParam("oldPass") String oldPass, @RequestParam("password") String password) {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";// 校验字符串为8到16位数字和字母组成
		if(!password.matches(regex)) {// 密码由8到16位数字和字母组成
			return "passwordError";
		}

		try{
			merchantInfoServiceImpl.updatePasswd(null);
		}catch(Exception e){
			log.error("修改密码异常：", e);
		}
		return "fail";
	}

}
