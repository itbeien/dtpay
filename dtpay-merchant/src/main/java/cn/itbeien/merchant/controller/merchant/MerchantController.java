package cn.itbeien.merchant.controller.merchant;

import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import cn.itbeien.merchant.service.merchant.IMerchantInfoService;
import cn.itbeien.merchant.service.merchant.IMerchantPaywayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Controller
@RequestMapping("/merchant")
@RequiredArgsConstructor
@Slf4j
public class MerchantController extends BaseController {
	
	private final IMerchantInfoService merchantInfoServiceImpl;

	private final IMerchantPaywayService merchantPaywayServiceImpl;
	
	/**
	 * 商户基本信息
	 * @param
	 * @return
	 */
	@RequestMapping("/merchantInfo")
	public AjaxResult merchantInfo() {
		MerchantInfo merchantInfo = null;
		try{
			merchantInfo = merchantInfoServiceImpl.getMerchantInfo(null);
		}catch(Exception e){
			log.error("查询商户信息异常",e);
		}
		return success(merchantInfo);
	}
	
	/**
	 * 商户上游产品列表
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/merchantList")
	@ResponseBody
	public TableDataInfo merchantList() {
		startPage();
		List<MerchantPaywayMapping> list = null;
		try {
			 list = merchantPaywayServiceImpl.getMerchantPaywayListByPage(null);
		}catch (Exception e){
			log.error("查询商户列表异常",e);
		}
		return getDataTable(list);
	}

}
