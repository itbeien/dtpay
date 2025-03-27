package cn.itbeien.business.merchant.domain.easypay.parameter;

import cn.itbeien.business.util.HttpClient;
import cn.itbeien.business.util.MD5Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @ProjectName: https
 * @ClassName: FuncInfo
 * @Author: easypay
 * @Description:
 * @Date: 2019/12/16 10:00
 *
 */
public class FuncInfo {
    private static final String key="eGGlh0NHurHFNwHB";

    //          1）支付宝
    public static String alipay(){
       JSONObject alipayData = new JSONObject();
        alipayData.put("funcId",2);  //功能ID：2
        alipayData.put("calcVal","0.38");  //扣率
//        alipayData.put("brandName","");  //品牌商户名称
        return JSON.toJSONString(alipayData);
    };

    //          2）微信
    public static String weChat(){
        JSONObject weChatData = new JSONObject();
        weChatData.put("funcId",3);   //功能ID:3
        weChatData.put("calcVal","0.38");   //扣率
        return weChatData.toString();
    }

    //          3）实时入账
    public static String realtime(){
        JSONObject realtimeData = new JSONObject();
        realtimeData.put("funcId",25);   //功能ID:3
        realtimeData.put("tradeType","0");   //交易类型：0-全部；1-银行卡；2-条码支付
        realtimeData.put("DCalcType","1");   //借记卡计费类型:0-按笔数（单位：元）;1-按比例（单位：%）
        realtimeData.put("DCalcVal","0.01");   //扣率的单位默认按比例（单位%）
        realtimeData.put("CCalcType","1");   //信用卡计费类型:0-按笔数（单位：元）;1-按比例（单位：%）
        realtimeData.put("CCalcVal","0.01");   //信用卡扣率
        return realtimeData.toString();
    }

    //4）银联二维码
    public  static String unionpay(){
        JSONObject unionpayData = new JSONObject();
        unionpayData.put("funcId",12);   //功能ID:3
        unionpayData.put("DStlmType","1");   //借记卡扣率方式 :1-封顶;0-不封顶
        unionpayData.put("DCalcVal","0.6");   //借记卡扣率
        unionpayData.put("DStlmMaxAmt","30");   //借记卡封顶金额
        unionpayData.put("DFeeLowLimit","0");   //借记卡手续费最低值
        unionpayData.put("CCalcVal","0.6");   //信用卡扣率
        unionpayData.put("CFeeLowLimit","0");   //	信用卡手续费最低值
        Map<String, String> tMap = new HashMap<String, String>();
        // JSONObject 转 map
        Iterator it = unionpayData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            tMap.put(entry.getKey(), entry.getValue());
        }

