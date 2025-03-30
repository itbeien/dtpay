package cn.itbeien.admin.mapper.order;

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
	 * 根据订单号查询支付流水订单
	 * @return
	 * @throws DataAccessException
	 */
	public TradeOrderSeq selectOrderByOrderId(String orderId);
	
	/**
	 * 查询渠道是否存在交易
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getTradeOrderSeqList(Map<String, Object> param);
	
	/**
	 * 根据支付流水号查询实体(Map)
	 * @param tradeSeq 支付流水号
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String,Object> getMapByOrderId(@Param("tradeSeq") String tradeSeq);
}