package cn.itbeien.admin.controller.order;


import cn.itbeien.admin.service.order.ITradeOrderService;
import cn.itbeien.admin.vo.order.TradeOrderQryPar;
import cn.itbeien.common.controller.BaseController;
import cn.itbeien.common.entity.trade.TradeOrder;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public AjaxResult detail(@RequestParam("orderId") String orderId){
		TradeOrder tradeOrder = this.tradeOrderServiceImpl.selectByPrimaryKey(orderId);
		return success(tradeOrder);
	}
}
