package cn.itbeien.common.core.controller;

import cn.itbeien.common.core.beans.BaseRequestBean;
import cn.itbeien.common.core.vo.BaseVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 所有支付渠道类全部实现此接口,具体的支付渠道实现类放在cn/itbeien/payment/channel/xxpay/
 * Copyright© 2024 itbeien
 */
@Slf4j
public abstract class AbstractBaseController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Resource
    protected BaseRequestBean requestBean;


    /**
     * 获取json格式的请求参数
     **/
    protected JSONObject getReqParamJSON() {
        JSONObject object = requestBean.getReqParamJSON();
        log.info("request params body {}", object);
        return object;
    }

    /**
     * 获取对象类型
     **/
    protected <T> T getObject(Class<T> clazz) {
        JSONObject params = getReqParamJSON();
        T result = params.toJavaObject(clazz);

        if (result instanceof BaseVO) {
            //如果属于BaseVO, 处理扩展参数
            JSONObject resultTemp = (JSONObject) JSON.toJSON(result);
            for (Map.Entry<String, Object> entry : params.entrySet()) {  //遍历原始参数
                if(!resultTemp.containsKey(entry.getKey())){
                    ((BaseVO) result).addExt(entry.getKey(), entry.getValue());
                }
            }
        }
        return result;
    }

}
