package cn.itbeien.business.merchant.domain.easypay.parameter;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: https
 * @ClassName: PicInfoList
 * @Author: easypay
 * @Description: 图片收集
 * @Date: 2019/12/16 10:26
 */
public class PicInfoList {
    public static String picInfo(String picMode,String fileId){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("picMode",picMode);
        data.put("fileId",fileId);
        return JSON.toJSONString(data);
    }
}
