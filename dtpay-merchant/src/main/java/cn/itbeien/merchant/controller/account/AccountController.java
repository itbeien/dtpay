package cn.itbeien.merchant.controller.account;

import cn.itbeien.common.annotation.Anonymous;
import cn.itbeien.auth.controller.BaseController;
import cn.itbeien.common.entity.merchant.CusLoginRecord;
import cn.itbeien.common.exception.ServiceException;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import cn.itbeien.common.vo.UserPwdVO;
import cn.itbeien.merchant.service.common.IUserLoginRecordService;
import cn.itbeien.merchant.service.merchant.IMerchantInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class AccountController extends BaseController {
	
	private final IUserLoginRecordService userLoginRecordServiceImpl;

	private final IMerchantInfoService merchantInfoServiceImpl;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 账号对应商户号信息
	 * @param
	 * @return
	 */
	@Anonymous
	@RequestMapping("/accountInfo")
	public AjaxResult accountInfo() {
		return success();
	}
	
	/**
	 * 账号登陆记录
	 * @return
	 */
	@Anonymous
	@PostMapping("/accountList")
	@PreAuthorize("@dss.hasPermi('dt:agent:list')")
	public TableDataInfo accountList(@Validated @RequestBody CusLoginRecord cusLoginRecord) {
		startPage(cusLoginRecord);
		List<CusLoginRecord> list = null;
		try {
			list = userLoginRecordServiceImpl.getUserLoginRecordList(cusLoginRecord);
		} catch (Exception e) {
			log.error("获取账号登陆记录异常：", e);
			throw new ServiceException("获取账号登陆记录异常");
		}
		return getDataTable(list);
	}
	
	/**
	 * 修改支付密码
	 * @param userPwdVO
	 * @return
	 */
	@Anonymous
	@RequestMapping("/updatePass")
	@PreAuthorize("@dss.hasPermi('dt:agent:list')")
	public String updatePass(@Validated @RequestBody UserPwdVO userPwdVO) {
		try{
			bCryptPasswordEncoder.matches(userPwdVO.getOldPass(), userPwdVO.getPassword());
			merchantInfoServiceImpl.updatePasswd(null,null);
		}catch(Exception e){
			log.error("修改密码异常：", e);
		}
		return "fail";
	}

}
