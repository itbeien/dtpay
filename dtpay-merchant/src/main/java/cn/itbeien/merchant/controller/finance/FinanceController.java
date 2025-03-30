package cn.itbeien.merchant.controller.finance;


import cn.itbeien.auth.controller.BaseController;
import cn.itbeien.common.entity.merchant.MerchantChannelBal;
import cn.itbeien.common.entity.merchant.MerchantChannelBalExt;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.enums.TradeType;
import cn.itbeien.common.enums.ZeroOneEnum;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import cn.itbeien.common.vo.merchant.MerchantAccRelVO;
import cn.itbeien.common.vo.trade.PlatPayDetailQryPar;
import cn.itbeien.merchant.service.finance.IMerchantAccRelService;
import cn.itbeien.merchant.service.finance.IMerchantChannelBalService;
import cn.itbeien.merchant.service.merchant.IMerchantInfoService;
import cn.itbeien.merchant.service.trade.IPlatPayDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/finance")
@RequiredArgsConstructor
@Slf4j
public class FinanceController extends BaseController {
	
	private final IMerchantAccRelService merchantAccRelServiceImpl;

	private final IPlatPayDetailService platPayDetailServiceImpl;

	private final IMerchantChannelBalService merchantChannelBalServiceImpl;

	private final IMerchantInfoService merchantInfoServiceImpl;
	
	@Value("${gateway.url}")
	private String gatewayUrl;
	
	/**
	 * @author itbeien
	 * 项目网站：https://www.itbeien.cn
	 * 公众号：贝恩聊架构
	 * 全网同名，欢迎小伙伴们关注
	 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
	 * 根据商户号获取 商户账号资金
	 * Copyright© 2025 itbeien
	 */
	@RequestMapping("/settlementRecord")
	public AjaxResult settlementRecord(){
		MerchantAccRelVO merchantAccRel = merchantAccRelServiceImpl.getMerchantAccRel(null);
		return AjaxResult.success(merchantAccRel);
	}
	
	/**
	 * 账户可提金额
	 */
	@RequestMapping("/cashoutList")
	@ResponseBody
	public String cashoutList(){
		
		String acctAvaiBal ="";
		try{
			MerchantInfo merchantInfo = merchantInfoServiceImpl.getMerchantInfoByMercNo(null);

			if(ZeroOneEnum.zero.getCode().equals(merchantInfo.getPayFlag())){
				MerchantAccRelVO merchantAccRel = merchantAccRelServiceImpl.getMerchantAccRel(null);
				acctAvaiBal =  merchantAccRel.getAcctAvaiBal();
			}else{
				MerchantChannelBal record = new MerchantChannelBal();
				List<MerchantChannelBalExt>  list = merchantChannelBalServiceImpl.selectMerchantChannelBalExtList(record);
				BigDecimal  availAmt = new BigDecimal(0);
				if(!list.isEmpty()){
					availAmt = list.get(0).getChnAvaiBal();
				}
				acctAvaiBal =  availAmt.toPlainString();
			}

		}catch (Exception e) {
			log.error("查询账户可提金额异常:{}",e);
		}
		return acctAvaiBal;
	}
	
	/**
	 * 申请结算
	 * @param cashReal 到账金额
	 * @return
	 */
	@RequestMapping("/settlement")
	public String settlement(@RequestParam(value = "cashReal", required = false) String cashReal) {
		String result = null;

		try{
			MerchantInfo merchantInfo = merchantInfoServiceImpl.getMerchantInfoByMercNo(null);
			BigDecimal  availAmt = new BigDecimal(0);
			if(ZeroOneEnum.zero.getCode().equals(merchantInfo.getPayFlag())){
				Map<String, Object> param = new HashMap<>();
				MerchantAccRelVO merchantAccRel = merchantAccRelServiceImpl.getSettlementInfo(null);
				result = merchantAccRel.getAcctAvaiBal();
			}else{
				MerchantChannelBal record = new MerchantChannelBal();
				List<MerchantChannelBalExt>  list = merchantChannelBalServiceImpl.selectMerchantChannelBalExtList(record);
				if(!list.isEmpty()){
					availAmt = list.get(0).getChnAvaiBal();
				}
			}

			BigDecimal needAmt = new BigDecimal(cashReal).add(merchantInfo.getPayFeeValue());

		}catch(Exception e){
			log.error("申请结算异常:{}",e);
		}
		
		return result;
		
	}
	
	/**
	 * 商户账号提现记录
	 * @return
	 */
	@RequestMapping("/settlementList")
	public TableDataInfo settlementList(@RequestBody PlatPayDetailQryPar platPayDetailQryPar) {
		platPayDetailQryPar.setTradeType(TradeType.WITHDRAWALS.getCode());
		List<PlatPayDetail> list = platPayDetailServiceImpl.getSettlementListByPage(platPayDetailQryPar);
		return getDataTable(list);
	}
	/**
	 * 商户资金记录（提现|代付、支付、退款）
	 * @return
	 */
	@RequestMapping("/fundList")
	public TableDataInfo fundList(@RequestBody PlatPayDetailQryPar platPayDetailQryPar) {
		startPage(platPayDetailQryPar);
		List<TradeOrderSeq> list = platPayDetailServiceImpl.getMerchantCapitalChangeList(platPayDetailQryPar);
		return getDataTable(list);
	}
	
}
