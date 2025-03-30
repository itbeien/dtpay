package cn.itbeien.payment.service.payfor.impl;

import cn.itbeien.common.entity.FreezeDetail;
import cn.itbeien.common.entity.merchant.MerchantChannelBal;
import cn.itbeien.common.entity.merchant.MerchantChannelBalKey;
import cn.itbeien.common.entity.merchant.MerchantAccRel;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.entity.trade.PlatPayDetail;
import cn.itbeien.common.entity.trade.TradeBatchInfo;
import cn.itbeien.common.enums.PayForStatusEnum;
import cn.itbeien.common.mapper.FreezeDetailMapper;
import cn.itbeien.common.mapper.PlatPayDetailMapper;
import cn.itbeien.common.mapper.merchant.MerchantAccRelMapper;
import cn.itbeien.common.mapper.merchant.MerchantChannelBalMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.common.util.Arith;
import cn.itbeien.common.util.DateUtils;
import cn.itbeien.common.util.StringUtils;
import cn.itbeien.payment.channel.vo.ChnBatchPayForNotifyResponse;
import cn.itbeien.payment.channel.vo.ChnPayForNotifyResponse;
import cn.itbeien.payment.component.cache.RedisLock;
import cn.itbeien.payment.core.vo.mq.MchPayForNotifyMqContent;
import cn.itbeien.payment.core.vo.response.PayForNotifyResponse;
import cn.itbeien.payment.enums.RespEnum;
import cn.itbeien.payment.exception.TradeException;
import cn.itbeien.payment.mapper.trade.TradeBatchInfoMapper;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Component
@Slf4j
public class PayForChannelService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisCache redisCacheUtils;
	
	@Autowired
	private MerchantAccRelMapper merchantAccRelMapper;
	
	@Autowired
	private MerchantChannelBalMapper merchantChannelBalMapper;
	
	@Autowired
	private PlatPayDetailMapper platPayDetailMapper;
	
	@Autowired
	private FreezeDetailMapper freezeDetailMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private  RedisTemplate redisTemplate;
	
	@Autowired
	private TradeBatchInfoMapper TradeBatchInfoMapper;
	
	/**
	 * 接收上游渠道  代付  结果通知，上游异步通知代付结果
	 */
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public void payForNotify(ChnPayForNotifyResponse notifyResponse){
		// 修改代付结果
		PlatPayDetail record = new PlatPayDetail();
		record.setPayId(notifyResponse.getPayId());
		
		// 根据payId查询出对应的平台商户号
		PlatPayDetail payDetail = platPayDetailMapper.selectByPrimaryKey(notifyResponse.getPayId());
		String mercNo = payDetail.getMercNo();
		
		//获取分布式锁
		RedisLock distributedLock = new RedisLock(redisTemplate, payDetail.getMercNo(), 50000,60000);
		try {
			//平台通过notify_url通知商户，商户做业务处理后，需要以字符串的形式反馈处理结果，内容如下
			//返回结果	结果说明
			//success	处理成功，平台收到此结果后不再进行后续通知
			//fail或其它字符	处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
			// 代付成功后更新  平台代付明细表 rx_plat_pay_detail 、 商户虚拟账户表  RX_MERCHANT_ACC_REL 和    商户对应渠道余额表   rx_merchant_channel_bal
			
			boolean lockFlag = distributedLock.lock();
			log.info("平台代付订单号:" + payDetail.getPayId() + ",获取锁标识值为：{}，获取分布式锁{}",lockFlag,lockFlag?"成功":"失败");
			if(!lockFlag){
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
			
			//查看是否商户冻结明细表是否已解冻
			log.info("商户冻结流水号：{}",payDetail.getRsfld2());
			FreezeDetail freezeDetail = freezeDetailMapper.selectByPrimaryKey(payDetail.getRsfld2());
			if(freezeDetail==null||((freezeDetail!=null)&&"01".equals(freezeDetail.getFreezeStatus()))){
				log.info("商户冻结流水号：{}已解冻,不允许重复解冻",payDetail.getRsfld2());
				throw new TradeException(RespEnum.E00068.getCode(), RespEnum.E00068.getDesc());
			}
			
			// 根据账号类型及代付订单号查询商户虚拟账户表
			MerchantAccRel merchantAccRel = this.merchantAccRelMapper.selectByMercAcct(mercNo, "00"); //商户虚拟账户表
			// 代付金额
			BigDecimal tradeAmt = notifyResponse.getTradeAmt();
			
			// 根据代付订单号查询  支付渠道编号  渠道商户号  商户编号
			Map<String, String> keyMap = platPayDetailMapper.selectKeyByPrimaryKey(notifyResponse.getPayId());
			MerchantChannelBalKey key = new MerchantChannelBalKey();
			key.setMercNo(merchantAccRel.getMercNo());// 下游商户编号
			key.setChannelCode(keyMap.get("channelCode"));	// 支付渠道编号
			key.setPayingMercNo(keyMap.get("payingMercNo"));	// 渠道商户号
			// 根据商户编号、支付渠道编号、渠道商户号获取记录。
			MerchantChannelBal merchantChannelBal = this.merchantChannelBalMapper.selectByPrimaryKey(key);
			String feeValue = null;
			// 支付成功
			if("0".equals(notifyResponse.getStatus())){
				record.setStatus(PayForStatusEnum.pf0000.getCode());
				if(!redisCacheUtils.exists(merchantAccRel.getMercNo())) {
					throw new TradeException(RespEnum.E00031.getCode(), RespEnum.E00031.getDesc());
				}
				// 从缓存中获取商户信息 手续费
				MerchantInfo merchantInfo = (MerchantInfo)this.redisCacheUtils.getCacheObject(merchantAccRel.getMercNo());//根据下游商户号获取商户缓存信息
				// 手续费
				BigDecimal feeBal = merchantInfo.getPayFeeValue();
				// 要减去 或者 加上的金额
				BigDecimal subAmt = Arith.add(tradeAmt, feeBal);
				log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantInfo.getMercNo() + "对应虚户表中的的原总金额为:" + merchantAccRel.getAcctBal() + ",冻结金额为:" +  merchantAccRel.getFreezeBal() + ",出账总金额为:" + merchantAccRel.getOutAmt() + ",手续费为:"+ merchantAccRel.getFeeBal());
				// 总余额 
				merchantAccRel.setAcctBal(Arith.sub(merchantAccRel.getAcctBal() == null ? new BigDecimal("0") : merchantAccRel.getAcctBal(), subAmt));
				// 冻结金额
				merchantAccRel.setFreezeBal(Arith.sub(merchantAccRel.getFreezeBal() == null ? new BigDecimal("0") : merchantAccRel.getFreezeBal(), subAmt));
				// 出账总金额
				merchantAccRel.setOutAmt(Arith.add(merchantAccRel.getOutAmt() == null ? new BigDecimal("0") : merchantAccRel.getOutAmt(), subAmt));
				// 手续费
				merchantAccRel.setFeeBal(Arith.add(merchantAccRel.getFeeBal() == null ? new BigDecimal("0") : merchantAccRel.getFeeBal(), feeBal));
				merchantAccRel.setUpdateTime(new Date());
				//更新商户虚拟账户表
				this.merchantAccRelMapper.updateBymercNoSelective(merchantAccRel);	
				log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantInfo.getMercNo() + "对应虚户表中的更新后的总金额为:" + merchantAccRel.getAcctBal() + ",冻结金额为:" +  merchantAccRel.getFreezeBal() + ",出账总金额为:" + merchantAccRel.getOutAmt() + ",手续费为:"+ merchantAccRel.getFeeBal());


				if(merchantChannelBal != null){
					log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantInfo.getMercNo() + "--" + keyMap.get("channelCode") + "--" + keyMap.get("payingMercNo") + "对应渠道余额表中原总金额为:" + merchantChannelBal.getChnBal() + ",冻结金额为:" + merchantChannelBal.getChnFreezeBal() + ",出账总金额为:" + merchantChannelBal.getChnOutAmt() + ",手续费为"+ merchantChannelBal.getChnFeeBal());
					// 总余额 
					merchantChannelBal.setChnBal(Arith.sub(merchantChannelBal.getChnBal() == null ? new BigDecimal("0") : merchantChannelBal.getChnBal(), subAmt));
					// 冻结金额
					merchantChannelBal.setChnFreezeBal(Arith.sub(merchantChannelBal.getChnFreezeBal() == null ? new BigDecimal("0") : merchantChannelBal.getChnFreezeBal(), subAmt));
					// 出账总金额
					merchantChannelBal.setChnOutAmt(Arith.add(merchantChannelBal.getChnOutAmt() == null ? new BigDecimal("0") : merchantChannelBal.getChnOutAmt(), subAmt));
					// 手续费
					merchantChannelBal.setChnFeeBal(Arith.add(merchantChannelBal.getChnFeeBal() == null ? new BigDecimal("0") : merchantChannelBal.getChnFeeBal(), feeBal));
					this.merchantChannelBalMapper.updateByPrimaryKey(merchantChannelBal);
					log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantInfo.getMercNo() + "--" + keyMap.get("channelCode") + "--" + keyMap.get("payingMercNo") +  "对应渠道余额表中更新后的总金额为:" + merchantChannelBal.getChnBal() + ",冻结金额为:" + merchantChannelBal.getChnFreezeBal() + ",出账总金额为:" + merchantChannelBal.getChnOutAmt() + ",手续费为:"+ merchantChannelBal.getChnFeeBal());
				}
				
				// 解冻
				this.unfreeze(payDetail.getRsfld2());
				
				if(StringUtils.isNotNull(notifyResponse.getBankPayForTradeSeq())) {  // 上游代付订单号
					record.setBankTradeSeq(notifyResponse.getBankPayForTradeSeq());
				}
				if(notifyResponse.getTradeEndTime() != null) {
					record.setSettleTime(notifyResponse.getTradeEndTime()); // 代付完成时间
				} else {
					record.setSettleTime(new Date()); // 代付完成时间 设置为当前系统时间
				}
			}else if("1".equals(notifyResponse.getStatus())){ // 支付失败
				record.setStatus(PayForStatusEnum.pf0004.getCode());
				if(!redisCacheUtils.exists(merchantAccRel.getMercNo())) {
					throw new TradeException(RespEnum.E00031.getCode(), RespEnum.E00031.getDesc());
				}
				// 从缓存中获取商户信息 手续费
				MerchantInfo merchantInfo = (MerchantInfo)this.redisCacheUtils.getCacheObject(merchantAccRel.getMercNo());//根据下游商户号获取商户缓存信息
				// 手续费
				BigDecimal feeBal = merchantInfo.getPayFeeValue();
				// 失败 则 重新获取 代付金额
				tradeAmt = payDetail.getTransAmt();
				// 要减去 或者 加上的金额
				BigDecimal subAmt = Arith.add(tradeAmt, feeBal);
				
				log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantAccRel.getMercNo() + "对应虚户表中原可提现金额为:" + merchantAccRel.getAcctAvaiBal() + ",冻结金额为:" + merchantAccRel.getFreezeBal());
				// 支付失败则 将原来虚户 和 对应渠道  中 冻结的资金还原
				// 虚户中 总金额 加 代付金额， 冻结金额 减 代付金额
				merchantAccRel.setAcctAvaiBal(Arith.add(merchantAccRel.getAcctAvaiBal() == null ? new BigDecimal("0") : merchantAccRel.getAcctAvaiBal(), subAmt));
				merchantAccRel.setFreezeBal(Arith.sub(merchantAccRel.getFreezeBal() == null ? new BigDecimal("0") : merchantAccRel.getFreezeBal(), subAmt));
				merchantAccRel.setUpdateTime(new Date());
				// 更新商户虚拟账户表
				this.merchantAccRelMapper.updateBymercNoSelective(merchantAccRel);
				log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantAccRel.getMercNo() + "对应虚户表中更新后可提现金额为:" + merchantAccRel.getAcctAvaiBal() + ",冻结金额为:" + merchantAccRel.getFreezeBal());
				
				// 更新对应渠道表
				if(merchantChannelBal != null){
					log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantChannelBal.getMercNo() + "--" + keyMap.get("channelCode") + "--" + keyMap.get("payingMercNo") + "对应渠道余额表中原可用金额为:" + merchantChannelBal.getChnAvaiBal() + ",冻结金额为:" + merchantChannelBal.getChnFreezeBal());
					merchantChannelBal.setChnAvaiBal(Arith.add(merchantChannelBal.getChnAvaiBal() == null ? new BigDecimal("0") : merchantChannelBal.getChnAvaiBal(), subAmt));
					merchantChannelBal.setChnFreezeBal(Arith.sub(merchantChannelBal.getChnFreezeBal() == null ? new BigDecimal("0") : merchantChannelBal.getChnFreezeBal(), subAmt));
					this.merchantChannelBalMapper.updateByPrimaryKey(merchantChannelBal);
					log.info("上游异步通知 --平台代付订单号:" + payDetail.getPayId() + ",商户" + merchantChannelBal.getMercNo() + "--" + keyMap.get("channelCode") + "--" + keyMap.get("payingMercNo") + "对应渠道余额表中更新后可用金额为:" + merchantChannelBal.getChnAvaiBal() + ",冻结金额为:" + merchantChannelBal.getChnFreezeBal());
				}
				
				// 解冻
				this.unfreeze(payDetail.getRsfld2());
				feeValue = "0";
			}
			
			record.setUpdateTime(new Date());
			// 平台返回信息，不更新平台信息，为了对比 哪一步出错
