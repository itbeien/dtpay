package cn.itbeien.payment.controller;

import cn.itbeien.common.core.controller.AbstractBaseController;
import cn.itbeien.common.core.vo.TradeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@RestController
@Slf4j
public class TradeController extends AbstractBaseController {

    @RequestMapping(value = "/gateway/trade.do", method = RequestMethod.POST)
    public String trade() {
        log.info("下游请求参数：{}",getReqParamJSON());
        String jsonRes = "";
        TradeResponse tradeResponse = null;

        return jsonRes;
    }
}
