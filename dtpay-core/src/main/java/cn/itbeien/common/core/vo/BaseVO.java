package cn.itbeien.common.core.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class BaseVO<T> implements Serializable {

    private JSONObject ext;

    //获取的时候设置默认值
    public JSONObject getExt() {
        return ext;
    }

    //设置扩展字段
    public BaseVO addExt(String key, Object val) {

        if(ext == null) {
            ext = new JSONObject();
        }
        ext.put(key,val);
        return this;
    }

    /** get ext value  可直接使用JSONObject对象的函数 **/
    public JSONObject extv() {
        return ext == null ? new JSONObject() : ext;
    }

}
