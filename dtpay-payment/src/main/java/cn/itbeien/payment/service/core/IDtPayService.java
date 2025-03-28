package cn.itbeien.payment.service.core;

import cn.itbeien.common.entity.PlatPayDetail;
import cn.itbeien.common.entity.TradeRefundSeq;
import cn.itbeien.payment.service.channel.vo.*;
import cn.itbeien.payment.service.core.vo.request.PayQueryRequest;
import cn.itbeien.payment.service.core.vo.request.PayRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
public interface IDtPayService {
	/**
	 *
	 * 方法用途: 发起支付渠道支付接口调用
	 * @param
	 * @return PayBackBean 上游支付通道预支付接口返回数据，由具体支付渠道类把预支付返回结果适配为PayBackBean
	 * <br>
	 */
	public PayBackBean doPay(PayRequest payRequest);

	/**
	 * @param channelType 根据渠道类型适配解析上游数据报文
	 * 方法用途: 接收上游渠道异步支付结果通知<br>
	 */
	public PayInfoBean notifyInfo(HttpServletRequest request, String channelType);

	/**
	 *
	 * 方法用途:主动查询上游支付订单结果 <br>
	 * @param payQueryRequest 上游支付查询所需参数
	 * 渠道商户号 PAYING_MERC_NO 设置渠道商户号供上游查询时使用
	 * @return PayOrderQerBean 上游支付查询返回结果
	 */
	public PayOrderQerBean doPayQuery(PayQueryRequest payQueryRequest);


	/**
	 * 商户退款处理
	 * @param tradeRefundSeq
	 * @return
	 */
	public ChnRefundResponse doRefund(TradeRefundSeq tradeRefundSeq);

	/**
	 * 商户退款查询
	 * @param tradeRefundSeq
	 * @return
	 */
	public ChnRefundQueryResponse doRefundQuery(TradeRefundSeq tradeRefundSeq);


	/**
	 * 商户代付处理
	 * @param platPayDetail
	 * @return
	 */
	public ChnPayForResponse doPayFor(PlatPayDetail platPayDetail);

	/**
	 * 商户批量代付处理
	 * @param platPayDetailList
	 * @return
	 */
	public ChnBatchPayForResponse doBatchPayFor(List<PlatPayDetail> platPayDetailList);


	/**
	 * @param channelType 根据渠道类型适配解析上游数据报文
	 * 方法用途: 接收上游渠道代付异步支付结果通知<br>
	 */
	public ChnPayForNotifyResponse payForNotify(HttpServletRequest request, String channelType);

	/**
	 * batchPayForNotify:(接收上游渠道批量代付异步支付结果通知).
	 * @param request
	 * @param channelType
	 */
	public ChnBatchPayForNotifyResponse batchPayForNotify(HttpServletRequest request, String channelType);

	/**
	 * 商户代付结果查询
	 * @param platPayDetail
	 * @return
	 */
	public ChnPayForResponse doPayForQuery(PlatPayDetail platPayDetail);

	/**
	 * 商户余额查询
	 * @param chnAvalBalQueryRequest
	 * @return
	 */
	public ChnAvalBalQueryResponse doAvalBalQuery(ChnAvalBalQueryRequest chnAvalBalQueryRequest);
}
