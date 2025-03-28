package cn.itbeien.merchant.controller.finance;


import cn.itbeien.common.entity.finance.MerchantChannelBal;
import cn.itbeien.common.entity.finance.MerchantChannelBalExt;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeOrderSeq;
import cn.itbeien.common.enums.TradeType;
import cn.itbeien.common.enums.ZeroOneEnum;
import cn.itbeien.common.vo.BootTable;
import cn.itbeien.common.vo.merchant.MerchantAccRelVO;
import cn.itbeien.common.vo.trade.PlatPayDetailQryPar;
import cn.itbeien.merchant.service.finance.IMerchantAccRelService;
import cn.itbeien.merchant.service.finance.IMerchantChannelBalService;
import cn.itbeien.merchant.service.merchant.IMerchantInfoService;
import cn.itbeien.merchant.service.trade.IPlatPayDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class FinanceController {
	
	private final IMerchantAccRelService merchantAccRelServiceImpl;

	private final IPlatPayDetailService platPayDetailServiceImpl;

	private final IMerchantChannelBalService merchantChannelBalServiceImpl;

	private final IMerchantInfoService merchantInfoServiceImpl;
	
	@Value("${gateway.url}")
	private String gatewayUrl;
	
	/**
	 * 商户账号资金
	 * @param request
	 * @return
	 */
	@RequestMapping("/settlementRecord")
	public String settlementRecord(HttpServletRequest request){
		Map<String, Object> param = new HashMap<>();
		MerchantAccRelVO merchantAccRel = merchantAccRelServiceImpl.getMerchantAccRel(null);
		request.setAttribute("merchantAccRel", merchantAccRel);
		return "finance/settlementRecord";
	}
	
	/**
	 * 账户可提金额
	 * @param
	 * @return
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
	public Object settlementList(PlatPayDetailQryPar platPayDetailQryPar) {
		Map<String, Object> param = new HashMap<>();
		param.put("trandType", TradeType.WITHDRAWALS.getCode());// 交易业务类型  ：01-支付 11-充值  02-代付  03-提现
		BootTable<PlatPayDetail> bootTable = platPayDetailServiceImpl.getSettlementListByPage(platPayDetailQryPar);
		return bootTable;
	}

	@RequestMapping("/fundRecord")
	public String fundRecord(){
		return "finance/fundRecord";
	}
	
	/**
	 * 商户资金记录（提现|代付、支付、退款）
	 * @return
	 */
	@RequestMapping("/fundList")
	public Object fundList(PlatPayDetailQryPar platPayDetailQryPar) {
		BootTable<TradeOrderSeq> bootTable = platPayDetailServiceImpl.getMerchantCapitalChangeList(platPayDetailQryPar);
		return bootTable;
	}
	
}
