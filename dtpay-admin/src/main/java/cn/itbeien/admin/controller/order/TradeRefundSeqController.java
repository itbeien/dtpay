package cn.itbeien.admin.controller.order;

import cn.itbeien.admin.service.order.ITradeRefundSeqService;
import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.trade.TradeRefundSeq;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tradeRefundSeq")
@Slf4j
public class TradeRefundSeqController extends BaseController {

	@Autowired
	private ITradeRefundSeqService tradeRefundSeqServiceImpl;
	/**
	 * 退款订单流水列表查询
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getList")
	public TableDataInfo getList() throws ParseException{
		List<Map<String, Object>> list = null;
		try {
			list = tradeRefundSeqServiceImpl.getList(null);
		} catch (Exception e) {
			log.error("退款订单流水列表查询", e);
		}
		return getDataTable(list);
	}
	
	/**
	 * 根据退款流水号查询实体
	 * @param refundTradeSeq
	 * @return
	 */
    @RequestMapping(value = "/getByRefundTradeSeq", method = RequestMethod.POST)
    public AjaxResult getByRefundTradeSeq(@RequestParam(value="refundTradeSeq",required=true) String refundTradeSeq) {
    	TradeRefundSeq tradeRefundSeq = null;
    	try {
			tradeRefundSeq = tradeRefundSeqServiceImpl.getByRefundTradeSeq(refundTradeSeq);
		} catch (Exception e) {
			log.error("根据退款流水号查询实体,退款流水号:",refundTradeSeq, e);
		}
        return AjaxResult.success(tradeRefundSeq);
    }
}
