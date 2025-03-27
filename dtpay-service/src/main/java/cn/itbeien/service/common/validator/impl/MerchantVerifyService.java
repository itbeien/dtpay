package cn.itbeien.service.common.validator.impl;

import cn.itbeien.common.core.cache.RedisCacheUtils;
import cn.itbeien.common.core.enums.ApiEnum;
import cn.itbeien.common.core.enums.MercStatusEnum;
import cn.itbeien.common.core.vo.TradeRequest;
import cn.itbeien.common.exception.BusinessException;
import cn.itbeien.payment.dao.MerchantAccessRightMapper;
import cn.itbeien.service.common.validator.IMerchantVerifyService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
@Slf4j
public class MerchantVerifyService implements IMerchantVerifyService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private MerchantAccessRightMapper merchantAccessRightMapper;

    @Override
    public <T extends TradeRequest> T getTradeRequest(Map<String, Object> params) {
        JSONObject json = (JSONObject)JSONObject.toJSON(params);

        T tradeRequest = getTradeRequest(json);

        return tradeRequest;
    }

    /**
     * 校验商户是否有接口使用权限
     * @param map
     * @return
     */
    public void validateInterfaceCode(Map<String,Object> map){

        List<Map<String,Object>> maplist = merchantAccessRightMapper.getMapByMercNoAndInterface(map);
        if(maplist==null||maplist.size()==0){
            log.info("商户号{}对应接口{}",map.get("mercNo"),map.get("interfaceCode")+"未获授权");
            throw new BusinessException(map.get("interfaceCode")+ApiEnum.E50017.getMsg(),ApiEnum.E50017.getCode());
        }

        log.info("商户号{}对应接口{}",map.get("mercNo"),map.get("interfaceCode")+(MercStatusEnum.ENABLED.getCode().equals(maplist.get(0).get("status"))?"已获授权":"未获授权"));
        if(!MercStatusEnum.ENABLED.getCode().equals(maplist.get(0).get("status"))){
            throw new BusinessException(map.get("interfaceCode")+ApiEnum.E50017.getMsg(),ApiEnum.E50017.getCode());
        }
    }

}
