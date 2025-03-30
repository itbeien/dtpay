package cn.itbeien.admin.controller.order;


import cn.itbeien.admin.service.order.ITradeOrderSeqService;
import cn.itbeien.auth.controller.BaseController;
import cn.itbeien.common.page.TableDataInfo;
import cn.itbeien.common.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
@RestController
@RequestMapping("/tradeOrderSeq")
@Slf4j
public class TradeOrderSeqController extends BaseController {

	@Autowired
	private ITradeOrderSeqService tradeOrderSeqServiceImpl;
	
	/**
	 * 支付订单流水列表查询
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getList")
	public TableDataInfo getList(){
		List<Map<String, Object>> bootTable = null;
		try {
			bootTable = tradeOrderSeqServiceImpl.getList(null);
		} catch (Exception e) {
			log.error("支付订单流水列表查询", e);
		}
		return getDataTable(bootTable);
	}
	
	/**
	 * 根据支付流水号查询实体
	 * @param tradeSeq
	 * @return
	 */
    @RequestMapping(value = "/getByTradeSeq", method = RequestMethod.POST)
    public AjaxResult getByTradeSeq(@RequestParam(value="tradeSeq",required=true) String tradeSeq) {
    	Map<String,Object> list = null;
    	try{
    		list = tradeOrderSeqServiceImpl.getMapByOrderId(tradeSeq);
		} catch (Exception e) {
			log.error("根据支付流水号查询实体,支付流水号:{},异常:{}",tradeSeq, e);
		}
        return AjaxResult.success(list);
    }
    
}
