package cn.itbeien.service.common.validator;

import cn.itbeien.common.core.vo.TradeRequest;

import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IMerchantVerifyService {

    /**
     * 根据传入参数获取请求对象
     * @param params
     * @return
     */
    public <T extends TradeRequest> T getTradeRequest(Map<String, Object> params);


}
