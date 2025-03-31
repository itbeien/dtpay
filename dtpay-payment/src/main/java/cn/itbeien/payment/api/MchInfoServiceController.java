package cn.itbeien.payment.api;

import cn.itbeien.common.entity.RouterPolicy;
import cn.itbeien.common.entity.merchant.ExtMerchantInfo;
import cn.itbeien.common.entity.merchant.MerchantChannelMapping;
import cn.itbeien.common.entity.merchant.MerchantInfo;
import cn.itbeien.common.entity.merchant.MerchantPaywayMapping;
import cn.itbeien.common.entity.pay.PayChannel;
import cn.itbeien.common.entity.pay.PayChannelScenes;
import cn.itbeien.common.enums.DefaultFlagEnum;
import cn.itbeien.common.mapper.RouterPolicyMapper;
import cn.itbeien.common.mapper.merchant.ExtMerchantInfoMapper;
import cn.itbeien.common.mapper.merchant.MerchantChannelMappingMapper;
import cn.itbeien.common.mapper.merchant.MerchantInfoMapper;
import cn.itbeien.common.mapper.merchant.MerchantPaywayMappingMapper;
import cn.itbeien.common.redis.RedisCache;
import cn.itbeien.payment.mapper.pay.PayChannelMapper;
import cn.itbeien.payment.mapper.pay.PayChannelScenesMapper;
import cn.itbeien.payment.service.pay.PayOrderNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 商户信息接口
 * Copyright© 2025 itbeien
 */
@RestController
@Slf4j
public class MchInfoServiceController {
    
    @Autowired
    private PayOrderNotifyService payOrderNotifyService;
    
    @Autowired
	private RedisCache redisCache;
    
    
    @Autowired
	private MerchantPaywayMappingMapper merchantPaywayMappingMapper;
	
	@Autowired
	private MerchantInfoMapper merchantInfoMapper;
	@Autowired
	private PayChannelMapper payChannelMapper;
	@Autowired
	private PayChannelScenesMapper payChannelScenesMapper;
	@Autowired
	private MerchantChannelMappingMapper merchantChannelMappingMapper;
	@Autowired
	private RouterPolicyMapper routerPolicyMapper;
	@Autowired
	private ExtMerchantInfoMapper extMerchantInfoMapper;

    @RequestMapping(value = "/mch_info/select")
    public String selectMchInfo(@RequestParam String jsonParam) {
		return null;
    }

    @RequestMapping(value = "/cache/refresh.do")
    public String refreshCache(){
    	try{
    		
    		 // 执行操作
			log.info("手动执行加载业务缓存");
    		
    		//加载商户支付方式映射表(包含商户费率相关信息)
    		List<MerchantPaywayMapping> merchantPaywayMappings = merchantPaywayMappingMapper.selectAll();
    		for(MerchantPaywayMapping merchantPaywayMapping : merchantPaywayMappings){//key为商户支付方式映射表ID,商户号+支付方式编号+场景编号 唯一确认一条记录
				redisCache.setCacheObject(merchantPaywayMapping.getMercNo()+merchantPaywayMapping.getPaywayCode()+merchantPaywayMapping.getSceneCode(), merchantPaywayMapping);
    		}
    		
    		//加载商户信息表
    		List<MerchantInfo> mechantInfos = this.merchantInfoMapper.selectAll();
    		for(MerchantInfo merchantInfo : mechantInfos){//商户号为key存储商户信息
				redisCache.setCacheObject(merchantInfo.getMercNo(),merchantInfo);
    		}
    		
    		//加载机构信息表（已经废除），加载支付渠道表
    		List<PayChannel> payChannels  = payChannelMapper.selectAll();
    		for(PayChannel payChannel : payChannels){//缓存key为支付渠道编号
				redisCache.setCacheObject(payChannel.getChannelCode(),payChannel);
    		}
    		
    		//加载支付场景表
    		List<PayChannelScenes> payChannelScenes = this.payChannelScenesMapper.selectAll();
    		for(PayChannelScenes payChannelScene : payChannelScenes){//key为支付场景表渠道对应ID
				redisCache.setCacheObject(payChannelScene.getChannelCode()+payChannelScene.getPaywayCode()+payChannelScene.getSceneCode(), payChannelScene);
    		}
    		
    		
    		//加载商户支付渠道管理表,查询状态为启用状态的数据(ey),如果默认通道不为启用状态。去查下非默认通道最新创建的一条通道
    		List<MerchantChannelMapping> merchantChannelMappings = this.merchantChannelMappingMapper.selectAll(DefaultFlagEnum.S1.getCode());
    		for(MerchantChannelMapping merchantChannelMapping : merchantChannelMappings){
    			//下游上传商户号，支付方式，支付场景，支付通道默认标识(1为默认通道，0为非默认通道，非默认通道数据有多条)为缓存key存储商户支付渠道缓存信息
				redisCache.setCacheObject("channelMapping"+merchantChannelMapping.getMercNo()+merchantChannelMapping.getPaywayCode()+merchantChannelMapping.getSceneCode(),merchantChannelMapping);
    		}
    		
    		
    		//加载路由策略表
    		List<RouterPolicy> routerPolicys = routerPolicyMapper.selectAll();//获取路由策略表中的状态字段为启用状态的（ey）
    		for(RouterPolicy routerPolicy:routerPolicys){
				redisCache.setCacheObject("routerPolicys", routerPolicys);
    		}
    		
    		//加载渠道商户信息表
    		List<ExtMerchantInfo> extMerchantInfos = extMerchantInfoMapper.selectAll();
    		for(ExtMerchantInfo extMerchantInfo:extMerchantInfos){//渠道商户号作为缓存主键PAYING_MERC_NO,渠道商户对应id作为主键获取不了信息
				redisCache.setCacheObject(extMerchantInfo.getPayingMercNo(), extMerchantInfo);//渠道商户号
    		}


			log.info("手动加载业务缓存完成");
    		
    		return "手动刷新缓存成功！";
    	}catch(Exception e){
    		return "手动刷新缓存失败！"+e.getMessage();
    	}
    }



}
