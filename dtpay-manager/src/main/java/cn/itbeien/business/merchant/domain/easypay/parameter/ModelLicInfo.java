package cn.itbeien.business.merchant.domain.easypay.parameter;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class ModelLicInfo {

    public static String getModelLic(String termModelLic,String materialcode){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("termModelLic",termModelLic);
        data.put("materialcode",materialcode);
        return JSON.toJSONString(data);
    }
}
