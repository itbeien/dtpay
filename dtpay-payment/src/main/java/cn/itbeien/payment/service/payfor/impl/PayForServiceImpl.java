package cn.itbeien.payment.service.payfor.impl;

import cn.itbeien.common.entity.FreezeDetail;
import cn.itbeien.common.entity.merchant.MerchantChannelBal;
import cn.itbeien.common.entity.merchant.MerchantChannelBalExt;
import cn.itbeien.common.entity.merchant.MerchantAccRel;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeBatchInfo;
import cn.itbeien.common.enums.*;
import cn.itbeien.common.mapper.FreezeDetailMapper;
import cn.itbeien.common.mapper.ICommonMapper;
import cn.itbeien.common.mapper.PlatPayDetailMapper;
import cn.itbeien.common.mapper.merchant.MerchantAccRelMapper;
import cn.itbeien.common.mapper.merchant.MerchantChannelBalMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.DateUtils;
import cn.itbeien.common.util.SpringUtils;
import cn.itbeien.common.util.StringUtils;
import cn.itbeien.common.util.uuid.UUID;
import cn.itbeien.payment.channel.vo.ChannelVo;
import cn.itbeien.payment.channel.vo.ChnBatchPayForResponse;
import cn.itbeien.payment.channel.vo.ChnPayForResponse;
import cn.itbeien.common.redis.RedisLock;
import cn.itbeien.payment.core.vo.request.BatchPayForRequest;
import cn.itbeien.payment.core.vo.request.PayForRequest;
import cn.itbeien.payment.core.vo.response.BatchPayForResponse;
import cn.itbeien.payment.core.vo.response.PayForResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.mapper.trade.TradeBatchInfoMapper;
import cn.itbeien.payment.service.genseq.SeqService;
import cn.itbeien.payment.service.payfor.IPayForService;
import cn.itbeien.payment.service.risk.RiskManageService;
import cn.itbeien.payment.service.route.IRouteService;
import cn.itbeien.payment.service.route.model.RouteResultModel;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class PayForServiceImpl implements IPayForService {
	
	@Autowired
	private RedisCache redisCacheUtils;
	
	@Autowired
	private PlatPayDetailMapper platPayDetailMapper;
	
	@Autowired
	private MerchantAccRelMapper merchantAccRelMapper;
	
	@Autowired
	private MerchantChannelBalMapper merchantChannelBalMapper;
	
	@Autowired
	private TradeBatchInfoMapper tradeBatchInfoMapper;
	
	@Autowired
	private IRouteService routeServiceImpl;
	
	@Autowired
	private ICommonMapper commonMapper;
	
	@Autowired
	private FreezeDetailMapper freezeDetailMapper;
	
	@Autowired
	private SeqService seqService;
	
	@Autowired
	RiskManageService riskManageService;
	
	@Autowired
	private  RedisTemplate redisTemplate;
	
	public void payFor22(PayForRequest tradeRequest, PayForResponse tradeResponse) {
		
		RedisLock lock = new RedisLock(redisTemplate, tradeRequest.getMercNo(), 10000,20000);
		try {
			boolean flag = lock.lock();
			if(!flag){
				System.out.println("获取锁失败");
			}
			System.out.println(flag);
	    } catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	@Override
	public void payFor(PayForRequest tradeRequest, PayForResponse tradeResponse) {
		
		//1、判断商户是否可以代付
		MerchantInfo merchantInfo = (MerchantInfo)redisCacheUtils.getCacheObject(tradeRequest.getMercNo());
		
		Map<String,Object> mercNoMap = new HashMap<>();
		mercNoMap.put("mercNo", merchantInfo.getMercNo());
		if(!riskManageService.validateIpFrom(mercNoMap)){
			throw new TradeException(RespEnum.E50015.getCode(), "请求ip["+mercNoMap.get("ip")+"]"+RespEnum.E50015.getDesc());
		}
		
		if(!MercStatusEnum.APPRV_PASSD.getCode().equals(merchantInfo.getStatus())){
			throw new TradeException(RespEnum.E00042.getCode(), RespEnum.E00042.getDesc());
		}
		
		if(!(PayTypeEnum.tp1.getCode().equals(merchantInfo.getPayType())||PayTypeEnum.tp2.getCode().equals(merchantInfo.getPayType()))){
			throw new TradeException(RespEnum.E00019.getCode(), RespEnum.E00019.getDesc());
		}
		
		if(!ZeroOneEnum.one.getCode().equals(merchantInfo.getPayFlag())){
				//已开通代付 
			throw new TradeException(RespEnum.E00020.getCode(), RespEnum.E00020.getDesc());
		}
		
		//2、根据订单号判断是否重复代付订单
		Map<String,Object> reqMap = new HashMap<>();
		reqMap.put("mercNo", tradeRequest.getMercNo());
		reqMap.put("mercOrderNo", tradeRequest.getMercOrderNo());
		PlatPayDetail platPayDetailExists = platPayDetailMapper.selectByMap(reqMap);
		if(platPayDetailExists!= null){
			throw new TradeException(RespEnum.E00014.getCode(), RespEnum.E00014.getDesc());
		}
		// 查询商户批次号是否存在
		reqMap.put("mercNo", tradeRequest.getMercNo());
		reqMap.put("mercBatchNo", tradeRequest.getMercBatchNo());
		Integer count = platPayDetailMapper.selectCountByMercBatchNo(reqMap);
		if(count > 0){
			throw new TradeException(RespEnum.E00014.getCode(), RespEnum.E00014.getDesc());
		}
		
		//解析结算json大字段
		JSONObject jsonOject =  (JSONObject)JSONObject.parse(merchantInfo.getMercScaleRate());
		merchantInfo.setT0(new BigDecimal(jsonOject.getString("T0")));
		merchantInfo.setT1(new BigDecimal(jsonOject.getString("T1")));
		merchantInfo.setD0(new BigDecimal(jsonOject.getString("D0")));
		merchantInfo.setD1(new BigDecimal(jsonOject.getString("D1")));
		
		//3、检查单笔限额、代付总额是否超额：查询虚户可用余额，判断可用余额是否足够代付以及手续费
		BigDecimal tradeAmt = new BigDecimal(tradeRequest.getTradeAmt());
		log.info("交易金额为{}，单笔限额为{}", tradeAmt,merchantInfo.getSingleLimitAmt());
		if(tradeAmt.compareTo(merchantInfo.getSingleLimitAmt())>0){
			throw new TradeException(RespEnum.E00021.getCode(), RespEnum.E00021.getDesc());
		}
		
		//获取分布式锁
		RedisLock distributedLock = new RedisLock(redisTemplate, tradeRequest.getMercNo(), 50000,60000);
		try {
			boolean flag = distributedLock.lock();
			log.info("获取锁标识值为：{}，获取分布式锁{}",flag,flag?"成功":"失败");
			if(!flag){
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
			//查询虚户可用余额，判断可用余额是否足够代付以及手续费
			MerchantAccRel merchantAccRel = merchantAccRelMapper.selectByCorePayAcc(merchantInfo.getCorePayAcct());
			BigDecimal needAvalAmt  = tradeAmt.add(merchantInfo.getPayFeeValue());
			log.info("提现金额加手续费金额为{},其中手续费金额为{}", needAvalAmt,merchantInfo.getPayFeeValue());
			if(needAvalAmt.compareTo(merchantAccRel.getAcctAvaiBal())>0){
				throw new TradeException(RespEnum.E00022.getCode(), RespEnum.E00022.getDesc());
			}
			
			log.info("商户虚户{}账户总金额为:{}",merchantAccRel.getCorePayAcct(),merchantAccRel.getAcctBal()+
					",可用余额为:"+merchantAccRel.getAcctAvaiBal()+",在冻结余额为："+merchantAccRel.getFreezeBal());
			
			PlatPayDetail platPayDetail = new PlatPayDetail();
			String payId =  "P"+seqService.getOrderId();
			log.info("生成代付订单号(payId)为{}", payId);
			
			platPayDetail.setPayId(payId);
			platPayDetail.setCharset(tradeRequest.getCharset());
			platPayDetail.setVersion(tradeRequest.getVersion());
			platPayDetail.setCurrency(tradeRequest.getFeeType());
			platPayDetail.setInterfaceCode(tradeRequest.getInterfaceCode());
			platPayDetail.setLocale(tradeRequest.getLocale());
			platPayDetail.setSignType(tradeRequest.getSignType());
			
			platPayDetail.setMercNo(tradeRequest.getMercNo());
			platPayDetail.setMercOrderNo(tradeRequest.getMercOrderNo());
			platPayDetail.setToAcctNo(tradeRequest.getToAcctNo());
			platPayDetail.setToAcctName(tradeRequest.getToAcctName());
			platPayDetail.setToBankName(tradeRequest.getToBankName());
			platPayDetail.setToBankNo(tradeRequest.getToBankNo());
			platPayDetail.setAcctType(tradeRequest.getToAcctType());
			platPayDetail.setCheckFlag(ZeroOneEnum.zero.getCode());
			
			platPayDetail.setTradeType(tradeRequest.getTradeType());
			platPayDetail.setTransAmt(new BigDecimal(tradeRequest.getTradeAmt()));
			platPayDetail.setFeeValue(merchantInfo.getPayFeeValue());
			platPayDetail.setStatus(PayForStatusEnum.pf0001.getCode());
			platPayDetail.setTransUsage(tradeRequest.getRemark());
			platPayDetail.setCreateTime(new Date());
			platPayDetail.setCreator(tradeRequest.getMercNo());

			//保存通知下游地址
			platPayDetail.setService(tradeRequest.getNotifyUrl());
			//保存开户市
			platPayDetail.setRsfld4(tradeRequest.getCity());
			//保存开户省
			platPayDetail.setRsfld5(tradeRequest.getProvince());
			platPayDetail.setBankNum(tradeRequest.getBankNum());
			platPayDetail.setMobilePhone(tradeRequest.getMobilePhone());
			platPayDetail.setIdCard(tradeRequest.getIdCard());

			String batchNo = "BAT"+seqService.getBatSeq();
			log.info("payFor 生成平台代付批次号(batchNo)为{}", batchNo);
			//批次号
			platPayDetail.setPlatBatchNo(batchNo);
			platPayDetail.setMercBatchNo(tradeRequest.getMercBatchNo());

			log.info("商户开通代付标识为：{}",merchantInfo.getPayFlag());
			//4、检查是否未具有代付功能，0-无代付功能商户  1-有代付功能，联机发起代付
			if(ZeroOneEnum.zero.getCode().equals(merchantInfo.getPayFlag())
					&& TradeTypeEnum.TDTYPE04.getCode().equals(tradeRequest.getTradeType())){
				
				//0-无代付功能商户，则扣去虚户提现余额，等待人工审核再进行代付
				MerchantChannelBal   merchantChannelBal  = new MerchantChannelBal();
				merchantChannelBal.setMercNo(merchantInfo.getMercNo());
				List<MerchantChannelBalExt>  merchantChannelBalExts = merchantChannelBalMapper.selectNoPayForMerchantChannelBalExtList(merchantChannelBal);
				
				//统计需要扣减交易金额代付通道列表
				List<MerchantChannelBalExt> chooseChns = new ArrayList<MerchantChannelBalExt>();
				BigDecimal beginAmt = new BigDecimal(0);
				for(MerchantChannelBalExt temp:merchantChannelBalExts){
					beginAmt = beginAmt.add(temp.getChnAvaiBal());
					if(beginAmt.compareTo(needAvalAmt)<0){
						log.info("代付通道编号为{}，账户可用余额为{}", temp.getChannelCode(),temp.getChnAvaiBal());
						temp.setChnFeeBal(beginAmt);
						chooseChns.add(temp);
					}else{
						beginAmt = beginAmt.min(temp.getChnAvaiBal());
						BigDecimal smallChange = needAvalAmt.subtract(beginAmt);
						temp.setChnFeeBal(beginAmt);
						//表示当前对应渠道需要扣除的代付金额
						temp.setChnAvaiBal(smallChange);
						//表示当前对应渠道需要扣除的代付金额
						temp.setChnBal(smallChange);
						log.info("代付通道编号为{}，账户可用余额为{}", temp.getChannelCode(),temp.getChnAvaiBal()+",实际扣除金额为"+smallChange);
						chooseChns.add(temp);
						break;
					}
				}
				
				StringBuffer sbuffer = new StringBuffer();
			    for(MerchantChannelBalExt merchantChannelBalExt:chooseChns){
			    	sbuffer.append(merchantChannelBalExt.getPayingMercNo());
			    }
			    if(chooseChns.size()>1){
			    	platPayDetail.setPayingMercNo("ALL");
			    	platPayDetail.setPayingMercName(sbuffer.toString());
			    }else{
			    	platPayDetail.setPayingMercNo(chooseChns.get(0).getPayingMercNo());
			    	platPayDetail.setPayingMercName("");
			    }
				platPayDetail.setApprStatus(ApprStatusEnum.PENDING_APPRV.getCode());
				platPayDetail.setRsfld1(merchantInfo.getTriFlag());
				platPayDetailMapper.insert(platPayDetail);
				
				//无代付通道直接扣除交易金额
				this.mercNoChannelPayFor(tradeResponse,platPayDetail,chooseChns);
			}else if(ZeroOneEnum.one.getCode().equals(merchantInfo.getPayFlag())){
				/**
				 * 根据商户号查询商户渠道余额列表,选择代付通道
				 * 先查询代付余额满足代付金额则继续，不满足交易金额则返回商户可提现金额
				 */
				// 1-有代付功能，联机发起代付，查询商户对应各通道可用余额
				MerchantChannelBal   merchantChannelBal  = new MerchantChannelBal();
				merchantChannelBal.setMercNo(merchantInfo.getMercNo());
				List<MerchantChannelBalExt>  merchantChannelBalExts = merchantChannelBalMapper.selectMerchantChannelBalExtList(merchantChannelBal);
				log.info("生成代付订单号(payId)为{}，商户可代付渠道数为{}", payId,merchantChannelBalExts.size());
				if(!StringUtils.isNotNull(merchantChannelBalExts)){
					log.info("生成代付订单号(payId)为{}，查无符合的代付通道，商户代付失败", payId);
					throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
				}
				MerchantChannelBalExt chooseChn = merchantChannelBalExts.get(0);
				log.info("生成代付订单号(payId)为{}，{}", payId,"商户代付渠道编号:"+chooseChn.getChannelCode()+"渠道商户号为:"+chooseChn.getPayingMercNo());
				if(chooseChn.getChnAvaiBal().compareTo(needAvalAmt)<0){
					log.info("可用余额为{}", chooseChn.getChnAvaiBal());
					throw new TradeException(RespEnum.E00023.getCode(), RespEnum.E00023.getDesc()+chooseChn.getChnAvaiBal().subtract(merchantInfo.getPayFeeValue())+"元");
				}
				
				log.info("商户号:{}渠道分户账户总金额为:{}",chooseChn.getMercNo()+"渠道商户号："+chooseChn.getPayingMercNo(),chooseChn.getChnBal()+
						",可用余额为:"+chooseChn.getChnAvaiBal()+",在冻结余额为："+chooseChn.getChnFreezeBal());
				
				List<MerchantChannelBalExt> chooseChns = new ArrayList<MerchantChannelBalExt>();
				
				//获取路由参数信息
				RouteResultModel routeResultModel = routeServiceImpl.routeByCondition(chooseChn.getMercNo(), chooseChn.getChannelCode(),
						null, null);
				
				log.info("商户开通代付标识为：{},{}",merchantInfo.getTriFlag(),ZeroOneEnum.zero.getCode().equals(merchantInfo.getTriFlag())?" 0-非实时，需要审批":" 1-实时交易");
				//代付是否需要审核  0-非实时，需要审批  1-实时交易
				if(ZeroOneEnum.zero.getCode().equals(merchantInfo.getTriFlag())){
					platPayDetail.setApprStatus(ApprStatusEnum.PENDING_APPRV.getCode());
					platPayDetail.setRsfld1(merchantInfo.getTriFlag());
					platPayDetailMapper.insert(platPayDetail);
					return;
				}else if(ZeroOneEnum.one.getCode().equals(merchantInfo.getTriFlag())){
					log.info("生成代付订单号(payId)为{}，{}", payId,"交易将代付金额"+platPayDetail.getTransAmt()+"扣除手续费"+platPayDetail.getFeeValue());
					//设置需待扣减代付金额、代付手续费
					chooseChn.setChnAvaiBal(platPayDetail.getTransAmt());
					chooseChn.setChnFeeBal(platPayDetail.getFeeValue());
					chooseChn.setChnOutAmt(needAvalAmt);
				
					//统计需要扣减交易金额代付通道列表
					chooseChns.add(chooseChn);
					
					platPayDetail.setPayingMercNo(chooseChn.getPayingMercNo());
					platPayDetail.setPayingMercName(chooseChn.getPayingMercName());
					platPayDetail.setChannelCode(chooseChn.getChannelCode());
					
					platPayDetail.setApprStatus(ApprStatusEnum.APPRV_PASSD.getCode());
					platPayDetail.setRemark("接口实时审核");
					platPayDetail.setRsfld1(merchantInfo.getTriFlag());
					platPayDetail.setTradeTime(new Date());
					platPayDetail.setRsfld3(routeResultModel.getChannelType());
					platPayDetail.setCostValue(routeResultModel.getPayFeeValue());
					platPayDetailMapper.insert(platPayDetail);
				}
				//获取设置代付流水号,同时为冻结时的流水号
				String id = this.serialNumber("FREEZE_NO");
				platPayDetail.setRsfld2(id);
				log.info("代付记录保存成功,即将冻结记录ID{}",platPayDetail.getRsfld2());
				//6、调用代付接口
				this.channelPayFor(tradeResponse,platPayDetail,chooseChns,routeResultModel.getServiceBeanId());
//				tradeResponse.setRespCode("000000");
//				tradeResponse.setRespDesc("处理成功");
			}else{
				log.error("["+tradeResponse.getPayId()+"]代付发生异常,无代付功能并且非后台提现");
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
		} catch (InterruptedException e) {
			log.error("获取分布式锁失败！");
			log.error("["+tradeResponse.getPayId()+"]代付发生异常",e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} catch(TradeException e){//业务异常
			log.error("["+tradeResponse.getPayId()+"]代付发生异常:",e);
			throw e;	
		} catch (Exception e) {
			log.error("["+tradeResponse.getPayId()+"]代付发生异常:",e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} finally{
			log.error("释放分布式锁");
			distributedLock.unlock();
		}
	}
	
	
	@Override
	public void payForWithAppr(PayForRequest tradeRequest, PayForResponse tradeResponse) {
		//1、判断商户是否可以代付
		MerchantInfo merchantInfo = (MerchantInfo)redisCacheUtils.getCacheObject(tradeRequest.getMercNo());
		
		Map<String,Object> mercNoMap = new HashMap<>();
		mercNoMap.put("mercNo", merchantInfo.getMercNo());
		if(!riskManageService.validateIpFrom(mercNoMap)){
			throw new TradeException(RespEnum.E50015.getCode(), "请求ip["+mercNoMap.get("ip")+"]"+RespEnum.E50015.getDesc());
		}
		
		if(!MercStatusEnum.APPRV_PASSD.getCode().equals(merchantInfo.getStatus())){
			throw new TradeException(RespEnum.E00042.getCode(), RespEnum.E00042.getDesc());
		}
		
		if(!(PayTypeEnum.tp1.getCode().equals(merchantInfo.getPayType())||PayTypeEnum.tp2.getCode().equals(merchantInfo.getPayType()))){
			throw new TradeException(RespEnum.E00019.getCode(), RespEnum.E00019.getDesc());
		}
		
		if(!ZeroOneEnum.one.getCode().equals(merchantInfo.getPayFlag())){
			//已开通代付 
			throw new TradeException(RespEnum.E00020.getCode(), RespEnum.E00020.getDesc());
		}
		
		//2、根据订单号判断代付订单是否存在
		Map<String,Object> reqMap = new HashMap<>();
		reqMap.put("mercNo", tradeRequest.getMercNo());
		reqMap.put("mercOrderNo", tradeRequest.getMercOrderNo());
		PlatPayDetail platPayDetailExists = platPayDetailMapper.selectByMap(reqMap);
		if(platPayDetailExists== null){
			throw new TradeException(RespEnum.E00017.getCode(), RespEnum.E00017.getDesc());
		}
		
		//解析结算json大字段
		JSONObject jsonOject =  (JSONObject)JSONObject.parse(merchantInfo.getMercScaleRate());
		merchantInfo.setT0(new BigDecimal(jsonOject.getString("T0")));
		merchantInfo.setT1(new BigDecimal(jsonOject.getString("T1")));
		merchantInfo.setD0(new BigDecimal(jsonOject.getString("D0")));
		merchantInfo.setD1(new BigDecimal(jsonOject.getString("D1")));
		
		//3、检查单笔限额、代付总额是否超额：查询虚户可用余额，判断可用余额是否足够代付以及手续费
		BigDecimal tradeAmt = new BigDecimal(tradeRequest.getTradeAmt());
		log.info("交易金额为{}，单笔限额为{}", tradeAmt,merchantInfo.getSingleLimitAmt());
		if(tradeAmt.compareTo(merchantInfo.getSingleLimitAmt())>0){
			platPayDetailExists.setStatus(PayForStatusEnum.pf0004.getCode());
			platPayDetailExists.setRespCode(RespEnum.E00021.getCode());
			platPayDetailExists.setRespDesc(RespEnum.E00021.getDesc());
			platPayDetailMapper.updateByPrimaryKey(platPayDetailExists);
			throw new TradeException(RespEnum.E00021.getCode(), RespEnum.E00021.getDesc());
		}
		
		//获取分布式锁
		RedisLock distributedLock = new RedisLock(redisTemplate, tradeRequest.getMercNo(), 50000,60000);
		try {
			boolean flag = distributedLock.lock();
			log.info("获取锁标识值为：{}，获取分布式锁{}",flag,flag?"成功":"失败");
			if(!flag){
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
			//查询虚户可用余额，判断可用余额是否足够代付以及手续费
			MerchantAccRel  merchantAccRel = merchantAccRelMapper.selectByCorePayAcc(merchantInfo.getCorePayAcct());
			BigDecimal needAvalAmt  = tradeAmt.add(merchantInfo.getPayFeeValue());
			log.info("提现金额加手续费金额为{},其中手续费金额为{}", needAvalAmt,merchantInfo.getPayFeeValue());
			if(needAvalAmt.compareTo(merchantAccRel.getAcctAvaiBal())>0){
				platPayDetailExists.setStatus(PayForStatusEnum.pf0004.getCode());
				platPayDetailExists.setRespCode(RespEnum.E00022.getCode());
				platPayDetailExists.setRespDesc(RespEnum.E00022.getDesc());
				platPayDetailMapper.updateByPrimaryKey(platPayDetailExists);
				throw new TradeException(RespEnum.E00022.getCode(), RespEnum.E00022.getDesc());
			}
			log.info("商户虚户{}账户总金额为:{}",merchantAccRel.getCorePayAcct(),merchantAccRel.getAcctBal()+
					",可用余额为:"+merchantAccRel.getAcctAvaiBal()+",在冻结余额为："+merchantAccRel.getFreezeBal());

			log.info("商户开通代付标识为：{}",merchantInfo.getPayFlag());
			//4、检查是否未具有代付功能，0-无代付功能商户  1-有代付功能，联机发起代付
			if(ZeroOneEnum.one.getCode().equals(merchantInfo.getPayFlag())){
				/**
				 * 根据商户号查询商户渠道余额列表,选择代付通道
				 * 先查询代付余额满足代付金额则继续，不满足交易金额则返回商户可提现金额
				 */
				// 1-有代付功能，联机发起代付，查询商户对应各通道可用余额
				MerchantChannelBal   merchantChannelBal  = new MerchantChannelBal();
				merchantChannelBal.setMercNo(merchantInfo.getMercNo());
				List<MerchantChannelBalExt>  merchantChannelBalExts = merchantChannelBalMapper.selectMerchantChannelBalExtList(merchantChannelBal);
				log.info("生成代付订单号(payId)为{}，商户可代付渠道数为{}", platPayDetailExists.getPayId(),merchantChannelBalExts.size());
				if(!StringUtils.isNotNull(merchantChannelBalExts)){
					log.info("生成代付订单号(payId)为{}，查无符合的代付通道，商户代付失败", platPayDetailExists.getPayId());
					platPayDetailExists.setStatus(PayForStatusEnum.pf0004.getCode());
					platPayDetailExists.setRespCode(RespEnum.E99999.getCode());
					platPayDetailExists.setRespDesc(RespEnum.E99999.getDesc());
					platPayDetailMapper.updateByPrimaryKey(platPayDetailExists);
					throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
				}
				MerchantChannelBalExt chooseChn = merchantChannelBalExts.get(0);
				log.info("生成代付订单号(payId)为{}，{}", platPayDetailExists.getPayId(),"商户代付渠道编号:"+chooseChn.getChannelCode()+"渠道商户号为:"+chooseChn.getPayingMercNo());
				if(chooseChn.getChnAvaiBal().compareTo(needAvalAmt)<0){
					log.info("可用余额为{}", chooseChn.getChnAvaiBal());
					platPayDetailExists.setStatus(PayForStatusEnum.pf0004.getCode());
					platPayDetailExists.setRespCode(RespEnum.E00023.getCode());
					platPayDetailExists.setRespDesc(RespEnum.E00023.getDesc()+chooseChn.getChnAvaiBal().subtract(merchantInfo.getPayFeeValue())+"元");
					platPayDetailMapper.updateByPrimaryKey(platPayDetailExists);
					throw new TradeException(RespEnum.E00023.getCode(), RespEnum.E00023.getDesc()+chooseChn.getChnAvaiBal().multiply(merchantInfo.getPayFeeValue())+"元");
				}

				log.info("商户号:{},渠道分户账户总金额为:{}",chooseChn.getMercNo()+"渠道商户号："+chooseChn.getPayingMercNo(),chooseChn.getChnBal()+
						",可用余额为:"+chooseChn.getChnAvaiBal()+",在冻结余额为："+chooseChn.getChnFreezeBal());
				
				List<MerchantChannelBalExt> chooseChns = new ArrayList<MerchantChannelBalExt>();
				
				//获取路由参数信息
				RouteResultModel  routeResultModel = routeServiceImpl.routeByCondition(chooseChn.getMercNo(), chooseChn.getChannelCode(),
						null, null);


				log.info("生成代付订单号(payId)为{}，{}", platPayDetailExists.getPayId(),"交易将代付金额:"+platPayDetailExists.getTransAmt()+"扣除手续费:"+platPayDetailExists.getFeeValue());
				//设置需扣减代付金额、代付手续费
				chooseChn.setChnAvaiBal(platPayDetailExists.getTransAmt());
				chooseChn.setChnFeeBal(platPayDetailExists.getFeeValue());
				chooseChn.setChnOutAmt(needAvalAmt);
				
				//统计需要扣减交易金额代付通道列表
				chooseChns.add(chooseChn);
					
				platPayDetailExists.setPayingMercNo(chooseChn.getPayingMercNo());
				platPayDetailExists.setPayingMercName(chooseChn.getPayingMercName());
				platPayDetailExists.setChannelCode(chooseChn.getChannelCode());
					
				platPayDetailExists.setApprStatus(ApprStatusEnum.APPRV_PASSD.getCode());
				platPayDetailExists.setRemark("运营后台人工审核接口");
				platPayDetailExists.setTradeTime(new Date());
				platPayDetailExists.setRsfld3(routeResultModel.getChannelType());//渠道常量
				platPayDetailExists.setCostValue(routeResultModel.getPayFeeValue());
				//获取设置代付流水号,同时为冻结时的流水号
				String id = this.serialNumber("FREEZE_NO"); 
				platPayDetailExists.setRsfld2(id);
				log.info("代付记录保存成功,即将冻结记录ID{}",platPayDetailExists.getRsfld2());
				
				//6、调用代付接口
				this.channelPayFor(tradeResponse,platPayDetailExists,chooseChns,routeResultModel.getServiceBeanId());
			}else{
				log.error("["+tradeResponse.getPayId()+"]代付发生异常");
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
		} catch (InterruptedException e) {
			log.error("获取分布式锁失败:{0}",e);
			log.error("["+tradeResponse.getPayId()+"]代付发生异常:",e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} catch (Exception e) {
			log.error("["+tradeResponse.getPayId()+"]代付发生异常",e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} finally{
			log.error("释放分布式锁");
			distributedLock.unlock();
		}
	}
	
	@Override
	public void batchPayFor(BatchPayForRequest tradeRequest, BatchPayForResponse tradeResponse) {
		
		//1、判断商户是否可以代付
		MerchantInfo merchantInfo = (MerchantInfo)redisCacheUtils.getCacheObject(tradeRequest.getMercNo());
		
		Map<String,Object> mercNoMap = new HashMap<>();
		mercNoMap.put("mercNo", merchantInfo.getMercNo());
		if(!riskManageService.validateIpFrom(mercNoMap)){
			throw new TradeException(RespEnum.E50015.getCode(), "请求ip["+mercNoMap.get("ip")+"]"+RespEnum.E50015.getDesc());
		}
		
		//检查商户状态
		if(!MercStatusEnum.APPRV_PASSD.getCode().equals(merchantInfo.getStatus())){
			throw new TradeException(RespEnum.E00042.getCode(), RespEnum.E00042.getDesc());
		}
		
		if(!(PayTypeEnum.tp1.getCode().equals(merchantInfo.getPayType())||PayTypeEnum.tp2.getCode().equals(merchantInfo.getPayType()))){
			throw new TradeException(RespEnum.E00019.getCode(), RespEnum.E00019.getDesc());
		}
		
		//检查是否未具有代付功能，0-无代付功能商户  1-有代付功能，联机发起代付
		if(!ZeroOneEnum.one.getCode().equals(merchantInfo.getPayFlag())){
			//已开通代付 
			throw new TradeException(RespEnum.E00020.getCode(), RespEnum.E00020.getDesc());
		}
		
		//2、根据商户批次号判断批量代付是否存在
		Map<String,Object> reqMap = new HashMap<>();
		reqMap.put("mercNo", tradeRequest.getMercNo());
		reqMap.put("mercBatchNo", tradeRequest.getMercBatchNo());
		TradeBatchInfo tradeBatchInfo = tradeBatchInfoMapper.selectByMap(reqMap);
		if(tradeBatchInfo != null){
			throw new TradeException(RespEnum.E00014.getCode(), RespEnum.E00014.getDesc());
		}
		
//		String batchNo = "S"+seqService.getBatSeq();
		String batchNo = "F"+new SimpleDateFormat("yyyyMMdd").format(new Date()) + DateUtils.getRandom(7);
		log.info("生成平台代付批次号(batchNo)为{}", batchNo);
		
		//3、解析代付明细信息，判断批次总金额、总笔数笔数和代付明细金额汇总、笔数汇总是否一致
		String[] dtlStrList = tradeRequest.getDetailData().split("\\&");
		
		if(dtlStrList.length!=Integer.parseInt(tradeRequest.getBatchCnt())){
			throw new TradeException(RespEnum.E00070.getCode(), RespEnum.E00070.getDesc());
		}
		
		Date currentDate = new Date();
		List<PlatPayDetail> platPayDtlList = new ArrayList<PlatPayDetail>();
		List<FreezeDetail> freezeDtlList = new ArrayList<FreezeDetail>();
		BigDecimal initAmt = new BigDecimal(0);
		BigDecimal needAvalAmt = new BigDecimal(0);
		List<String> mercNoList = new ArrayList<String>();
		//判断笔数是否一致
		for(String str:dtlStrList){
			String[] payForInfo = str.split("\\^");

			PlatPayDetail platPayDetail = new PlatPayDetail();
//			String payId =  "P"+seqService.getOrderId();
			//  upt
			//随机数
			String payId =  "S"+new SimpleDateFormat("yyyyMMdd").format(new Date()) + DateUtils.getRandom(7);
			log.info("生成代付订单号(payId)为{}", payId);
			
			platPayDetail.setPayId(payId);
			platPayDetail.setCharset(tradeRequest.getCharset());
			platPayDetail.setVersion(tradeRequest.getVersion());
			platPayDetail.setInterfaceCode(tradeRequest.getInterfaceCode());
			platPayDetail.setLocale(tradeRequest.getLocale());
			platPayDetail.setSignType(tradeRequest.getSignType());
			
			platPayDetail.setMercNo(tradeRequest.getMercNo());
//			platPayDetail.setPayId(seqService.getOrderId());
			platPayDetail.setMercOrderNo(String.valueOf(payForInfo[0]));
//			platPayDetail.setTradeType(String.valueOf(payForInfo[1]));
			platPayDetail.setTradeType(tradeRequest.getTradeType());
			
			BigDecimal  transAmt = new BigDecimal(payForInfo[1]);
			initAmt = initAmt.add(transAmt);
			needAvalAmt = needAvalAmt.add(transAmt);
			needAvalAmt = needAvalAmt.add(merchantInfo.getPayFeeValue());
			
			platPayDetail.setTransAmt(transAmt);
			platPayDetail.setFeeValue(merchantInfo.getPayFeeValue());
			platPayDetail.setTransUsage(payForInfo[2]);
			platPayDetail.setTradeTime(DateUtils.parseTime(payForInfo[3]));
			platPayDetail.setCurrency(String.valueOf(payForInfo[4]));
			platPayDetail.setToAcctNo(String.valueOf(payForInfo[5]));
			platPayDetail.setToAcctName(String.valueOf(payForInfo[6]));
			platPayDetail.setAcctType(String.valueOf(payForInfo[7]));
			platPayDetail.setToBankNo(String.valueOf(payForInfo[8]));
			platPayDetail.setToBankName(String.valueOf(payForInfo[9]));
			platPayDetail.setRsfld4(String.valueOf(payForInfo[10]));
			platPayDetail.setRsfld5(String.valueOf(payForInfo[11]));

			if(payForInfo.length == 15) {
				platPayDetail.setMobilePhone(String.valueOf(payForInfo[12]));
				platPayDetail.setIdCard(String.valueOf(payForInfo[13]));
				platPayDetail.setBankNum(String.valueOf(payForInfo[14]));
			}

			platPayDetail.setService(tradeRequest.getNotifyUrl()); //通知url
			platPayDetail.setCreateTime(currentDate);
			platPayDetail.setCreator(tradeRequest.getMercNo());
			platPayDetail.setStatus(PayForStatusEnum.pf0001.getCode());
			
			platPayDetail.setMercBatchNo(tradeRequest.getMercBatchNo());
			platPayDetail.setPlatBatchNo(batchNo);//批次号
			platPayDtlList.add(platPayDetail);

			mercNoList.add(String.valueOf(payForInfo[0]));
		}

		log.info("明细汇总金额："+initAmt+"需要账户可用金额（含手续费）："+needAvalAmt);
		
		//校验总金额是否匹配
		if(initAmt.compareTo(new BigDecimal(tradeRequest.getBatchAmt()))!=0){
			throw new TradeException(RespEnum.E00071.getCode(), RespEnum.E00071.getDesc());
		}
		// 查询一批中的订单是否重复订单
		Map<String, Object> paramMap = new HashMap<String, Object>(2);
		paramMap.put("mercNo", tradeRequest.getMercNo());
		paramMap.put("list", mercNoList);
		Integer i = platPayDetailMapper.selectCountByMercNo(paramMap);
		if(i > 0) {
			throw new TradeException(RespEnum.E00014.getCode(), RespEnum.E00014.getDesc());
		}
		
		TradeBatchInfo tradeBatchInfoInsert = new TradeBatchInfo();
		tradeBatchInfoInsert.setPlatBatchNo(batchNo);
		tradeBatchInfoInsert.setMercBatchNo(tradeRequest.getMercBatchNo());
		tradeBatchInfoInsert.setCreateTime(new Date());
		tradeBatchInfoInsert.setCreator(tradeRequest.getMercNo());
		tradeBatchInfoInsert.setBatchAmt(new BigDecimal(tradeRequest.getBatchAmt()));
		tradeBatchInfoInsert.setBatchCnt(Integer.parseInt(tradeRequest.getBatchCnt()));
		tradeBatchInfoInsert.setInterfaceCode(tradeRequest.getInterfaceCode());
		tradeBatchInfoInsert.setMercNo(tradeRequest.getMercNo());
		tradeBatchInfoInsert.setNotifyUrl(tradeRequest.getNotifyUrl());
		tradeBatchInfoInsert.setTradeType(tradeRequest.getTradeType());
		tradeBatchInfoInsert.setBatchFeeAmt(needAvalAmt.subtract(tradeBatchInfoInsert.getBatchAmt()));
		tradeBatchInfoInsert.setBatchStatus(BatchStatusEnum.b0001.getCode());
//		RxpayLogger.info("商户开通代付标识为：{}",merchantInfo.getPayFlag());
		
//		//4、检查是否未具有代付功能，0-无代付功能商户  1-有代付功能，联机发起代付
//		if(ZeroOneEnum.zero.getCode().equals(merchantInfo.getPayFlag())){
//			
//			throw new TradeException("代付参数未配置");
//		}else if(ZeroOneEnum.one.getCode().equals(merchantInfo.getPayFlag())){
		/**
		 * 根据商户号查询商户渠道余额列表,选择代付通道
		 * 先查询代付余额满足代付金额则继续，不满足交易金额则返回商户可提现金额
		 */
		// 1-有代付功能，联机发起代付，查询商户对应各通道可用余额

		log.info("商户开通代付标识为：{},{}",merchantInfo.getTriFlag(),ZeroOneEnum.zero.getCode().equals(merchantInfo.getTriFlag())?" 0-非实时，需要审批":" 1-实时交易");
		
		//代付是否需要审核  0-非实时，需要审批  1-实时交易
		if(ZeroOneEnum.zero.getCode().equals(merchantInfo.getTriFlag())){
			for(PlatPayDetail tempPlatPayDetail:platPayDtlList){
				tempPlatPayDetail.setApprStatus(ApprStatusEnum.PENDING_APPRV.getCode());
				tempPlatPayDetail.setRsfld1(merchantInfo.getTriFlag());
			}
			platPayDetailMapper.batchInsert(platPayDtlList);
			return;
		}else if(ZeroOneEnum.one.getCode().equals(merchantInfo.getTriFlag())){
			//查询虚户可用余额，判断可用余额是否足够代付以及手续费
			MerchantAccRel  merchantAccRel = merchantAccRelMapper.selectByCorePayAcc(merchantInfo.getCorePayAcct());
			log.info("提现金额加手续费金额为{},其中手续费金额为{}", needAvalAmt,merchantInfo.getPayFeeValue());
			if(needAvalAmt.compareTo(merchantAccRel.getAcctAvaiBal())>0){
				throw new TradeException(RespEnum.E00022.getCode(), RespEnum.E00022.getDesc());
			}

			log.info("商户虚户{}账户总金额为:{}",merchantAccRel.getCorePayAcct(),merchantAccRel.getAcctBal()+
					",可用余额为:"+merchantAccRel.getAcctAvaiBal()+",在冻结余额为："+merchantAccRel.getFreezeBal());
			
			MerchantChannelBal   merchantChannelBal  = new MerchantChannelBal();
			merchantChannelBal.setMercNo(merchantInfo.getMercNo());
			List<MerchantChannelBalExt>  merchantChannelBalExts = merchantChannelBalMapper.selectMerchantChannelBalExtList(merchantChannelBal);
			log.info("生成平台代付批次号(batchNo)为{}，商户可代付渠道数为{}", batchNo,merchantChannelBalExts.size());
			if(!StringUtils.isNotNull(merchantChannelBalExts)){
				log.info("生成平台代付批次号(batchNo)为{}，查无符合的代付通道，商户代付失败", batchNo);
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
			MerchantChannelBalExt chooseChn = merchantChannelBalExts.get(0);
			log.info("生成平台代付批次号(batchNo)为{}，{}", batchNo,"商户代付渠道编号:"+chooseChn.getChannelCode()+"渠道商户号为:"+chooseChn.getPayingMercNo());
			if(chooseChn.getChnAvaiBal().compareTo(needAvalAmt)<0){
				log.info("可用余额为{}", chooseChn.getChnAvaiBal());
				throw new TradeException(RespEnum.E00023.getCode(), RespEnum.E00023.getDesc()+chooseChn.getChnAvaiBal().subtract(merchantInfo.getPayFeeValue())+"元");
			}

			log.info("商户号:{}渠道分户账户总金额为:{}",chooseChn.getMercNo()+"渠道商户号："+chooseChn.getPayingMercNo(),chooseChn.getChnBal()+
					",可用余额为:"+chooseChn.getChnAvaiBal()+",在冻结余额为："+chooseChn.getChnFreezeBal());
			
			List<MerchantChannelBalExt> chooseChns = new ArrayList<MerchantChannelBalExt>();
			
			//获取路由参数信息
			RouteResultModel  routeResultModel = routeServiceImpl.routeByCondition(chooseChn.getMercNo(), chooseChn.getChannelCode(),
					null, null);
			log.info("生成平台代付批次号(batchNo)为{}，{}", batchNo,"交易将代付总金额"+initAmt+"扣除总手续费"+needAvalAmt.subtract(initAmt));
			
			
			//设置需待扣减代付金额、代付手续费
			chooseChn.setChnAvaiBal(initAmt);
			chooseChn.setChnFeeBal(needAvalAmt.subtract(initAmt));
			chooseChn.setChnOutAmt(needAvalAmt);
		
			//统计需要扣减交易金额代付通道列表
			chooseChns.add(chooseChn);
			
			for(PlatPayDetail tempPlatPayDetail:platPayDtlList){
				tempPlatPayDetail.setPayingMercNo(chooseChn.getPayingMercNo());
				tempPlatPayDetail.setPayingMercName(chooseChn.getPayingMercName());
				tempPlatPayDetail.setChannelCode(chooseChn.getChannelCode());
				
				tempPlatPayDetail.setApprStatus(ApprStatusEnum.APPRV_PASSD.getCode());
				tempPlatPayDetail.setRemark("接口实时审核");
				tempPlatPayDetail.setRsfld1(merchantInfo.getTriFlag());
				tempPlatPayDetail.setTradeTime(new Date());
				tempPlatPayDetail.setRsfld3(routeResultModel.getChannelType());
				tempPlatPayDetail.setCostValue(routeResultModel.getPayFeeValue());
				
				//获取设置代付流水号,同时为冻结时的流水号
				String id = this.serialNumber("FREEZE_NO");
				tempPlatPayDetail.setRsfld2(id);
				log.info("生成平台代付批次号(batchNo)为{}，代付记录保存成功,即将冻结记录ID{}",batchNo,tempPlatPayDetail.getRsfld2());
			
				//添加商户冻结记录
				FreezeDetail freezeDetail = new FreezeDetail();
				freezeDetail.setId(tempPlatPayDetail.getRsfld2());//将设置的代付流水号作为冻结ID
				freezeDetail.setOrderId(tempPlatPayDetail.getPayId());
				freezeDetail.setMercNo(tempPlatPayDetail.getMercNo());
				freezeDetail.setMercOrderNo(tempPlatPayDetail.getMercOrderNo());
				freezeDetail.setTradeType(tempPlatPayDetail.getTradeType());
				freezeDetail.setOrderAmount(tempPlatPayDetail.getTransAmt());//代付金额
				freezeDetail.setAvaiAmount(new BigDecimal(0));//代付时，可用金额设置为0即可
				freezeDetail.setRemark(tempPlatPayDetail.getTransUsage());//支付备注
				freezeDetail.setFeeAmount(tempPlatPayDetail.getFeeValue());//手续费金额
				freezeDetail.setFreezeAmt(tempPlatPayDetail.getTransAmt().add(tempPlatPayDetail.getFeeValue()));//冻结金额
				freezeDetail.setFreezeTime(new Date());//冻结时间 当前日期
				freezeDetail.setChannelCode(tempPlatPayDetail.getChannelCode());
				freezeDetail.setPayingMercNo(tempPlatPayDetail.getPayingMercNo());
				freezeDetail.setJsType("05");//结算类型 d0-D0结算  d1-D1结算  t0-T0结算 t1-T1结算  05-冻结
				freezeDetail.setUnfreezeTime(DateUtils.parseDate("9999-12-31"));//日期设置为“9999-12-31”，表示不限解冻日期
				freezeDetail.setPaywayCode("");
				freezeDetail.setSceneCode("");
				freezeDetail.setFeeValue(tempPlatPayDetail.getFeeValue());//回佣值 代付时与手续值相同
				freezeDetail.setFreezeStatus("00");//00冻结 01解冻
				freezeDtlList.add(freezeDetail);
			}
			tradeBatchInfoInsert.setBatchStatus(BatchStatusEnum.b0001.getCode());
			tradeBatchInfoMapper.insert(tradeBatchInfoInsert);
			log.info("生成平台代付批次号(batchNo)为{}，批量批次信息保存成功",batchNo);
			
			platPayDetailMapper.batchInsert(platPayDtlList);
			log.info("生成平台代付批次号(batchNo)为{}，批量代付明细记录保存成功",batchNo);
			
			//6、调用批量代付接口
			this.channelBatchPayFor(tradeResponse,tradeBatchInfoInsert,platPayDtlList,chooseChns,routeResultModel.getServiceBeanId(),freezeDtlList);
		}
	}
	
	/**
	 * 调用渠道代付
	 * @param tradeResponse
	 * @param chooseChns
	 * @param serviceBeanId
	 */
	private void channelBatchPayFor(BatchPayForResponse tradeResponse,TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList
			,List<MerchantChannelBalExt> chooseChns,String serviceBeanId,List<FreezeDetail> freezeDtlList) {
		
		try{
			this.freezeAvailBalWithBatch(tradeBatchInfo,platPayDetailList, chooseChns,freezeDtlList);
			//6、发起代付请求
			//step2根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doBatchPayFor", new Class[] {List.class});

			//step3调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnBatchPayForResponse chnBatchPayForResponse =(ChnBatchPayForResponse) method.invoke(clazz, new Object[] {platPayDetailList});//同步返回预支付结果
			
			this.handlePayForResultWithBatch(tradeResponse, platPayDetailList, chooseChns,chnBatchPayForResponse.getBankPayForTradeSeq()
					,chnBatchPayForResponse.getCompleteTime(),chnBatchPayForResponse,tradeBatchInfo,freezeDtlList);
		}catch(TradeException e){
			log.error("["+tradeBatchInfo.getPlatBatchNo()+"]代付发生异常",e);
			this.unfreezeAvailBalWithBatch(tradeBatchInfo,platPayDetailList, chooseChns,freezeDtlList);
			this.payForExceptionHandleWithBatch(tradeResponse,tradeBatchInfo,platPayDetailList,e.getErrorCode(),e.getErrorMsg());
			return;
		}catch(Exception e){
			log.error("["+tradeBatchInfo.getPlatBatchNo()+"]代付发生异常",e);
			this.unfreezeAvailBalWithBatch(tradeBatchInfo,platPayDetailList, chooseChns,freezeDtlList);
			this.payForExceptionHandleWithBatch(tradeResponse,tradeBatchInfo, platPayDetailList,RespEnum.E99999.getCode(),RespEnum.E99999.getDesc());
			return;
		}
	}


	/**
	 * 调用渠道代付
	 * @param payForResponse
	 * @param platPayDetail
	 * @param chooseChns
	 */
	@Transactional
	protected void mercNoChannelPayFor(PayForResponse payForResponse,PlatPayDetail platPayDetail,List<MerchantChannelBalExt> chooseChns) {
		Date currentDate = new Date();
		//从虚户中扣减交易金额
		MerchantAccRel tradeMerchantAccRel = new MerchantAccRel();
		tradeMerchantAccRel.setMercNo(platPayDetail.getMercNo());
		tradeMerchantAccRel.setCoreAcctType("00");
		tradeMerchantAccRel.setAcctBal(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setAcctAvaiBal(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setFeeBal(platPayDetail.getFeeValue());
		tradeMerchantAccRel.setOutAmt(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setUpdateTime(new Date());
		tradeMerchantAccRel.setUpdater(platPayDetail.getMercNo());
		merchantAccRelMapper.updateAccBalByCondition(tradeMerchantAccRel);
		
		//批量扣减对应渠道道中应分摊的交易金额 chooseChns
		merchantChannelBalMapper.batchUpdateAvaiBal(chooseChns);
		
		//更新提现状态
		platPayDetail.setStatus(PayForStatusEnum.pf0000.getCode());
		platPayDetail.setRetCode(RespEnum.S00000.getCode());
		platPayDetail.setRetDesc(RespEnum.S00000.getDesc());
		platPayDetail.setRespCode(RespEnum.S00000.getCode());
		platPayDetail.setRespDesc(RespEnum.S00000.getDesc());
		platPayDetail.setBankTradeSeq(UUID.getUUID());
		platPayDetail.setSettleTime(currentDate);
		platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		
		//设置返回值
		payForResponse.setPayId(platPayDetail.getPayId());
		payForResponse.setStatus(PayForStatusEnum.pf0000.getCode());
		payForResponse.setRespCode(RespEnum.S00000.getCode());
		payForResponse.setRespDesc(RespEnum.S00000.getDesc());
		payForResponse.setTradeTime(DateUtils.formatTime(currentDate));
	}
	
	
	/**
	 * 调用渠道代付
	 * @param tradeResponse
	 * @param platPayDetail
	 * @param chooseChns
	 * @param serviceBeanId
	 */
	private void channelPayFor(PayForResponse tradeResponse,PlatPayDetail platPayDetail,List<MerchantChannelBalExt> chooseChns,String serviceBeanId) {
		//冻结虚户余额
		FreezeDetail freezeDetail = null;
		try{
			freezeDetail = this.freezeAvailBal(platPayDetail, chooseChns);
			//6、发起代付请求
			//step2根据支付渠道表支付渠道编号获取支付通道对应处理类BEANID路由到具体支付通道业务类
			Object clazz  = SpringUtils.getBean(serviceBeanId);//根据beanid获取支付渠道类
			
			Method method = clazz.getClass().getMethod("doPayFor", new Class[] {PlatPayDetail.class});

			//step3调用支付通道支付接口组装请求参数，发起支付请求。解析返回的支付参数，设置到支付结果通用实体类。
			ChnPayForResponse chnPayForResponse =(ChnPayForResponse) method.invoke(clazz, new Object[] {platPayDetail});//同步返回预支付结果
			
			this.handlePayForResult(tradeResponse, platPayDetail, chooseChns,chnPayForResponse.getBankPayForTradeSeq()
					,chnPayForResponse.getCompleteTime(),chnPayForResponse,freezeDetail);
		}catch(TradeException e){
			log.error("["+platPayDetail.getPayId()+"]代付发生异常",e);
			this.unfreezeAvailBal(platPayDetail, chooseChns,freezeDetail);
			this.payForExceptionHandle(tradeResponse,platPayDetail,e.getErrorCode(),e.getErrorMsg());
			return;
		}catch(Exception e){
			log.error("["+tradeResponse.getPayId()+"]代付发生异常",e);
			this.unfreezeAvailBal(platPayDetail, chooseChns,freezeDetail);
			this.payForExceptionHandle(tradeResponse, platPayDetail,RespEnum.E99999.getCode(),RespEnum.E99999.getDesc());
			return;
		}
	}


	/**
	 * 冻结虚户与对应渠道余额
	 * @param platPayDetail
	 * @param chooseChns
	 */
	@Transactional
	protected FreezeDetail freezeAvailBal(PlatPayDetail platPayDetail, List<MerchantChannelBalExt> chooseChns) {
		
		log.info("代付流水号{}，进行代付资金冻结",platPayDetail.getPayId());
		
		//从虚户中扣减交易金额
		MerchantAccRel tradeMerchantAccRel = new MerchantAccRel();
		tradeMerchantAccRel.setMercNo(platPayDetail.getMercNo());
		tradeMerchantAccRel.setCoreAcctType("00");
		tradeMerchantAccRel.setAcctAvaiBal(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setFreezeBal(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setUpdateTime(new Date());
		tradeMerchantAccRel.setUpdater(platPayDetail.getMercNo());
		merchantAccRelMapper.freezeAccAvailBalByCondition(tradeMerchantAccRel);
		log.info("代付流水号{}，{}",platPayDetail.getPayId(),tradeMerchantAccRel.getMercNo()+"商户虚户可用余额中减少："+tradeMerchantAccRel.getAcctAvaiBal()+"，冻结余额中增加："+tradeMerchantAccRel.getFreezeBal());
		
		//批量扣减对应渠道中应分摊的交易金额 chooseChns
		merchantChannelBalMapper.batchFreezeAvaiBal(chooseChns);
		log.info("代付流水号{}，{}",platPayDetail.getPayId(),chooseChns.get(0).getMercNo()+":"+chooseChns.get(0).getPayingMercNo()
				+"商户分户可用余额中减少："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal())+"，冻结余额中增加："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal()));
		
		//添加商户冻结记录
		FreezeDetail freezeDetail = new FreezeDetail();
		freezeDetail.setId(platPayDetail.getRsfld2());//将设置的代付流水号作为冻结ID
		freezeDetail.setOrderId(platPayDetail.getPayId());
		freezeDetail.setMercNo(platPayDetail.getMercNo());
		freezeDetail.setMercOrderNo(platPayDetail.getMercOrderNo());
		freezeDetail.setTradeType(platPayDetail.getTradeType());
		freezeDetail.setOrderAmount(platPayDetail.getTransAmt());//代付金额
		freezeDetail.setAvaiAmount(new BigDecimal(0));//代付时，可用金额设置为0即可
		freezeDetail.setRemark(platPayDetail.getTransUsage());//支付备注
		freezeDetail.setFeeAmount(platPayDetail.getFeeValue());//手续费金额
		freezeDetail.setFreezeAmt(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));//冻结金额
		freezeDetail.setFreezeTime(new Date());//冻结时间 当前日期
		freezeDetail.setChannelCode(platPayDetail.getChannelCode());
		freezeDetail.setPayingMercNo(platPayDetail.getPayingMercNo());
		freezeDetail.setJsType("05");//结算类型 d0-D0结算  d1-D1结算  t0-T0结算 t1-T1结算  05-冻结
		freezeDetail.setUnfreezeTime(DateUtils.parseDate("9999-12-31"));//日期设置为“9999-12-31”，表示不限解冻日期
		freezeDetail.setPaywayCode("");
		freezeDetail.setSceneCode("");
		freezeDetail.setFeeValue(platPayDetail.getFeeValue());//回佣值 代付时与手续值相同
		freezeDetail.setFreezeStatus("00");//00冻结 01解冻
		freezeDetailMapper.insert(freezeDetail);
		log.info("代付流水号{}，冻结成功，流水记录为：{}",platPayDetail.getPayId(),platPayDetail.getRsfld2());
		return freezeDetail;
	}
	
	/**
	 * 解冻虚户与对应渠道余额
	 * @param platPayDetail
	 * @param chooseChns
	 */
	@Transactional
	protected void unfreezeAvailBal(PlatPayDetail platPayDetail, List<MerchantChannelBalExt> chooseChns,FreezeDetail freezeDetail) {

		log.info("代付流水号{}，进行代付资金解冻",platPayDetail.getPayId());
		//从虚户中扣减交易金额
		MerchantAccRel tradeMerchantAccRel = new MerchantAccRel();
		tradeMerchantAccRel.setMercNo(platPayDetail.getMercNo());
		tradeMerchantAccRel.setCoreAcctType("00");
		tradeMerchantAccRel.setAcctAvaiBal(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setFreezeBal(platPayDetail.getTransAmt().add(platPayDetail.getFeeValue()));
		tradeMerchantAccRel.setUpdateTime(new Date());
		tradeMerchantAccRel.setUpdater(platPayDetail.getMercNo());
		merchantAccRelMapper.unfreezeAccAvailBalByCondition(tradeMerchantAccRel);
		log.info("代付流水号{}，{}",platPayDetail.getPayId(),tradeMerchantAccRel.getMercNo()+"商户虚户可用余额中增加："+tradeMerchantAccRel.getAcctAvaiBal()+"，冻结余额中减少："+tradeMerchantAccRel.getFreezeBal());
		
		//批量扣减对应渠道中应分摊的交易金额 chooseChns
		merchantChannelBalMapper.batchUnfreezeAvaiBal(chooseChns);
		log.info("代付流水号{}，{}",platPayDetail.getPayId(),chooseChns.get(0).getMercNo()+":"+chooseChns.get(0).getPayingMercNo()
				+"商户分户可用余额中增加："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal())+"，冻结余额中减少："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal()));
		
		//改为解冻状态
		FreezeDetail freezeDetail2 = new FreezeDetail();
		freezeDetail2.setId(freezeDetail.getId());
		freezeDetail2.setFreezeStatus("01"); //00-冻结   01-解冻
		freezeDetailMapper.updateByPrimaryKeySelective(freezeDetail2);
		log.info("代付流水号{}，解冻成功，流水记录为：{}",platPayDetail.getPayId(),platPayDetail.getRsfld2());
	}
	
	
	
	/**
	 * 获取订单表序列号
	 * @param nextVal 需要生成序列号的名称
	 * 方法用途: <br>
	 * @return
	 */
	private String serialNumber(String nextVal){
		String batchNo = commonMapper.getSeqNextvalByName(nextVal);
		//String dbdate = commonDao.getMysqlDbDate();
		String dbdate = DateUtils.formatTime(new Date());
		if (batchNo.length() < 8) {// 如果序列号不够8位则填充0
			int i = (8 - batchNo.length());
			String prefix = "";
			for (int j = 0; j < i; j++) {
				prefix += "0";
			}
			batchNo = prefix + batchNo;// 以0填充后的序列号
		}
		return dbdate+batchNo;
	}
	
	
	/**********批量代付处理开始***************/
	
	/**
	 * 批量冻结虚户与对应渠道余额
	 * @param tradeBatchInfo
	 * @param platPayDetailList
	 * @param chooseChns
	 * @param freezeDtlList
	 */
	@Transactional
	protected void freezeAvailBalWithBatch(TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList, List<MerchantChannelBalExt> chooseChns
			,List<FreezeDetail> freezeDtlList) {

		log.info("商户号{}，批量代付批次号{}，进行代付资金冻结",tradeBatchInfo.getMercNo(),tradeBatchInfo.getPlatBatchNo());
		
		//从虚户中扣减交易金额
		MerchantAccRel tradeMerchantAccRel = new MerchantAccRel();
		tradeMerchantAccRel.setMercNo(tradeBatchInfo.getMercNo());
		tradeMerchantAccRel.setCoreAcctType("00");
		tradeMerchantAccRel.setAcctAvaiBal(tradeBatchInfo.getBatchAmt().add(tradeBatchInfo.getBatchFeeAmt()));
		tradeMerchantAccRel.setFreezeBal(tradeBatchInfo.getBatchAmt().add(tradeBatchInfo.getBatchFeeAmt()));
		tradeMerchantAccRel.setUpdateTime(new Date());
		tradeMerchantAccRel.setUpdater(tradeBatchInfo.getMercNo());
		merchantAccRelMapper.freezeAccAvailBalByCondition(tradeMerchantAccRel);
		log.info("批量代付批次号{}，{}",tradeBatchInfo.getPlatBatchNo(),tradeMerchantAccRel.getMercNo()+"商户虚户可用余额中减少："+tradeMerchantAccRel.getAcctAvaiBal()+"，冻结余额中增加："+tradeMerchantAccRel.getFreezeBal());
		
		//批量扣减对应渠道中应分摊的交易金额 chooseChns
		merchantChannelBalMapper.batchFreezeAvaiBal(chooseChns);
		log.info("批量代付批次号{}，{}",tradeBatchInfo.getPlatBatchNo(),chooseChns.get(0).getMercNo()+":"+chooseChns.get(0).getPayingMercNo()
				+"商户分户可用余额中减少："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal())+"，冻结余额中增加："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal()));
		
		//添加商户批量冻结记录
		freezeDetailMapper.batchInsert(freezeDtlList);
	}
	
	/**
	 * 批量解冻虚户与对应渠道余额
	 * @param tradeBatchInfo
	 * @param platPayDetailList
	 * @param chooseChns
	 * @param freezeDtlList
	 */
	@Transactional
	protected void unfreezeAvailBalWithBatch(TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList, List<MerchantChannelBalExt> chooseChns
			,List<FreezeDetail> freezeDtlList) {

		log.info("商户号{}，批量代付批次号{}，进行代付资金冻结",tradeBatchInfo.getMercNo(),tradeBatchInfo.getPlatBatchNo());
		
		//从虚户中扣减交易金额
		MerchantAccRel tradeMerchantAccRel = new MerchantAccRel();
		tradeMerchantAccRel.setMercNo(tradeBatchInfo.getMercNo());
		tradeMerchantAccRel.setCoreAcctType("00");
		tradeMerchantAccRel.setAcctAvaiBal(tradeBatchInfo.getBatchAmt().add(tradeBatchInfo.getBatchFeeAmt()));
		tradeMerchantAccRel.setFreezeBal(tradeBatchInfo.getBatchAmt().add(tradeBatchInfo.getBatchFeeAmt()));
		tradeMerchantAccRel.setUpdateTime(new Date());
		tradeMerchantAccRel.setUpdater(tradeBatchInfo.getMercNo());
		merchantAccRelMapper.unfreezeAccAvailBalByCondition(tradeMerchantAccRel);
		log.info("批量代付批次号{}，{}",tradeBatchInfo.getPlatBatchNo(),tradeMerchantAccRel.getMercNo()+"商户虚户可用余额中增加："+tradeMerchantAccRel.getAcctAvaiBal()+"，冻结余额中减少："+tradeMerchantAccRel.getFreezeBal());
		
		//批量扣减对应渠道中应分摊的交易金额 chooseChns
		merchantChannelBalMapper.batchUnfreezeAvaiBal(chooseChns);
		log.info("批量代付批次号{}，{}",tradeBatchInfo.getPlatBatchNo(),chooseChns.get(0).getMercNo()+":"+chooseChns.get(0).getPayingMercNo()
				+"商户分户可用余额中增加："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal())+"，冻结余额中减少："+chooseChns.get(0).getChnAvaiBal().add(chooseChns.get(0).getChnFeeBal()));
		
		//改为解冻状态
		for(FreezeDetail tempFreezeDetail:freezeDtlList){
			FreezeDetail freezeDetail2 = new FreezeDetail();
			freezeDetail2.setId(tempFreezeDetail.getId());
			freezeDetail2.setFreezeStatus("01"); //00-冻结   01-解冻
			freezeDetailMapper.updateByPrimaryKeySelective(freezeDetail2);
		}
		log.info("批量代付批次号{}，解冻成功",tradeBatchInfo.getPlatBatchNo());
	}
	
	/**
	 * 代付结果查询
	 * @param bankResponse
	 */
	private <T extends ChannelVo>  void handlePayForResultWithBatch(BatchPayForResponse tradeResponse,List<PlatPayDetail> platPayDetailList,
			List<MerchantChannelBalExt> chooseChns,String bankPayForTradeSeq,
			Date completeTime,T bankResponse,
			TradeBatchInfo tradeBatchInfo,List<FreezeDetail> freezeDetailList) {

		log.info("上游通道返回代付状态为：{},返回信息为{}",bankResponse.getRespCode(),bankResponse.getRespDesc());
		if(bankResponse.getRespCode().equals(RespEnum.S00000.getCode())){//成功
			this.successHandleWithBatch(tradeResponse,tradeBatchInfo,platPayDetailList,chooseChns,bankPayForTradeSeq,completeTime,bankResponse);
		}else{//失敗
			//失败时，解冻冻结金额
			this.unfreezeAvailBalWithBatch(tradeBatchInfo,platPayDetailList, chooseChns,freezeDetailList);
			this.failHandleWithBatch(tradeResponse,tradeBatchInfo,platPayDetailList,bankPayForTradeSeq,completeTime,bankResponse);
		}
	}


	/**
	 * 批量代付失败结果处理--事务控制，异常回滚
	 * @param batchPayForResponse
	 * @param platPayDetailList
	 * @param bankPayForTradeSeq
	 * @param completeTime
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void failHandleWithBatch(BatchPayForResponse batchPayForResponse,TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList
			,String bankPayForTradeSeq,Date completeTime,T bankResponse) {
		//更新批次表信息状态
		if(RespEnum.E00002.getCode().equals(bankResponse.getRespCode())){
			tradeBatchInfo.setBatchStatus(BatchStatusEnum.b0003.getCode());
			batchPayForResponse.setBatchStatus(PayForStatusEnum.pf0003.getCode());
		}else{
			tradeBatchInfo.setBatchStatus(BatchStatusEnum.b0004.getCode());
			batchPayForResponse.setBatchStatus(PayForStatusEnum.pf0004.getCode());
		}
		
		tradeBatchInfo.setSuccessBatchAmt(new BigDecimal(0));
		tradeBatchInfo.setSuccessBatchCnt(0);
		tradeBatchInfo.setFailBatchAmt(tradeBatchInfo.getBatchAmt());
		tradeBatchInfo.setFailBatchCnt(tradeBatchInfo.getBatchCnt());
		tradeBatchInfo.setRespCode(bankResponse.getRespCode());
		tradeBatchInfo.setRespDesc(bankResponse.getRespDesc());
		tradeBatchInfoMapper.updateByPrimaryKeySelective(tradeBatchInfo);
		
		//修改订单状态为失败
		for(PlatPayDetail platPayDetail:platPayDetailList){
			if(RespEnum.E00002.getCode().equals(bankResponse.getRespCode())){
				platPayDetail.setStatus(PayForStatusEnum.pf0003.getCode());
			}else{
				platPayDetail.setStatus(PayForStatusEnum.pf0004.getCode());
			}
			
			platPayDetail.setRetCode(bankResponse.getRetCode());
			platPayDetail.setRetDesc(bankResponse.getRetDesc());
			platPayDetail.setRespCode(bankResponse.getRespCode());
			platPayDetail.setRespDesc(bankResponse.getRespDesc());
			platPayDetail.setBankTradeSeq(bankPayForTradeSeq);
			platPayDetail.setSettleTime(completeTime);
			platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		}
		
		//设置返回值
		batchPayForResponse.setRespCode(bankResponse.getRespCode());
		batchPayForResponse.setRespDesc(bankResponse.getRespDesc());
	}

	/**
	 * 批量代付成功结果处理--事务控制异常回滚
	 * @param batchPayForResponse
	 * @param platPayDetailList
	 * @param chooseChns
	 * @param bankPayForTradeSeq
	 * @param completeTime
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void successHandleWithBatch(BatchPayForResponse batchPayForResponse,TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList,
			List<MerchantChannelBalExt> chooseChns,String bankPayForTradeSeq,
			Date completeTime,T bankResponse) {
		
		//更新批次表信息状态
		tradeBatchInfo.setBatchStatus(BatchStatusEnum.b0002.getCode());
		tradeBatchInfo.setRespCode(bankResponse.getRespCode());
		tradeBatchInfo.setRespDesc(bankResponse.getRespDesc());
		tradeBatchInfoMapper.updateByPrimaryKeySelective(tradeBatchInfo);
		
		//再更新代付明细表状态
		for(PlatPayDetail platPayDetail:platPayDetailList){
			//修该代付状态为成功
			platPayDetail.setStatus(PayForStatusEnum.pf0002.getCode());
			platPayDetail.setRetCode(bankResponse.getRetCode());
			platPayDetail.setRetDesc(bankResponse.getRetDesc());
			platPayDetail.setRespCode(bankResponse.getRespCode());
			platPayDetail.setRespDesc(bankResponse.getRespDesc());
			platPayDetail.setBankTradeSeq(bankPayForTradeSeq);
			platPayDetail.setSettleTime(completeTime);
			platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		}
		
		//设置返回值
		batchPayForResponse.setBatchStatus(PayForStatusEnum.pf0002.getCode());
		batchPayForResponse.setRespCode(bankResponse.getRespCode());
		batchPayForResponse.setRespDesc(bankResponse.getRespDesc());
	}

    /**
     * 批量代付时发生异常---交易时异常做未知状态处理
	 * 1、修改订单及流水状态，并更新失败原因
	 * 2、封装返回对象
	 * 3、添加异步查询队列到redis定时查询银行返回结果
     * @param tradeResponse
     * @param platPayDetailList
     * @param errorCode
     * @param errorMsg
     */
	private void payForExceptionHandleWithBatch(BatchPayForResponse tradeResponse,TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList, String errorCode, String errorMsg) {
		ChannelVo channelVo = new ChannelVo();
		channelVo.setMercNo(platPayDetailList.get(0).getMercNo());
		channelVo.setRespCode(errorCode);
		channelVo.setRespDesc(errorMsg);
		this.unknownHandleWithBatch(tradeResponse, tradeBatchInfo, platPayDetailList,  channelVo);
	}
	
	/**
	 * 批量代付处理中
	 * @param batchPayForResponse
	 * @param platPayDetailList
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void unknownHandleWithBatch(BatchPayForResponse batchPayForResponse,TradeBatchInfo tradeBatchInfo,List<PlatPayDetail> platPayDetailList,T bankResponse) {
		//更新批次表信息状态
		tradeBatchInfo.setBatchStatus(BatchStatusEnum.b0003.getCode());
		tradeBatchInfo.setSuccessBatchAmt(new BigDecimal(0));
		tradeBatchInfo.setSuccessBatchCnt(0);
		tradeBatchInfo.setFailBatchAmt(tradeBatchInfo.getBatchAmt());
		tradeBatchInfo.setFailBatchCnt(tradeBatchInfo.getBatchCnt());
		tradeBatchInfo.setRespCode(bankResponse.getRespCode());
		tradeBatchInfo.setRespDesc(bankResponse.getRespDesc());
		tradeBatchInfoMapper.updateByPrimaryKeySelective(tradeBatchInfo);
		//修改订单状态为失败
		for(PlatPayDetail platPayDetail:platPayDetailList){
			platPayDetail.setStatus(PayForStatusEnum.pf0003.getCode());
			platPayDetail.setRetCode(bankResponse.getRetCode());
			platPayDetail.setRetDesc(bankResponse.getRetDesc());
			platPayDetail.setRespCode(bankResponse.getRespCode());
			platPayDetail.setRespDesc(bankResponse.getRespDesc());
			platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		}
		
		//设置返回值
		batchPayForResponse.setBatchStatus(PayForStatusEnum.pf0003.getCode());
		batchPayForResponse.setRespCode(bankResponse.getRespCode());
		batchPayForResponse.setRespDesc(bankResponse.getRespDesc());
	}

	
	/**********批量代付处理结束***************/

	/**
	 * 代付结果查询
	 * @param tradeResponse
	 * @param platPayDetail
	 * @param chooseChns
	 * @param bankPayForTradeSeq
	 * @param completeTime
	 * @param bankResponse
	 * @param freezeDetail
	 * @param <T>
	 */
	private <T extends ChannelVo>  void handlePayForResult(PayForResponse tradeResponse,PlatPayDetail platPayDetail,List<MerchantChannelBalExt> chooseChns
			,String bankPayForTradeSeq,Date completeTime,T bankResponse,FreezeDetail freezeDetail) {
		log.info("上游通道返回代付状态为：{},返回信息为{}",bankResponse.getRespCode(),bankResponse.getRespDesc());
		if(bankResponse.getRespCode().equals(RespEnum.S00000.getCode())){
			// 成功
			this.successHandle(tradeResponse,platPayDetail,chooseChns,bankPayForTradeSeq,completeTime,bankResponse);
		}else{//失敗
			//失败时，解冻冻结金额
			this.unfreezeAvailBal(platPayDetail, chooseChns,freezeDetail);
			this.failHandle(tradeResponse,platPayDetail,bankPayForTradeSeq,completeTime,bankResponse);
		}
	}

	/**
	 * 代付处理中
	 * @param payForResponse
	 * @param platPayDetail
	 * @param bankResponse
	 */
	@Transactional
	protected  <T extends ChannelVo> void unknownHandle(PayForResponse payForResponse,PlatPayDetail platPayDetail,T bankResponse) {
		//修改订单状态为失败
		platPayDetail.setStatus(PayForStatusEnum.pf0004.getCode());
		platPayDetail.setRetCode(bankResponse.getRetCode());
		platPayDetail.setRetDesc(bankResponse.getRetDesc());
		platPayDetail.setRespCode(bankResponse.getRespCode());
		platPayDetail.setRespDesc(bankResponse.getRespDesc());
		platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		
		//设置返回值
		platPayDetail.setStatus(PayForStatusEnum.pf0004.getCode());
		payForResponse.setStatus(PayForStatusEnum.pf0003.getCode());
		payForResponse.setRespCode(bankResponse.getRespCode());
		payForResponse.setRespDesc(bankResponse.getRespDesc());
	}

	/**
	 * 代付失败结果处理--事务控制，异常回滚
	 * @param payForResponse
	 * @param platPayDetail
	 */
	@Transactional
	protected  <T extends ChannelVo> void failHandle(PayForResponse payForResponse,PlatPayDetail platPayDetail,String bankPayForTradeSeq,Date completeTime,T bankResponse) {
		//修改订单状态为失败
		if(RespEnum.E00002.getCode().equals(bankResponse.getRespCode())){
			platPayDetail.setStatus(PayForStatusEnum.pf0003.getCode());
		}else{
			platPayDetail.setStatus(PayForStatusEnum.pf0004.getCode());
		}
		
		platPayDetail.setRetCode(bankResponse.getRetCode());
		platPayDetail.setRetDesc(bankResponse.getRetDesc());
		platPayDetail.setRespCode(bankResponse.getRespCode());
		platPayDetail.setRespDesc(bankResponse.getRespDesc());
		platPayDetail.setBankTradeSeq(bankPayForTradeSeq);
		platPayDetail.setSettleTime(completeTime);
		platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		
		//设置返回值
		payForResponse.setPayId(platPayDetail.getPayId());
		if(RespEnum.E00002.getCode().equals(bankResponse.getRespCode())){
			payForResponse.setStatus(PayForStatusEnum.pf0003.getCode());
		}else{
			payForResponse.setStatus(PayForStatusEnum.pf0004.getCode());
		}
		payForResponse.setRespCode(bankResponse.getRespCode());
		payForResponse.setRespDesc(bankResponse.getRespDesc());
		if(completeTime != null){
			payForResponse.setTradeTime(DateUtils.formatTime(completeTime));
		}
		
	}


	/**
	 * 代付成功结果处理--事务控制异常回滚
	 * @param payForResponse
	 * @param platPayDetail
	 * @param chooseChns
	 * @param bankPayForTradeSeq
	 * @param completeTime
	 * @param bankResponse
	 * @param <T>
	 */
	@Transactional
	protected  <T extends ChannelVo> void successHandle(PayForResponse payForResponse,PlatPayDetail platPayDetail,List<MerchantChannelBalExt> chooseChns,String bankPayForTradeSeq,Date completeTime,T bankResponse) {
		//修该代付状态为成功
		platPayDetail.setStatus(PayForStatusEnum.pf0002.getCode());
		platPayDetail.setRetCode(bankResponse.getRetCode());
		platPayDetail.setRetDesc(bankResponse.getRetDesc());
		platPayDetail.setRespCode(bankResponse.getRespCode());
		platPayDetail.setRespDesc(bankResponse.getRespDesc());
		platPayDetail.setBankTradeSeq(bankPayForTradeSeq);
		platPayDetail.setSettleTime(completeTime);
		platPayDetailMapper.updateByPrimaryKeySelective(platPayDetail);
		
		
		//设置返回值
		payForResponse.setPayId(platPayDetail.getPayId());
		payForResponse.setStatus(PayForStatusEnum.pf0002.getCode());
		payForResponse.setRespCode(bankResponse.getRespCode());
		payForResponse.setRespDesc(bankResponse.getRespDesc());
		if(completeTime != null){
			payForResponse.setTradeTime(DateUtils.formatTime(completeTime));
		}
	}

	/**
	 * 代付时发生异常---交易时异常做未知状态处理
	 * 1、修改订单及流水状态，并更新失败原因
	 * 2、封装返回对象
	 * 3、添加异步查询队列到redis定时查询银行返回结果
	 * @param payForResponse
	 * @param payForOrderId
	 * @param errorCode
	 * @param errorMsg
	 */
	/**
	 * 代付时发生异常---交易时异常做未知状态处理
	 * 	1、修改订单及流水状态，并更新失败原因
	 *  2、封装返回对象
	 *  3、添加异步查询队列到redis定时查询银行返回结果
	 * @param tradeResponse
	 * @param platPayDetail
	 * @param errorCode
	 * @param errorMsg
	 */
	private void payForExceptionHandle(PayForResponse tradeResponse,PlatPayDetail platPayDetail, String errorCode, String errorMsg) {
		ChannelVo channelVo = new ChannelVo();
		channelVo.setMercNo(platPayDetail.getMercNo());
		channelVo.setOrderId(platPayDetail.getPayId());
		channelVo.setRespCode(errorCode);
		channelVo.setRespDesc(errorMsg);
		this.unknownHandle(tradeResponse, platPayDetail,  channelVo);
	}

}
