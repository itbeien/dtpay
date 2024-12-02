package cn.itbeien.business.merchant.domain.easypay;


import cn.itbeien.business.merchant.domain.easypay.parameter.PublicFun;
import cn.itbeien.business.util.HttpClient;
import cn.itbeien.business.util.MD5Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static cn.itbeien.business.merchant.domain.easypay.parameter.PublicFun.getPublicMap;


public class RunApp {
    private static final Logger log = LoggerFactory.getLogger(RunApp.class);
    private static final String key="eGGlh0NHurHFNwHB";
    public static final String URL= "https://mtest.eycard.cn:4443/AG_MerchantManagementSystem_Core/agent/api/gen";

    public static void main(String [] args){


//        getSign();

//        doPost2();

           //doAGMERAPPLY();

           doPost(PublicFun.getAGMERAPPLY(null,null),URL);     //5.2-商户信息录入后，去商户管理中台-->商户审核，进行审核
          //doPost(PublicFun.getAGENTPATCH());     //5.3-商户信息补件
         // doPost(PublicFun.getMerchantSigned());     //5.3-商户签约申请
          // doPost(PublicFun.getQUERYAUDMER());    //5.5 商户审核结果查询
          // doPost(PublicFun.getQUERYMER());       //5.6 商户信息查

//        doPost(PublicFun.getALTERMER());       //5.7 商户信息变更
//        doPost(PublicFun.getADDSYSTERM());     //5.8 新增终端
//        doPost(PublicFun.getALTERSYSTERM());   //5.9 终端变更
//        doPost(PublicFun.getALTERPAYACC());    //5.10 结算账户变更
//        doPost(PublicFun.getALTERFUNC());      //5.11 商户功能变更
//        doPost(PublicFun.getQUERYPARA());      //5.12 参数查询
//        doPost(PublicFun.getSENDZFBORWX());    //5.13 微信支付宝拆分

//        -------------
//        doPost(PublicFun.getQueryAuth());      //5.14 查询授权码
//        doPost(PublicFun.getAddAppid());       //5.15 微信APPID配置
//        doPost(PublicFun.getAddJSAPIPATH());   //5.16 微信授权目录配置
//        doPost(PublicFun.getQueryWxConfig());  //5.17 微信配置查询
//        doPost(PublicFun.getAlterWxmer());     //5.18 微信商户简称及客服号码修改
//        doPost(PublicFun.getQueryWxmer());     //5.19 微信商户信息查询

//        -------------
//        doPost(PublicFun.getQUERYREBATE());       //5.20 商户营销信息查询
//        doPost(PublicFun.getINSERTMERREBATE());   //5.21 商户营销信息新增

//         订单服务系统
//        -------------
//        doPost(PublicFun.getTradeInfo());    // 5.1 交易信息查询
//        doPost(PublicFun.getCreateOrder());  // 5.2 订单创建
//        doPost(PublicFun.getCloseOrder());   // 5.3 订单关闭
//        doPost(PublicFun.getOrder());        // 5.4 订单拉取   -- 易生调用接口用于主动拉取订单信息

        //doPost(PublicFun.getAPPLYQUERYSTATE());  // 查询开户意愿确认状态 (微信)

//        doPost(PublicFun.getALIAUTHQUERY());  //  商家认证状态(支付宝)

//        doPost(PublicFun.getADDTERMLIC());        //机具序列号入库


    }


