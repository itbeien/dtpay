package cn.itbeien.payment.service.genseq;

import cn.itbeien.common.util.DateUtils;
import cn.itbeien.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * 一次最大9999的ID生成
 * Copyright© 2025 itbeien
 */
@Service
@Slf4j
public class SeqService {
	public static AtomicInteger idv=new AtomicInteger(1);
	public static AtomicInteger seqv=new AtomicInteger(1);
	public static AtomicInteger tracev=new AtomicInteger(1);
	public static AtomicInteger batSeq=new AtomicInteger(1);
	public static DateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Value("${system.fint}")
	public int fint;//系统标识
	/**
	 * 生成唯一ID
	 * @return
	 */
	public  String getOrderId(){
		
		log.info("订单号的值fint为："+ fint);
		
		if(idv.intValue()>99999){
			idv=new AtomicInteger(1);
		}
		return sdf.format(new Date())+String.valueOf(fint)+(fint*1000000+idv.getAndIncrement());
	}
	
	/**
	 * 生成支付网关的订单号
	 * @return
	 */
	public String getSeq(){
		
		log.info("订单号的值fint为："+ fint);

		if(seqv.intValue()>99999){
			seqv=new AtomicInteger(1);
		}
		return sdf.format(new Date())+(fint*100000+seqv.getAndIncrement());
	}
	
	/**
	 * 生成上送到银行的流水号
	 * @return
	 */
	public String getTrace(){
		log.info("订单号的值fint为："+ fint);
		if(tracev.intValue()>999999){
			tracev=new AtomicInteger(1);
		}
		String sn = String.valueOf(tracev.getAndIncrement());
		
		return DateUtils.formatTime(new Date())+fint+(StringUtils.leftPadding(sn, 6, "0"));
	}
	
	/**
	 * 生成唯一batSeq
	 * @return
	 */
	public  String getBatSeq(){
		
		log.info("订单号的值fint为："+ fint);
		
		if(idv.intValue()>99999){
			batSeq=new AtomicInteger(1);
		}
		return sdf.format(new Date())+String.valueOf(fint)+(fint*1000000+batSeq.getAndIncrement());
	}
}