//			record.setRespCode(notifyResponse.getRespCode());
//			record.setRespDesc(notifyResponse.getRespDesc());
			// 渠道
			record.setRetCode(notifyResponse.getRetCode());
			record.setRetDesc(notifyResponse.getRetDesc());
			platPayDetailMapper.updateByPrimaryKeySelective(record);
			
			// 根据payid查询 平台代付明细表
			PlatPayDetail platPayDetail = platPayDetailMapper.selectByPrimaryKey(notifyResponse.getPayId());
			// 状态不为提现的时候 且 回调有值的时候 ，  需要 回调下游
			if(!"03".equals(platPayDetail.getTradeType()) && StringUtils.isNotNull(platPayDetail.getService()) && ("0".equals(notifyResponse.getStatus()) || "1".equals(notifyResponse.getStatus()))) {
				//通知下游商户订单状态开发，异步通过消息队列触发调用下游商户通知代付结果方法
				MchPayForNotifyMqContent mchNotifyMqContent = new MchPayForNotifyMqContent();
				PayForNotifyResponse payNotifyResponse = new PayForNotifyResponse();
				payNotifyResponse.setMercNo(merchantAccRel.getMercNo());
				payNotifyResponse.setMercOrderNo(platPayDetail.getMercOrderNo());
				payNotifyResponse.setTradeAmt(String.valueOf(notifyResponse.getTradeAmt()));
				payNotifyResponse.setPayId(notifyResponse.getPayId());
				payNotifyResponse.setStatus(record.getStatus());   // 支付状态
				payNotifyResponse.setTradeTime(cn.itbeien.common.util.DateUtils.formatTime(platPayDetail.getTradeTime()));  // 代付发起时间
				if(platPayDetail.getSettleTime() != null) {
					payNotifyResponse.setTradeEndTime(DateUtils.formatTime(platPayDetail.getSettleTime()));  // 代付完成时间
				}
				payNotifyResponse.setFeeValue(StringUtils.isEmpty(feeValue) ? String.valueOf(platPayDetail.getFeeValue().setScale(2, BigDecimal.ROUND_HALF_UP)) : feeValue); // 取两位小数
				payNotifyResponse.setNotifyUrl(platPayDetail.getService());  // 获取数据库中的回调地址
				//DtRocketMqProducer rxRocketMqProducer = (DtRocketMqProducer)SpringContextUtil.getBean("rxRocketMqProducer");//消息生产者
				BeanUtils.copyProperties(payNotifyResponse, mchNotifyMqContent);
				//rxRocketMqProducer.realTimeMqPayForNotifyMch(mchNotifyMqContent);
			}
		} catch (InterruptedException e) {
			log.error("获取分布式锁失败,平台代付订单号：" + payDetail.getPayId() + ",代付查询：["+payDetail.getPayId()+"]代付结果更新发生异常：" + e.getMessage(),e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} catch (Exception e) {
			log.error("平台代付订单号：" + payDetail.getPayId() + ",代付查询：["+payDetail.getPayId()+"]代付结果更新发生异常：" + e.getMessage(), e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} finally{
			log.info("平台代付订单号：" + payDetail.getPayId() + ",回调 释放分布式锁");
			distributedLock.unlock();
		}
	}
	
	
	/**
	 * 接收上游渠道  批量代付  结果通知，上游异步通知代付结果
	 */
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public void batchPayForNotify(ChnBatchPayForNotifyResponse notifyResponse){
		// 修改代付批次结果
		TradeBatchInfo batchInfo = new TradeBatchInfo();
		batchInfo.setPlatBatchNo(notifyResponse.getPlatBatchNo());
		// 平台批次号
		String platBatchNo = notifyResponse.getPlatBatchNo();
		// 代付明细List
	    List<ChnPayForNotifyResponse> payForRes = notifyResponse.getPayForRes();
	    // 根据批次号 查询出代付明细
	    List<PlatPayDetail> tradeBatchInfos = platPayDetailMapper.selectPlatDetailByPlatBatchNo(platBatchNo);
	    if(tradeBatchInfos == null || tradeBatchInfos != null && tradeBatchInfos.get(0) == null) {
			log.info("平台代付批次号:" + platBatchNo + ",根据批次号查询代付明细为空,订单不存在！");
	    	throw new TradeException(RespEnum.E00025.getCode(), RespEnum.E00025.getDesc());
	    }
	    // 得到商户号
	    String mercNo = tradeBatchInfos.get(0).getMercNo();
	    if(!redisCacheUtils.exists(mercNo)) {
			throw new TradeException(RespEnum.E00031.getCode(), RespEnum.E00031.getDesc());
		}
	    
	    // 根据批次号 关联查询 冻结明细记录，将冻结明细保存到Map
	    Map<String, FreezeDetail> freeDetailMap = new HashMap<String, FreezeDetail>();
	    List<FreezeDetail> freezeDetails = freezeDetailMapper.selectFreezeDetailByBatchNo(platBatchNo);
	    for(FreezeDetail fd : freezeDetails) {
	    	freeDetailMap.put(fd.getId(), fd);
	    }
	    
	    // 从缓存中获取商户信息 手续费
		MerchantInfo merchantInfo = (MerchantInfo)this.redisCacheUtils.getCacheObject(mercNo);	//根据下游商户号获取商户缓存信息
		// 手续费
		BigDecimal feeBal = merchantInfo.getPayFeeValue();
	    // 代付明细 保存到Map
	    Map<String, PlatPayDetail> infoMap = new HashMap<String, PlatPayDetail>();
	    for(PlatPayDetail infoVo : tradeBatchInfos) {
	    	infoMap.put(infoVo.getPayId(), infoVo);
	    }
	    // 修改代付结果
 		PlatPayDetail record = null;
 		List<PlatPayDetail> payDetails = new ArrayList<PlatPayDetail>();
  		
  		// 成功总金额
  		BigDecimal successAcctBal = new BigDecimal("0");
  		// 成功冻结金额
  		BigDecimal successFreezeBal =  new BigDecimal("0");
  		// 成功出账总金额
  		BigDecimal successOutAmt =  new BigDecimal("0");
  		// 成功出账手续费
  		BigDecimal successFeeBal =  new BigDecimal("0");
  		// 失败可用金额
		BigDecimal failAcctAvaiBal = new BigDecimal("0");
		// 失败冻结金额
  		BigDecimal failFreezeBal =  new BigDecimal("0");
  		// 失败出账手续费
  		BigDecimal failFeeBal =  new BigDecimal("0");
  		
  		MerchantAccRel merchantAccRel = null;
  		MerchantChannelBal merchantChannelBal = null;
  		List<MerchantChannelBal> channelBals = new ArrayList<MerchantChannelBal>(); 
  		
  		List<String> fzDetails = new ArrayList<String>();
  		//获取分布式锁
  		RedisLock distributedLock = new RedisLock(redisTemplate, mercNo, 50000,60000);
  		try {
  			boolean lockFlag = distributedLock.lock();
			log.info("平台代付批次号:" + platBatchNo + ",获取锁标识值为：{}，获取分布式锁{}",lockFlag,lockFlag ? "成功":"失败");
			if(!lockFlag){
				throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
			}
  			/*
  			 * 平台通过notifyUrl通知商户，商户做业务处理后，需要以字符串的形式反馈处理结果，内容如下
  			 * 返回结果	结果说明
  			 * 		success	处理成功，平台收到此结果后不再进行后续通知
  			 * 		fail或其它字符	处理不成功，平台收到此结果或者没有收到任何结果，系统通过补单机制再次通知
  			 * 代付成功后更新  批次信息表 rx_trade_batch_info、 平台代付明细表 rx_plat_pay_detail、 商户虚拟账户表  rx_merchant_acc_rel 和    商户对应渠道余额表   rx_merchant_channel_bal
  			 */
  			// 遍历 代付明细信息
			for(ChnPayForNotifyResponse payFor : payForRes) {
  				record = new PlatPayDetail();
  				record.setPayId(payFor.getPayId());
  				// 根据payId查询出对应的记录
  				PlatPayDetail payDetail = infoMap.get(payFor.getPayId());
  				
  				merchantChannelBal = new MerchantChannelBal();
  				merchantChannelBal.setMercNo(mercNo);
  				merchantChannelBal.setChannelCode(payDetail.getChannelCode());
  				merchantChannelBal.setPayingMercNo(payDetail.getPayingMercNo());
  				
  				// 代付金额
  				BigDecimal tradeAmt = payFor.getTradeAmt();
  				// 代付订单金额不相等
  				if(payDetail.getTransAmt().compareTo(tradeAmt) != 0) {
					log.info("代付订单号:" + payFor.getPayId() + ",上游返回 订单代付金额 与 数据库中 保存金额 不等,上游返回金额为:{},数据库中保存金额为:{}", payFor.getTradeAmt(), payDetail.getTransAmt());
  					throw new TradeException(RespEnum.E00069.getCode(), RespEnum.E00069.getDesc());
  				}
  				// 查看是否商户冻结明细表是否已解冻
				log.info("商户冻结流水号：{},平台代付订单号：{}", payDetail.getRsfld2(), payDetail.getPayId());
  				FreezeDetail freezeDetail = freeDetailMap.get(payDetail.getRsfld2());
  				if(freezeDetail == null || ((freezeDetail!=null) && "01".equals(freezeDetail.getFreezeStatus()))){
					log.info("商户冻结流水号：{},平台代付订单号：{}, 已解冻,不允许重复解冻",payDetail.getRsfld2(), payDetail.getPayId());
  					throw new TradeException(RespEnum.E00068.getCode(), RespEnum.E00068.getDesc());
  				}
  				// 代付成功
  				if("0".equals(payFor.getStatus())){
  					record.setStatus(PayForStatusEnum.pf0000.getCode()); 
  					// 要减去 或者 加上的金额
  					BigDecimal subAmt = Arith.add(tradeAmt, feeBal);
  					// 总余额 
  					successAcctBal = Arith.add(successAcctBal, subAmt);
  					// 冻结金额
  					successFreezeBal = Arith.add(successFreezeBal, subAmt);
  					// 出账总金额
  					successOutAmt = Arith.add(successOutAmt, subAmt);
  					// 手续费
  					successFeeBal =  Arith.add(successFeeBal, feeBal);
  					
					// 商户对应渠道 总余额 
					merchantChannelBal.setChnBal(subAmt);
					// 商户对应渠道 冻结金额
					merchantChannelBal.setChnFreezeBal(subAmt);
					// 商户对应渠道 出账总金额
					merchantChannelBal.setChnOutAmt(subAmt);
					// 商户对应渠道 手续费
					merchantChannelBal.setChnFeeBal(feeBal);
					channelBals.add(merchantChannelBal);
					
  					// 解冻
  					fzDetails.add(payDetail.getRsfld2());
  					
  					if(StringUtils.isNotNull(payFor.getBankPayForTradeSeq())) {  // 上游代付订单号
  						record.setBankTradeSeq(payFor.getBankPayForTradeSeq());
  					}
  					if(payFor.getTradeEndTime() != null) {
  						record.setSettleTime(payFor.getTradeEndTime()); // 代付完成时间
  					} else {
  						record.setSettleTime(new Date()); // 代付完成时间 设置为当前系统时间
  					}
  				} else if("1".equals(payFor.getStatus())){ // 代付失败
  					record.setStatus(PayForStatusEnum.pf0004.getCode());
  					// 失败 则 重新获取 代付金额
  					tradeAmt = payDetail.getTransAmt();
  					// 要减去 或者 加上的金额
  					BigDecimal subAmt = Arith.add(tradeAmt, feeBal);
  					
  					// 支付失败则 将原来虚户 和 对应渠道  中 冻结的资金还原
  					// 虚户中 可用金额 加 代付金额， 冻结金额 减 代付金额
  					failAcctAvaiBal = Arith.add(failAcctAvaiBal, subAmt);
  					failFreezeBal = Arith.add(failFreezeBal, subAmt);
  					
  					// 更新对应渠道表
					merchantChannelBal.setFailChnAvaiBal(subAmt);
					merchantChannelBal.setFailChnFreezeBal(subAmt);
					channelBals.add(merchantChannelBal);
  					
  					// 解冻
  					fzDetails.add(payDetail.getRsfld2());
  				}

  				// 渠道
  				record.setRetCode(payFor.getRetCode());
  				record.setRetDesc(payFor.getRetDesc());
  				payDetails.add(record);
			}
			
			BigDecimal freezeBal = Arith.add(successFreezeBal, failFreezeBal); // 冻结金额
			BigDecimal mfeeBal = Arith.sub(successFeeBal, failFeeBal);  // 手续费
			
			merchantAccRel = new MerchantAccRel();
			merchantAccRel.setUpdateTime(new Date());
			merchantAccRel.setMercNo(mercNo);
			merchantAccRel.setAcctBal(successAcctBal.compareTo(new BigDecimal("0")) == 0 ? null : successAcctBal);    // 总金额
			merchantAccRel.setFreezeBal(freezeBal.compareTo(new BigDecimal("0")) == 0 ? null : freezeBal);  // 冻结金额
			merchantAccRel.setAcctAvaiBal(failAcctAvaiBal.compareTo(new BigDecimal("0")) == 0 ? null : failAcctAvaiBal);  // 可用金额
			merchantAccRel.setOutAmt(successOutAmt.compareTo(new BigDecimal("0")) == 0 ? null : successOutAmt); // 出账总金额
			merchantAccRel.setFeeBal(mfeeBal.compareTo(new BigDecimal("0")) == 0 ? null : mfeeBal); // 出账手续费
			
			// 更新商户虚户表
			MerchantAccRel accRel = merchantAccRelMapper.selectByMercAcct(mercNo, "00");
			log.info("商户号：{},虚户金额更新前：{}", mercNo, JSON.toJSONString(accRel));
			merchantAccRelMapper.updateAccBalByMercNo(merchantAccRel);
			log.info("商户号：{},虚户金额更新后：{}", mercNo, JSON.toJSONString(merchantAccRel));
			
			// 批量更新商户对应渠道表
			if(StringUtils.isNotNull(channelBals)) {
				merchantChannelBalMapper.batchUpdateChaBal(channelBals);
				log.info("成功的代付订单。商户号：{},批量更新商户对应渠道的记录为：{}", mercNo, JSON.toJSONString(channelBals));
			}
			
			// 批量更新 代付明细表
			if(StringUtils.isNotNull(payDetails)) {
				platPayDetailMapper.updateBatchByPrimaryKeySelective(payDetails);
				log.info("商户号：{},批量更新 代付明细表的记录为：{}", mercNo, JSON.toJSONString(payDetails));
			}
			
			// 批量更新 资金冻结表
			if(StringUtils.isNotNull(fzDetails)) {
				freezeDetailMapper.updateBatchFreeDetials(fzDetails);
				log.info("商户号：{},批量更新 资金冻结表：{}", mercNo, JSON.toJSONString(fzDetails));
			}
			
			// 更新批次表
			batchInfo.setBatchAmt(notifyResponse.getBatchAmt());
			batchInfo.setBatchCnt(notifyResponse.getBatchCnt());
			batchInfo.setSuccessBatchAmt(notifyResponse.getSuccessBatchAmt());
			batchInfo.setSuccessBatchCnt(notifyResponse.getSuccessBatchCnt());
			batchInfo.setFailBatchAmt(notifyResponse.getFailBatchAmt());
			batchInfo.setFailBatchCnt(notifyResponse.getFailBatchCnt());
			batchInfo.setBatchStatus(notifyResponse.getBatchStatus());
			batchInfo.setRetCode(notifyResponse.getRetCode());
			batchInfo.setRetDesc(notifyResponse.getRetDesc());
			batchInfo.setUpdateTime(new Date());
			TradeBatchInfoMapper.updateByPrimaryKeySelective(batchInfo);
  		} catch (InterruptedException e) {
			log.error("获取分布式锁失败,平台代付批次号：" + platBatchNo + ",批量代付回调：["+platBatchNo+"]代付结果更新发生异常：" + e.getMessage(),e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} catch (Exception e) {
			log.error("平台代付批次号：" + platBatchNo + ",批量代付回调：["+platBatchNo+"]代付结果更新发生异常：" + e.getMessage(), e);
			throw new TradeException(RespEnum.E99999.getCode(), RespEnum.E99999.getDesc());
		} finally{
			log.info("平台代付批次号：" + platBatchNo + ",回调 释放分布式锁");
			distributedLock.unlock();
		}
	}
	
	/**
	 * unfreeze:(解冻记录).  
	 * @author
	 * 2018年2月28日上午10:55:38
	 *
	 * @param uuid
	 */
	private void unfreeze(String uuid){
		FreezeDetail freezeDetail = new FreezeDetail();
		freezeDetail.setId(uuid);
		freezeDetail.setFreezeStatus("01");
		freezeDetailMapper.updateByPrimaryKeySelective(freezeDetail);
	}
	
}