    public static String doPost(Map<String ,String> data,String url){
        String MD5String = MD5Util.getSign(key,data,"UTF-8");
        data.put("MAC",MD5String);
        log.info("\n请求地址：" + url + "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【向易生请求的报文】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + JSON.toJSONString(data));
        String response = null;
        try {
            response = HttpClient.postBody(url,data,"application/x-www-form-urlencoded");
            log.info("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<【易生返回的报文】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"+response);

        } catch (Exception e) {
            log.error("请求易生接口异常:{}",e);
        }
        return response;
    }


    public static void getSign(){
        // 解析 map
        String ff = "{\n" +
                "    \"picInfoList\": \"[{\\\"picMode\\\":\\\"01\\\",\\\"fileId\\\":\\\"61bfe0601faf202cd074a4ad\\\"},{\\\"picMode\\\":\\\"02\\\",\\\"fileId\\\":\\\"61bfe0613e10af4ed7e6f38f\\\"},{\\\"picMode\\\":\\\"03\\\",\\\"fileId\\\":\\\"61bfe0633e10af4ed7e6f390\\\"},{\\\"picMode\\\":\\\"04\\\",\\\"fileId\\\":\\\"61bfe0641faf202cd074a4b4\\\"},{\\\"picMode\\\":\\\"06\\\",\\\"fileId\\\":\\\"61bfe0663e10af4ed7e6f39a\\\"},{\\\"picMode\\\":\\\"07\\\",\\\"fileId\\\":\\\"61bfe0681faf202cd074a4bd\\\"},{\\\"picMode\\\":\\\"08\\\",\\\"fileId\\\":\\\"61bfe06a3e10af4ed7e6f3a6\\\"},{\\\"picMode\\\":\\\"09\\\",\\\"fileId\\\":\\\"61bfe06d1faf202cd074a4bf\\\"},{\\\"picMode\\\":\\\"10\\\",\\\"fileId\\\":\\\"61bfe06f3e10af4ed7e6f3a9\\\"},{\\\"picMode\\\":\\\"11\\\",\\\"fileId\\\":\\\"61bfe0701faf202cd074a4c8\\\"},{\\\"picMode\\\":\\\"14\\\",\\\"fileId\\\":\\\"61bfe0711faf202cd074a4ca\\\"},{\\\"picMode\\\":\\\"15\\\",\\\"fileId\\\":\\\"61bfe0723e10af4ed7e6f3ae\\\"}]\",\n" +
                "    \"merInfo\": \"{\\\"merArea\\\":\\\"1023\\\",\\\"businEndtime\\\":\\\"2359\\\",\\\"stlmType\\\":\\\"1\\\",\\\"businBegtime\\\":\\\"0000\\\",\\\"employeeNum\\\":\\\"1\\\",\\\"merMode\\\":\\\"2\\\",\\\"businName\\\":\\\"安贞便利店\\\",\\\"merName\\\":\\\"商户_剧卓\\\",\\\"merType\\\":\\\"5331\\\",\\\"businForm\\\":\\\"02\\\",\\\"merAddr\\\":\\\"北京市北京市朝阳区长新大厦\\\",\\\"standardFlag\\\":\\\"0\\\"}\",\n" +
                "    \"operaTrace\": \"16399647872558BG3\",\n" +
                "    \"messageType\": \"AGMERAPPLY\",\n" +
                "    \"backUrl\": \"https://zmss.apnpay.com/receiverapi/receviver/customer/notice\",\n" +
                "    \"clientCode\": \"63754675\",\n" +
                "    \"licInfo\": \"{\\\"businScope\\\":\\\"收钱\\\",\\\"controlerLegalType\\\":\\\"0\\\",\\\"capital\\\":\\\"10\\\",\\\"capitalCurrency\\\":\\\"人民币\\\",\\\"controlerName\\\":\\\"剧卓\\\",\\\"licAddr\\\":\\\"北京市北京市朝阳区长新大厦\\\",\\\"controlerLegalValidity\\\":\\\"[\\\\\\\"2021.04.25\\\\\\\",\\\\\\\"2041.04.25\\\\\\\"]\\\",\\\"controlerLegalCode\\\":\\\"130182199303073558\\\"}\",\n" +
                "    \"funcInfo\": \"[{\\\"funcId\\\":2,\\\"calcVal\\\":\\\"0.36\\\"}, {\\\"funcId\\\":3,\\\"calcVal\\\":\\\"0.36\\\"}, {\\\"funcId\\\":25,\\\"tradeType\\\":\\\"0\\\",\\\"DCalcType\\\":\\\"1\\\",\\\"DCalcVal\\\":\\\"0.00\\\",\\\"CCalcType\\\":\\\"1\\\",\\\"CCalcVal\\\":\\\"0.00\\\"}, {\\\"funcId\\\":12,\\\"DStlmType\\\":\\\"1\\\",\\\"DCalcVal\\\":\\\"0.55\\\",\\\"DStlmMaxAmt\\\":\\\"30\\\",\\\"DFeeLowLimit\\\":\\\"0\\\",\\\"CCalcVal\\\":\\\"0.55\\\",\\\"CFeeLowLimit\\\":\\\"0\\\"}, {\\\"funcId\\\":14,\\\"DStlmType\\\":\\\"0\\\",\\\"DCalcVal\\\":\\\"0.36\\\",\\\"DStlmMaxAmt\\\":\\\"30\\\",\\\"DFeeLowLimit\\\":\\\"0\\\",\\\"CCalcVal\\\":\\\"0.36\\\",\\\"CFeeLowLimit\\\":\\\"0\\\"}]\",\n" +
                "    \"plusInfo\": \"{\\\"legalPhone\\\":\\\"15900000000\\\",\\\"merLegal\\\":\\\"剧卓\\\",\\\"legalType\\\":\\\"0\\\",\\\"linkmanCode\\\":\\\"130182199303073558\\\",\\\"linkmanValidity\\\":\\\"[\\\\\\\"2021.04.25\\\\\\\",\\\\\\\"2041.04.25\\\\\\\"]\\\",\\\"legalCode\\\":\\\"130182199303073558\\\",\\\"linkmanType\\\":\\\"0\\\",\\\"legalValidity\\\":\\\"[\\\\\\\"2021.04.25\\\\\\\",\\\\\\\"2041.04.25\\\\\\\"]\\\",\\\"linkmanPhone\\\":\\\"15900000000\\\",\\\"linkMan\\\":\\\"剧卓\\\"}\",\n" +
                "    \"version\": \"1.0\",\n" +
                "    \"accInfo\": \"{\\\"bankCode\\\":\\\"105100021060\\\",\\\"legalType\\\":\\\"0\\\",\\\"accName\\\":\\\"剧卓\\\",\\\"legalCode\\\":\\\"130182199303073558\\\",\\\"bankName\\\":\\\"中国建设银行股份有限公司北京西三旗支行\\\",\\\"accType\\\":\\\"00\\\",\\\"account\\\":\\\"6217000010144854901\\\",\\\"accPhone\\\":\\\"15933210586\\\"}\"\n" +
                "}";
        JSONObject json_object = JSONObject.parseObject(ff);

        Map<String, String> tMap = new HashMap<String, String>();
        // JSONObject 转 map
        Iterator it = json_object.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            tMap.put(entry.getKey(), entry.getValue());
        }

        System.out.println(tMap);

        String MAC = MD5Util.getSign(key, tMap, "UTF-8");

        System.out.println("===============================MAC值=====================" + "\n" + MAC);

    }

