package cn.itbeien.business.merchant.domain.easypay.parameter;

import cn.itbeien.business.merchant.domain.easypay.AccInfo;
import cn.itbeien.business.merchant.domain.easypay.ChannelMerchantInfo;
import cn.itbeien.business.util.MD5Util;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PublicFun {
    public static final String version ="1.0";
    public static final String clientCode = "48310323";  // 机构号

    public static final String merTrace="6604d8ec3e10af364a5cf933";




    public static final Map<String,String> getPublicMap(){
        Map<String, String > data =new HashMap<String, String>();
        data.put("version", version);//版本号
        data.put("clientCode", clientCode);
        return data;
    }

    //5.1交易信息查询
    public static Map<String , String> getTradeInfo(){
        Map data = new HashMap<String, String>();
        data.put("clientCode","48319999");
        data.put("version","1.0");
        data.put("messageType","QUERYTRADE");
        data.put("pepDate","20210707");
        data.put("termTermcode","W1489139");
        data.put("orderId","CpiN68lysKZRMnCxe2p1");
        String mac= getSign( "fudhsyrgnbpdeibh", data, "UTF-8");
        System.out.println("mac<<<<<<:" + mac);
        data.put("MAC", "93B69AAFD4CA9BD0435879693D0AF1A1");
        return data;
    }

    //5.2订单创建
    public static Map<String , String> getCreateOrder(){
        Map data = new HashMap<String, String>();
        data.put("clientCode","48319999");
        data.put("version","1.0");
        data.put("messageType","CREATEORDER");
        data.put("termMercode","931241680500001");
        data.put("termTermcode","39032505");
        data.put("orderId","21070214511280199408");
//        data.put("orderDesc","YS");
        data.put("tranamt","100");
        data.put("expDate","1");
        String mac= getSign( "fudhsyrgnbpdeibh", data, "UTF-8");
        System.out.println("mac<<<<<<:" + mac);
        data.put("MAC", mac);
        return data;
    }

    //5.3订单关闭
    public static Map<String , String> getCloseOrder(){
        Map data = new HashMap<String, String>();
        data.put("clientCode","48319999");
        data.put("version","1.0");
        data.put("messageType","CLOSEORDER");
        data.put("termMercode","931241680500001");
        data.put("termTermcode","39032505");
        data.put("orderId","21070214511280199408");
        String mac= getSign( "fudhsyrgnbpdeibh", data, "UTF-8");
        System.out.println("mac<<<<<<:" + mac);
        data.put("MAC", mac);
        return data;
    }

    //5.4订单拉取     -- 易生调用接口用于主动拉取订单信息
    public static Map<String , String> getOrder(){
        Map data = new HashMap<String, String>();
        data.put("clientCode","48319999");
        data.put("version","1.0");
        data.put("messageType","CLOSEORDER");
        data.put("termMercode","931241680500001");
        data.put("termTermcode","39032505");
        data.put("orderId","21070214511280199408");
        String mac= getSign( "fudhsyrgnbpdeibh", data, "UTF-8");
        System.out.println("mac<<<<<<:" + mac);
        data.put("MAC", mac);
        return data;
    }

    /**
     * 鉴权签名
     */
    public static String getSign(String key, Map<String, String> map, String encode) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {//空值不参与加密
                list.add(entry.getKey() + "=" + entry.getValue() + "&");// key=value+
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        try {
            System.out.println("md5之前：\n"+result);
            result = MD5Util.crypt(result, encode);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Encodes a string 2 MD5
     *
     * @param  str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
//    public static String crypt(String str, String encode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        if (str == null || str.length() == 0) {
//            throw new IllegalArgumentException("String to encript cannot be null or zero length");
//        }
//        StringBuffer hexString = new StringBuffer();
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(str.getBytes(encode));
//        byte[] hash = md.digest();
//        for (int i = 0; i < hash.length; i++) {
//            if ((0xff & hash[i]) < 0x10) {
//                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
//            } else {
//                hexString.append(Integer.toHexString(0xFF & hash[i]));
//            }
//        }
//        return hexString.toString().toUpperCase();
//    }

//    // 获取sign
//    public static String getSign(Map<String, String> map, String key) {
//        List<String> list = new ArrayList<String>();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            if (!StringUtil.isBlank(entry.getValue())) {
//                list.add(entry.getKey() + "=" + entry.getValue());// key=value
//            }
//        }
//        Collections.sort(list);
//        String str2 = "";
//        for (int i = 0; i < list.size(); i++) {
//            str2 += list.get(i) + "&";
//        }
//        str2 += "key=" + key;
//        System.out.println("getSign:<<<<" + str2);
//        str2 = MD5(str2);
//        return str2;
//    }
//    // md5加密
//    public static String MD5(String sourceStr) {
//        String result = "";
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(sourceStr.getBytes());
//            byte b[] = md.digest();
//            int i;
//            StringBuffer buf = new StringBuffer("");
//            for (int offset = 0; offset < b.length; offset++) {
//                i = b[offset];
//                if (i < 0)
//                    i += 256;
//                if (i < 16)
//                    buf.append("0");
//                buf.append(Integer.toHexString(i));
//            }
//            result = buf.toString();
//        } catch (NoSuchAlgorithmException e) {
//            System.out.println(e);
//        }
//        return result.toUpperCase();
//    }


//  -----------------------------------------------------------------------

    /**
     * 商户信息录入后，去商户管理中台-->商户审核，进行审核
     * @return
     */
    public static Map<String ,String >  getAGMERAPPLY(ChannelMerchantInfo merInfo, Map<String,String> picMap) {
        Map<String,String> data = getPublicMap();
        data.put("messageType","AGMERAPPLY");   //
        data.put("isContract","1");//isContract 字段 必须设置为 “1”
        data.put("merInfo", ParameterEncapsulation.getMerInfo(merInfo.getMerInfo()));   //商户信息
        data.put("plusInfo", ParameterEncapsulation.getPlusInfo(merInfo.getPlusInfo()));//附加信息
        data.put("licInfo", ParameterEncapsulation.getLicInfo(merInfo.getLicInfo(),merInfo.getMerInfo().getMerMode()));//营业执照信息
        data.put("accInfo", ParameterEncapsulation.getAccInfo(merInfo.getAccInfo()));//结算账户信息
        data.put("funcInfo", ParameterEncapsulation.getFuncInfo());//功能信息
        String merMode = merInfo.getMerInfo().getMerMode();
        String accType  = merInfo.getAccInfo().getAccType();
        data.put("picInfoList", ParameterEncapsulation.getPicInfoList(merMode,accType,picMap));//图片信息
        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(16));//操作流水
        return data;
    }

    // 5.3-商户信息补件
    public static Map<String ,String >  getAGENTPATCH(ChannelMerchantInfo merInfo) {
        Map<String,String> data = getPublicMap();
        data.put("messageType","AGENTPATCH");   //
        data.put("isContract","1");//isContract 字段 必须设置为 “1”
        data.put("merInfo", ParameterEncapsulation.getMerInfo(merInfo.getMerInfo()));   //商户信息
        data.put("plusInfo", ParameterEncapsulation.getPlusInfo(merInfo.getPlusInfo()));//附加信息
        //data.put("sysInfo", ParameterEncapsulation.getSysInfoList());//终端信息
        data.put("licInfo", ParameterEncapsulation.getLicInfo(merInfo.getLicInfo(),merInfo.getMerInfo().getMerMode()));//营业执照信息
        data.put("accInfo", ParameterEncapsulation.getAccInfo(merInfo.getAccInfo()));//结算账户信息
        data.put("funcInfo", ParameterEncapsulation.getFuncInfo());//功能信息
        String merMode = merInfo.getMerInfo().getMerMode();
        String accType  = merInfo.getAccInfo().getAccType();
        Map<String,String> picMap = new HashMap();
        data.put("picInfoList", ParameterEncapsulation.getPicInfoList(merMode,accType,picMap));//图片信息
        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水
        data.put("merTrace", merTrace);//商户唯一标识
        return data;
    }


    // 5.3-商户签约申请
    public static Map<String ,String >  getMerchantSigned(String merTrace) {
        Map<String,String> data = getPublicMap();
        data.put("messageType","CONTRACTAPPLY");
        data.put("merTrace", merTrace);//商户唯一标识
        return data;
    }

    // 商户信息变更
    public static Map<String ,String >  getALTERMER() {
       Map<String,String> data = getPublicMap();
        data.put("backUrl","http://stg1-mrs.wpgjcs.com/callback/easypay/notify/ALTERMER/00/11") ;   // O 回调地址
        data.put("messageType","ALTERMER");   //
        data.put("merTrace", merTrace);//商户唯一标识
        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水
        //data.put("sysInfo",ParameterEncapsulation.getSysInfoList());
        data.put("funcInfo",ParameterEncapsulation.getFuncInfo());
//        data.put("merInfo", ParameterEncapsulation.getMerInfo());   //商户信息
//        data.put("plusInfo", ParameterEncapsulation.getPlusInfo());//附加信息
//        data.put("licInfo", ParameterEncapsulation.getLicInfo());//营业执照信息
//        data.put("picInfoList", ParameterEncapsulation.getPicInfoList());//图片信息
      /*  data.put("picInfoList", "[{\"picMode\":\"01\",\"fileId\":\"61d692833e10af1ab25ed1b3\",\"picUrl\":\"http://testp-image.oss-cn-hangzhou.aliyuncs.com/app/files/1c69957c-db41-438c-a3cf-5cd1fc9074dc.png\"},{\"picMode\":\"02\",\"fileId\":\"61d6928e3e10af1ab25ed1bd\",\"picUrl\":\"http://testp-image.oss-cn-hangzhou.aliyuncs.com/app/files/96711511-331c-4bd6-86df-62e764200f36.png\"},{\"picMode\":\"05\",\"fileId\":\"61d6925f3e10af1ab25ed198\",\"picUrl\":\"http://testp-image.oss-cn-hangzhou.aliyuncs.com/app/files/5c4ec4cd-9ea9-49e5-ac86-8aa58119e5ec.png\"},{\"picMode\":\"06\",\"fileId\":\"61d692a83e10af1ab25ed1c1\",\"picUrl\":\"http://testp-image.oss-cn-hangzhou.aliyuncs.com/app/files/ea5da657-340e-46b4-bbf4-3b2b83eb321e.png\"},{\"picMode\":\"07\",\"fileId\":\"61d692a23e10af1ab25ed1bf\",\"picUrl\":\"http://testp-image.oss-cn-hangzhou.aliyuncs.com/app/files/efd78e10-b54b-46c8-b5a5-b14e55720382.png\"},{\"picMode\":\"08\",\"fileId\":\"61d692b03e10af1ab25ed1c3\",\"picUrl\":\"http://testp-image.oss-cn-hangzhou.aliyuncs.com/app/files/ea626547-cd4a-4068-9885-d65bfece02d1.png\"}]");*/

//        data.put("customInfo","");//自定义域
        return data;
    }
     public static Map<String ,String >  getADDSYSTERM() {
       Map<String,String> data = getPublicMap();
//        data.put("backUrl","") ;   // O 回调地址
         data.put("messageType","ADDSYSTERM");   //
         data.put("merTrace", merTrace);//商户唯一标识
         String operaTrace = data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水
         //data.put("sysInfo", ParameterEncapsulation.getSysInfoList());//终端信息



//        data.put("customInfo","");//自定义域
        return data;
    }
    public static Map<String ,String >  getALTERSYSTERM() {
       Map<String,String> data = getPublicMap();
//        data.put("backUrl","") ;   // O 回调地址
         data.put("messageType","ALTERSYSTERM");   //
         data.put("merTrace", merTrace);//商户唯一标识
         String operaTrace = data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水
         //data.put("sysInfo", ParameterEncapsulation.getSysInfoList());//终端信息
//        data.put("customInfo","");//自定义域
        return data;
    }
    public static Map<String ,String >  getALTERPAYACC(AccInfo accInfo) {
       Map<String,String> data = getPublicMap();
//        data.put("backUrl","") ;   // O 回调地址
        data.put("messageType","ALTERPAYACC");   //
        data.put("merTrace", merTrace);//商户唯一标识
        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水

        data.put("accInfo", ParameterEncapsulation.getAccInfo(accInfo));//结算账户信息
//        data.put("picInfoList", ParameterEncapsulation.getPicInfoList());//图片信息
//        data.put("customInfo","");//自定义域
        return data;
    }
public static Map<String ,String >  getALTERFUNC() {
       Map<String,String> data = getPublicMap();
//        data.put("backUrl","") ;   // O 回调地址
        data.put("messageType","ALTERFUNC");   //
        data.put("merTrace", merTrace);//商户唯一标识
//        data.put("merTrace","6314512b3e10af313fa4c572");//商户唯一标识
        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水
        data.put("funcInfo", ParameterEncapsulation.getFuncInfo());//功能信息
        //data.put("sysInfo", ParameterEncapsulation.getSysInfoList());//功能信息
//        data.put("customInfo","");//自定义域
        return data;
    }

    public static Map<String ,String >  getQUERYPARA() {
       Map<String,String> data = getPublicMap();
        data.put("messageType","QUERYPARA");   //
        data.put("paraType","01");
        data.put("paraName","联迪");
        return data;
    }
    public static Map<String ,String >  getSENDZFBORWX() {
       Map<String,String> data = getPublicMap();
//        data.put("backUrl","") ;   // O 回调地址
        data.put("messageType","SENDZFBORWX");   //
//        data.put("merTrace", merTrace);//商户唯一标识
        data.put("merTrace", "633554713e10af0a8c689c68");//商户唯一标识
        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(20));//操作流水
        data.put("operType","add");  // 操作类型  add-新增,update-修改；目前仅支持新增（add）
        data.put("tradeType","W");  //拆分类型  Z-支付宝；W-微信; Q-条码前置
//        data.put("isMini","0");  //是否小程序  0-否(默认)；1-是
        data.put("playType","unionPay");  //渠道类型  unionPay-银联；netPay-网联;Q1-条码微信；Q2-条码支付宝；Q3-条码云闪付
//        data.put("pubName","");  //上级渠道  	即公众号名称
//        data.put("partner","526509705");  //子商户号
//        data.put("appid","");  //
        return data;
    }


    // 5.5 商户审核结果查询
    public static Map<String ,String >  getQUERYAUDMER() {
       Map<String,String> data = getPublicMap();
        data.put("messageType","QUERYAUDMER");   //
//        data.put("merTrace", merTrace);//商户唯一标识    5e709b1f3e10af3f029d3bb1
        data.put("merTrace", merTrace);//商户唯一标识    5e709b1f3e10af3f029d3bb1
        data.put("operaTrace","qZEIAin4bu67pml0");//原操作流水号  LDIuSmqZzHdT6JAGvPQu  J2Z0IcWiMdyXwB1A47mp
//        data.put("customInfo","");//自定义域
        return data;
    }

    // 5.6 商户信息查询
    public static Map<String ,String >  getQUERYMER() {
       Map<String,String> data = getPublicMap();
        data.put("messageType","QUERYMER");   //
//        data.put("merTrace", merTrace);//商户唯一标识
        data.put("merTrace", merTrace);//商户唯一标识
//        data.put("customInfo","");//自定义域
        return data;
    }


//  ------------------------------------------------------------------------

    //5.14 查询授权码
    public static Map<String,String>  getQueryAuth(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","QUERYAUTH");
        data.put("merTrace","");               //商户唯一标识
        data.put("termMercode","");            //商户号
        data.put("termTermcode","");           //终端号
        return data;
    }

    //5.15 微信APPID配置
    public static Map<String,String>  getAddAppid(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALTERAPPID");
        data.put("wechatId","48310804");                 //子商户号
        data.put("appId","wx827fa93a3a23e76d");           //appId
        return data;
    }

    //5.16 微信授权目录配置
    public static Map<String,String>  getAddJSAPIPATH(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALTERAPPID");
        data.put("wechatId","451146365");                 //子商户号 appId
        data.put("appId","wxe9a85e037dc33f46");           //appId
        return data;
    }

    //5.17 微信配置查询
    public static Map<String,String>  getQueryWxConfig(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALTERAPPID");
        data.put("wechatId","");                          //子商户号
        data.put("appId","");                             //appId
        return data;
    }

    //5.18 微信商户简称及客服号码修改
    public static Map<String,String>  getAlterWxmer(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALTERAPPID");
        data.put("wechatId","");                          //子商户号
        data.put("merchantShortname","");                 //商户简称
        data.put("servicePhone","");                      //客服电话
        return data;
    }

    ///5.19 微信商户信息查询
    public static Map<String,String>  getQueryWxmer(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALTERAPPID");
        data.put("wechatId","");                          //子商户号
        return data;
    }

    //5.20 商户营销信息查询
    public static Map<String,String>  getQUERYREBATE(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","QUERYREBATE");
        data.put("merTrace", merTrace);  //商户唯一标识
        return data;
    }

    //5.21 商户营销信息新增 / 商户营销信息新增
    public static Map<String,String>  getINSERTMERREBATE(){
        Map<String,String> data = getPublicMap();
        data.put("messageType","MERREBATE");
        data.put("MAC","08785F14E91CD2B56424A53B5CE8A1BE");//MAC值
        data.put("merTrace", merTrace);  //商户唯一标识
        data.put("playType","insert");                    //操作类型
        data.put("rebateType","A001 ");                   //优惠类型
        data.put("rebateAmtHigh","100");                  //优惠额度
        data.put("rebateMode","0");                       //优惠方式
        data.put("rebateVal","10");                       //优惠值
        data.put("rebateBuss","");                        //优惠商业模型
        data.put("cardKind","");                          //卡类型
        data.put("bussType","");                          //业务类型
        data.put("limitPronum","");                       //笔数限制
//        data.put("limitDttype","");                     //时间范围
        data.put("limitHamt","");                         //每笔交易金额限制
        return data;
    }

    // 查询开户意愿确认状态
    public static Map<String, String> getAPPLYQUERYSTATE() {
        Map<String,String> data = getPublicMap();
        data.put("messageType","APPLYQUERYSTATE");
        data.put("version","1.0");
        data.put("clientCode",clientCode); // 机构号
//        data.put("clientCode","61165059"); // 机构号
        data.put("sub_mchid","542685889"); // 子商户号
        return data;
    }

    // 支付宝商家认证状态
    public static Map<String, String> getALIAUTHQUERY() {
        Map<String,String> data = getPublicMap();
        data.put("messageType","ALIAUTHQUERY");
        data.put("version","1.0");
//        data.put("clientCode",Profile.clientCode); // 机构号
        data.put("clientCode","61165068"); // 机构号
        data.put("alipayBody","{\"sub_merchant_id\": \"2088710285545942\"}"); // 子商户号
        return data;
    }

    public static Map<String, String> getADDTERMLIC() {
        Map<String,String> data = getPublicMap();
        data.put("messageType", "ADDTERMLIC");
        //data.put("termModelLicList", ParameterEncapsulation.getModelLicList());

//        物料编号 materialcode
//        data.put("operaTrace", RandomStringUtils.randomAlphanumeric(16));//操作流水
//        data.put("customInfo","");//自定义域

        return data;
    }
}
