package cn.itbeien.payment.mapper.trade;

import cn.itbeien.common.entity.trade.TradeOrderSeq;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

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
public interface TradeOrderSeqMapper {
    int deleteByPrimaryKey(String tradeSeq);

    int insert(TradeOrderSeq record);

    int insertSelective(TradeOrderSeq record);

//    TradeOrderSeq selectByPrimaryKey(String tradeSeq);

    int updateByPrimaryKeySelective(TradeOrderSeq record);

    int updateByPrimaryKey(TradeOrderSeq record);
    
    /**
     * 支付订单流水列表查询
     * @param param
     * @return
     * @throws DataAccessException
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);

	/**
	 * 根据支付流水号查询实体
	 * @param tradeSeq 支付流水号
	 * @return
	 * @throws DataAccessException
	 */
	public TradeOrderSeq selectByPrimaryKey(@Param("tradeSeq") String tradeSeq);
	
	
	public TradeOrderSeq selectByBankTradeSeq(@Param("bankTradeSeq") String bankTradeSeq);
	
	/**
	 * 查询支付成功订单的流水记录
	 * @param  平台订单号
	 * @return
	 * @throws DataAccessException
	 */
	public TradeOrderSeq selectOrderSuccessByOrderId(String orderId);
	
	
	/**
	 * 根据订单号查询支付流水订单
	 * @param  平台订单号
	 * @return
	 * @throws DataAccessException
	 */
	public TradeOrderSeq selectOrderByOrderId(String orderId);
	
	/**
	 * updateOrderByOrderId:(根据订单号 修改通知状态).  
	 *
	 * :2018年3月16日下午7:14:54
	 *
	 * @param orderId
	 * @param noticeStatus
	 * @return
	 */
	int updateOrderByOrderId(@Param("orderId") String orderId, @Param("noticeStatus") String noticeStatus);
}