    public static void doAGMERAPPLY(){
        Map<String, String > data =new HashMap<String, String>();
        data.put("picInfoList", "[{\"picMode\":\"01\",\"fileId\":\"61bfe0601faf202cd074a4ad\"},{\"picMode\":\"02\",\"fileId\":\"61bfe0613e10af4ed7e6f38f\"},{\"picMode\":\"03\",\"fileId\":\"61bfe0633e10af4ed7e6f390\"},{\"picMode\":\"04\",\"fileId\":\"61bfe0641faf202cd074a4b4\"},{\"picMode\":\"06\",\"fileId\":\"61bfe0663e10af4ed7e6f39a\"},{\"picMode\":\"07\",\"fileId\":\"61bfe0681faf202cd074a4bd\"},{\"picMode\":\"08\",\"fileId\":\"61bfe06a3e10af4ed7e6f3a6\"},{\"picMode\":\"09\",\"fileId\":\"61bfe06d1faf202cd074a4bf\"},{\"picMode\":\"10\",\"fileId\":\"61bfe06f3e10af4ed7e6f3a9\"},{\"picMode\":\"11\",\"fileId\":\"61bfe0701faf202cd074a4c8\"},{\"picMode\":\"14\",\"fileId\":\"61bfe0711faf202cd074a4ca\"},{\"picMode\":\"15\",\"fileId\":\"61bfe0723e10af4ed7e6f3ae\"}]");
        data.put("merInfo", "{\"merArea\":\"1023\",\"businEndtime\":\"2359\",\"stlmType\":\"1\",\"businBegtime\":\"0000\",\"employeeNum\":\"1\",\"merMode\":\"2\",\"businName\":\"安贞便利店\",\"merName\":\"商户_剧卓\",\"merType\":\"5331\",\"businForm\":\"02\",\"merAddr\":\"北京市北京市朝阳区长新大厦\",\"standardFlag\":\"0\"}");
        data.put("backUrl", "https://zmss.apnpay.com/receiverapi/receviver/customer/notice");
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


        log.info("\n请求地址：" + url + "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【向易生请求的报文】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + JSON.toJSONString(data));
        try {
            String response= HttpClient.postBody(url ,data,"application/x-www-form-urlencoded");
            log.info("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<【易生返回的报文】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doPost2(){


        Map<String, String > data =new HashMap<String, String>();
        data.put("version", "1.0");
        data.put("clientCode", "50310178");
        data.put("bankName", "ICBC_DEBIT");
        data.put("accType", "10");
        data.put("bankCode", "10010");
        data.put("accNo", "6225882129703322");
        data.put("accName", "accName");
        data.put("businLic", "38292032034094321");
        data.put("businName", "jlxy");
        data.put("picInfo", "[{\"picMode\":\"01\",\"fileid\":\"61e4e5be3e10af7f5e60d1ef\"}]");


        String MD5String = MD5Util.getSign(key,data,"UTF-8");
        data.put("MAC",MD5String);


        String url = "https://mtest.eycard.cn:4443/AG_MerchantManagementSystem_Core/jzt/api/ent";


        log.info("\n请求地址：" + url + "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【向易生请求的报文】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + JSON.toJSONString(data));
        try {
            String response= HttpClient.postBody(url ,data,"multipart/form-data");
            log.info("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<【易生返回的报文】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"+response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getALIAUTHQUERY() {
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALIAUTHQUERY");
        data.put("version","1.0");
//        data.put("clientCode",Profile.clientCode); // 机构号
        data.put("clientCode","48310323"); // 机构号
        data.put("alipayBody","{\n" +
                "\"sub_merchant_id\": \"2088510181002323\"\n" +
                "}"); // 子商户号
        return data;
    }
}
