package cn.itbeien.task.mapper.trade.tradeseq;

import cn.itbeien.common.entity.trade.TradeRefundSeq;
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
public interface TradeRefundSeqMapper {
    int deleteByPrimaryKey(String refundTradeSeq);

    int insert(TradeRefundSeq record);

    int insertSelective(TradeRefundSeq record);

    int updateByPrimaryKeySelective(TradeRefundSeq record);

    int updateByPrimaryKey(TradeRefundSeq record);
    
    /**
     * 退款订单流水列表查询
     * @param param
     * @return
     * @throws DataAccessException
     */
	public List<Map<String, Object>> getList(@Param("param") Map<String, Object> param);

	/**
	 * 根据退款流水号查询实体
	 * @param refundTradeSeq 退款流水号
	 * @return
	 * @throws DataAccessException
	 */
	public TradeRefundSeq selectByPrimaryKey(@Param("refundTradeSeq") String refundTradeSeq);
}