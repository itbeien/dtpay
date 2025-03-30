package cn.itbeien.admin.controller.order;


import cn.itbeien.admin.service.order.ITradeOrderService;
import cn.itbeien.admin.vo.order.TradeOrderQryPar;
import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.page.TableDataInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/tradeorder")
@Slf4j
public class TradeOrderController extends BaseController {
	
	@Autowired
	private ITradeOrderService tradeOrderServiceImpl;

	
	@RequestMapping("/list")
	public TableDataInfo list(){
		List<TradeOrder> tradeOrderList = tradeOrderServiceImpl.qryTradeOrderByPage(null);
		return getDataTable(tradeOrderList);
	}


	/**
	 * 获取总额及总笔数
	 */
	@RequestMapping("/sumAmtAndCount")
	public Object sumAmtAndCount(TradeOrderQryPar tradeOrderQryPar){
		Map<String,Object>  map = null;
		try{
			map = this.tradeOrderServiceImpl.sumAmtAndCount(tradeOrderQryPar);
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 导出商户信息
	 * @return
	 */
	@GetMapping("/export")
	public ResponseEntity<byte[]> export(){
		return null;
	}
	
	
	/**
	 * 
	 * 方法用途:查看详情 <br>
	 * @return
	 */
	@RequestMapping("/detail")
	public Object detail(@RequestParam("orderId") String orderId){
		TradeOrder tradeOrder = this.tradeOrderServiceImpl.selectByPrimaryKey(orderId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeOrder", tradeOrder);
		return map;
	}
}