        System.out.println(tMap);
        return unionpayData.toString();
    }

    //          5）立招
    public static String standfor(){
        JSONObject standforData = new JSONObject();
        standforData.put("funcId",13);   //功能ID:13
        standforData.put("twoBars",getTwoBars());
        return standforData.toString();
    }
    public static String getTwoBars(){
        List list = new ArrayList();
        JSONObject json = new JSONObject();
        json.put("twoBarCode","Q1558665234412");
        json.put("termName","hahah码牌1");
        list.add(json);
        return list.toString();
    }


    //          6）银联营销业务
    public static String unionpayMarketing(){
        JSONObject unionpayMarketingData = new JSONObject();
        unionpayMarketingData.put("funcId",14);   //功能ID:14
        unionpayMarketingData.put("DStlmType","1");   //借记卡扣率方式 :1-封顶;0-不封顶
        unionpayMarketingData.put("DCalcVal","0.35");   //借记卡扣率
        unionpayMarketingData.put("DStlmMaxAmt","20");   //借记卡封顶金额
        unionpayMarketingData.put("DFeeLowLimit","3.13");   //借记卡手续费最低值
        unionpayMarketingData.put("CCalcVal","0.35");   //信用卡扣率
        unionpayMarketingData.put("CFeeLowLimit","33.33");   //2	信用卡手续费最低值
        return unionpayMarketingData.toString();
    }


    //          7）银联小微二维码
    public static String unionpaySmall(){
        JSONObject unionpaySmallData = new JSONObject();
        unionpaySmallData.put("funcId",16);   //功能ID:16
//        unionpaySmallData.put("termMsg",getTermMsg());   //功能ID:16
        unionpaySmallData.put("DStlmType","1");   //借记卡扣率方式 :1-封顶;0-不封顶
        unionpaySmallData.put("DCalcVal","0.1");   //	借记卡扣率
        unionpaySmallData.put("DStlmMaxAmt","20");   //	借记卡封顶金额
        unionpaySmallData.put("DFeeLowLimit","10");   //	借记卡手续费最低值
        unionpaySmallData.put("CCalcVal","5");   //	信用卡扣率
        unionpaySmallData.put("CFeeLowLimit","100");   //	信用卡手续费最低值
        return unionpaySmallData.toString();
    }
    public static String getTermMsg(){
        List list = new ArrayList();
        JSONObject json = new JSONObject();
        json.put("unionPayQRCode","123456789");  //聚合码编号
        json.put("unionPayName","***********");   //二维码名称
        list.add(json);
        return list.toString();
    }


    //          8）银行卡收单
    public static String unionpayCard (){
        JSONObject unionpayCardData = new JSONObject();
        unionpayCardData.put("funcId",23);   //功能ID:3
        unionpayCardData.put("DStlmType","1");   //借记卡扣率方式 :1-封顶;0-不封顶
        unionpayCardData.put("DCalcVal","0.5");   //借记卡扣率
        unionpayCardData.put("DStlmMaxAmt","30");   //借记卡封顶金额
        unionpayCardData.put("DFeeLowLimit","0");   //借记卡手续费最低值
        unionpayCardData.put("CCalcVal","0.6");   //信用卡扣率
        unionpayCardData.put("CFeeLowLimit","0");   //信用卡手续费最低值
        return unionpayCardData.toString();

    }  //          8）D1增值服务费
    public static String valueAdded (){
        JSONObject unionpayCardData = new JSONObject();
        unionpayCardData.put("funcId",10);   //功能ID:3
        unionpayCardData.put("calcType","1");   //借计算方式 :1-按比例计算(单位：%);0-按笔计算(单位：元)
        unionpayCardData.put("calcVal","0.5");   //	手续费
        unionpayCardData.put("payDateType","0");   //费用处理模式：0-每天计费；1-节假日；2-节假日按天

        return unionpayCardData.toString();

    }

    public static void main(String[] args) {
        System.out.println(unionpay());
        Map<String, String > data =new HashMap<String, String>();
        data.put("picInfoList", "[{\"picMode\":\"01\",\"fileId\":\"61bfe0601faf202cd074a4ad\"},{\"picMode\":\"02\",\"fileId\":\"61bfe0613e10af4ed7e6f38f\"},{\"picMode\":\"03\",\"fileId\":\"61bfe0633e10af4ed7e6f390\"},{\"picMode\":\"04\",\"fileId\":\"61bfe0641faf202cd074a4b4\"},{\"picMode\":\"06\",\"fileId\":\"61bfe0663e10af4ed7e6f39a\"},{\"picMode\":\"07\",\"fileId\":\"61bfe0681faf202cd074a4bd\"},{\"picMode\":\"08\",\"fileId\":\"61bfe06a3e10af4ed7e6f3a6\"},{\"picMode\":\"09\",\"fileId\":\"61bfe06d1faf202cd074a4bf\"},{\"picMode\":\"10\",\"fileId\":\"61bfe06f3e10af4ed7e6f3a9\"},{\"picMode\":\"11\",\"fileId\":\"61bfe0701faf202cd074a4c8\"},{\"picMode\":\"14\",\"fileId\":\"61bfe0711faf202cd074a4ca\"},{\"picMode\":\"15\",\"fileId\":\"61bfe0723e10af4ed7e6f3ae\"}]");
        data.put("merInfo", "{\"merArea\":\"1023\",\"businEndtime\":\"2359\",\"stlmType\":\"1\",\"businBegtime\":\"0000\",\"employeeNum\":\"1\",\"merMode\":\"2\",\"businName\":\"安贞便利店\",\"merName\":\"商户_剧卓\",\"merType\":\"5331\",\"businForm\":\"02\",\"merAddr\":\"北京市北京市朝阳区长新大厦\",\"standardFlag\":\"0\"}");
        data.put("backUrl", "https://test.com");
        data.put("funcInfo", "[{\"funcId\":2,\"calcVal\":\"0.36\"}, {\"funcId\":3,\"calcVal\":\"0.36\"}, {\"funcId\":25,\"tradeType\":\"0\",\"DCalcType\":\"1\",\"DCalcVal\":\"0.00\",\"CCalcType\":\"1\",\"CCalcVal\":\"0.00\"}, {\"funcId\":12,\"DStlmType\":\"1\",\"DCalcVal\":\"0.55\",\"DStlmMaxAmt\":\"30\",\"DFeeLowLimit\":\"0\",\"CCalcVal\":\"0.55\",\"CFeeLowLimit\":\"0\"}, {\"funcId\":14,\"DStlmType\":\"0\",\"DCalcVal\":\"0.36\",\"DStlmMaxAmt\":\"30\",\"DFeeLowLimit\":\"0\",\"CCalcVal\":\"0.36\",\"CFeeLowLimit\":\"0\"}]");
        data.put("plusInfo", "{\"legalPhone\":\"15900000000\",\"merLegal\":\"剧卓\",\"legalType\":\"0\",\"linkmanCode\":\"130182199303073558\",\"linkmanValidity\":\"[\\\"2021.04.25\\\",\\\"2041.04.25\\\"]\",\"legalCode\":\"130182199303073558\",\"linkmanType\":\"0\",\"legalValidity\":\"[\\\"2021.04.25\\\",\\\"2041.04.25\\\"]\",\"linkmanPhone\":\"15900000000\",\"linkMan\":\"剧卓\"}");
        data.put("version", "1.0");
        data.put("accInfo", "{\"bankCode\":\"105100021060\",\"legalType\":\"0\",\"accName\":\"剧卓\",\"legalCode\":\"130182199303073558\",\"bankName\":\"中国建设银行股份有限公司北京西三旗支行\",\"accType\":\"00\",\"account\":\"6217000010144854901\",\"accPhone\":\"15933210586\"}");
        data.put("operaTrace", "1");
        data.put("messageType", "AGMERAPPLY");
        data.put("clientCode", "48310323");
        data.put("licInfo", "{\"businScope\":\"收钱\",\"controlerLegalType\":\"0\",\"capital\":\"10\",\"capitalCurrency\":\"人民币\",\"controlerName\":\"剧卓\",\"licAddr\":\"北京市北京市朝阳区长新大厦\",\"controlerLegalValidity\":\"[\\\"2021.04.25\\\",\\\"2041.04.25\\\"]\",\"controlerLegalCode\":\"130182199303073558\"}");

        String MD5String = MD5Util.getSign(key,data,"UTF-8");
        data.put("MAC",MD5String);

        String url = "https://mtest.eycard.cn:4443/AG_MerchantManagementSystem_Core/agent/api/gen";
        try{
            String response= HttpClient.postBody(url ,data,"application/x-www-form-urlencoded");
            System.out.println("响应数据:"+response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
