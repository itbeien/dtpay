package cn.itbeien.business.merchant.domain.easypay.parameter;

import cn.itbeien.business.enums.AccTypeEnum;
import cn.itbeien.business.enums.MerModeEnum;
import cn.itbeien.business.merchant.domain.easypay.AccInfo;
import cn.itbeien.business.merchant.domain.easypay.LicInfo;
import cn.itbeien.business.merchant.domain.easypay.MerInfo;
import cn.itbeien.business.merchant.domain.easypay.PlusInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParameterEncapsulation {

    /*
    * 商户信息
    * 描述：该字段为商户基本信息，JSON格式字符串，商户变更的时候，可以只传需要变更的字段
    * */
    public static String getMerInfo(MerInfo merInfo){
        JSONObject data = new JSONObject();
        data.put("merMode",merInfo.getMerMode());   //商户类型（0-企业 1-个体户 2-个人）
        data.put("merName", merInfo.getMerName());   //注册名称
        data.put("businName", merInfo.getBusinName());   //经营名称 (店名)
        data.put("merType",merInfo.getMerType());   //商户类型（MCC），银联定义的MCC编码
        data.put("standardFlag","0");   //行业大类 0-标准、1-优惠、2-减免
        data.put("merArea",merInfo.getMerArea());   //商户区域：银联地区码
        data.put("merAddr",merInfo.getMerAddr());   //注册地址
        data.put("businBegtime",merInfo.getBusinBegtime());   //营业时间：开始时间，格式：HHMM 1800
        data.put("businEndtime",merInfo.getBusinEndtime());   //营业时间：结束时间，格式：HHMM 0800
        data.put("employeeNum",merInfo.getEmployeeNum());   //公司规模:1：0-50人；2：50-100人；3:100以上
        data.put("businForm",merInfo.getBusinForm());   //经营形态：02-普通店、01-连锁店
        return data.toString();
    }

    /*
    *附加信息
    * 该字段为商户的法人身份证等信息，JSON格式字符串
    * */
    public  static String getPlusInfo(PlusInfo plusInfo){
        JSONObject data = new JSONObject();
        data.put("merLegal",plusInfo.getMerLegal());   //法定代表人姓名
        data.put("legalType",plusInfo.getLegalType());   //证件类型：0-身份证;1-护照;2-港澳通行证
        data.put("legalCode",plusInfo.getLegalCode());   //法人证件号码
        data.put("legalValidity",plusInfo.getLegalValidity());   //证件有效期 格式：[“起始日期”,”截止日期”],JSON格式字符串 ["2012.01.19", "2032.01.19"]
        data.put("legalPhone",plusInfo.getLegalPhone());   //手机号
        data.put("linkMan",plusInfo.getLinkMan());   //商户联系人姓名
        data.put("linkmanType",plusInfo.getLinkmanType());   //证件类型：0-居民身份证或临时身份证;1-外国公民护照;2-港澳居民来往大陆通行证或其他有效旅游证件；3-其他类个人身份有效证件；4-单位证件；5-军人或武警身份证件
        data.put("linkmanCode",plusInfo.getLinkmanCode());   //证件号码
        data.put("linkmanValidity",plusInfo.getLinkmanValidity());   //证件有效期 格式：[“起始日期”,”截止日期”],JSON格式字符串 ["2012.01.19", "2032.01.19"]
        data.put("linkmanPhone",plusInfo.getLinkmanPhone());   //手机号

        return data.toString();
    }

    /*
    * 营业执照信息
    * 该字段为商户的营业执照信息，JSON格式字符串
    * */
    public  static String getLicInfo(LicInfo licInfo, String merMode){
        JSONObject data = new JSONObject();
          if(!MerModeEnum.PERSON.getCode().equals(merMode)){
              data.put("merLic",licInfo.getMerLic());    //工商注册号/统一社会信用码（营业执照）  ,merMode为0,1时，必传
              data.put("licValidity",licInfo.getLicValidity());    //营业执照有效期 格式：[“起始日期”,”截止日期”],JSON格式字符串  JSON格式字符串 ,merMode为0,1时，必传 ["2012.01.19", "2032.01.19"]
          }
          data.put("licName",licInfo.getLicName());    //注册名称
          data.put("businScope",licInfo.getBusinScope());    //主营业务
          data.put("capital",licInfo.getCapital());    //注册资本：单位-万元
          data.put("licAddr",licInfo.getLicAddr());    //	注册地址
          data.put("controlerName",licInfo.getControlerName());    //	控股股东或实际控制人姓名或名称
          data.put("controlerLegalCode",licInfo.getControlerLegalCode());    //控股股东或实际控制人证件号码
          data.put("controlerLegalType",licInfo.getControlerLegalType());    //控股股东或实际控制人证件种类：0-身份证;1-护照;2-港澳通行证
          data.put("controlerLegalValidity",licInfo.getControlerLegalValidity());    //控股股东或实际控制人证件有效期 ["2012.01.19", "2032.01.19"]
          return data.toString();
    }


    /*
    *结算账户信息
    *该字段为商户的结算账户信息，JSON格式字符串
    * */
      public  static String getAccInfo(AccInfo accInfo){
          JSONObject data = new JSONObject();
          data.put("bankName",accInfo.getBankName());   //开户行名称
          data.put("bankCode",accInfo.getBankCode());   //开户行行号
          data.put("account",accInfo.getAccount());   //	账号
          data.put("accName",accInfo.getAccName());   //	账户名称
          data.put("accType",accInfo.getAccType());   //账户类型：00-个人；10-对公

          if("00".equals(accInfo.getAccType())){
              data.put("accArea",accInfo.getAccArea());   //账户所属区域:区号
              data.put("legalType",accInfo.getLegalType());   //证件类型：0-身份证;1-护照;2-港澳通行证 accType为个人时必填
              data.put("legalCode",accInfo.getLegalCode());   //证件号 accType为个人时必填
              data.put("accPhone",accInfo.getAccPhone());   //对私手机号 accType为个人时必填
          }



          //return "{\"bankName\":\"邮政储蓄银行\",\"bankCode\":\"403100000004\",\"account\":\"6221803610003002020\",\"accName\":\"何勇\",\"accType\":\"00\",\"legalType\":\"0\",\"legalCode\":\"342622199004102154\",\"accPhone\":\"18715101633\"}";

          return data.toString();
    }


    /*
     *功能信息
     *该字段为商户的功能信息，包括商户的扣率信息，JSON格式字符串(list)，可以开通1个或多个功能
     * */
      public  static String getFuncInfo(){
          List<String> list = new ArrayList<String>();

//          1）支付宝
          list.add(FuncInfo.alipay());
//          2）微信
          list.add(FuncInfo.weChat());
//          3）实时入账
//          list.add(FuncInfo.realtime());
//          4）银联二维码
          list.add(FuncInfo.unionpay());
//          5）立招
//          list.add(FuncInfo.standfor());
//          6）银联营销业务
          list.add(FuncInfo.unionpayMarketing());
//          7）银联聚合支付
//          list.add(FuncInfo.unionpaySmall());
//          8）银行卡收单
//          list.add(FuncInfo.unionpayCard());
//          8）D1增值服务费
//          list.add(FuncInfo.valueAdded());

          return list.toString();
    }

 /*   *//*
    * 图片信息
    * 该字段为商户的图片信息，JSON格式字符串(list)，可以上送0个或多个，
    * *//*
      public  static String  getPicInfoList(){
          List<String> list = new ArrayList<String>();
          list.add(PicInfoList.picInfo("01","5e6f3acc3e10af350d348112"));   //01 - 法人身份证正面
          list.add(PicInfoList.picInfo("02","5e6f3acc3e10af350d348116"));   //02 - 法人身份证反面
          list.add(PicInfoList.picInfo("03","5e6f3acc3e10af350d348114"));   //03-商户联系人身份证正面
          list.add(PicInfoList.picInfo("04","5e6f3acc3e10af350d348117"));   //04-商户联系人身份证反面
//          termMode为企业（0）或者个体户（1）时必传
          list.add(PicInfoList.picInfo("05","5e6f3acc3e10af350d34811a"));   //05-营业执照
          list.add(PicInfoList.picInfo("06","5e6f3acc3e10af350d34811c"));   //06-收银台
          list.add(PicInfoList.picInfo("07","5e6f3acc3e10af350d34811e"));   //07-内部环境照
          list.add(PicInfoList.picInfo("08","5e6f3acc3e10af350d348120"));   //08-营业场所门头照
          list.add(PicInfoList.picInfo("09","5e6f3acc3e10af350d348122"));   //09-门牌号
          list.add(PicInfoList.picInfo("10","5e6f3acd3e10af350d348124"));   //10-协议
          list.add(PicInfoList.picInfo("11","5e6f3acd3e10af350d348125"));   //11-商户登记表正面
          list.add(PicInfoList.picInfo("12","5e6f3acd3e10af350d348128"));   //12-商户登记表反面
//          收款银行为对公时，必传
          list.add(PicInfoList.picInfo("13","5df9c8c23e10af3a9a291aca"));   //13-开户许可证
          list.add(PicInfoList.picInfo("14","5e6f3acd3e10af350d348129"));   //14-银行卡
//          收款银行为对私时，必传
          list.add(PicInfoList.picInfo("15","5e6f3acd3e10af350d34812b"));   //15-清算授权书
//          list.add(PicInfoList.picInfo("16","5df9c8c23e10af3a9a291aca"));   //16-定位照（选填）
//          list.add(PicInfoList.picInfo("17","5df9c8c23e10af3a9a291aca"));   //17-手持身份证照片（选填）

          return list.toString();
    }*/

    /*
     * 图片信息
     * 该字段为商户的图片信息，JSON格式字符串(list)，可以上送0个或多个，
     * */
    public  static String  getPicInfoList(String  merMode, String accType, Map<String,String> map){
        //商户经营类型merMode（0-企业 1-个体户 2-个人）
        List<String> list = new ArrayList<String>();
        list.add(PicInfoList.picInfo("01",map.get("01")));   //01 - 法人身份证正面
        list.add(PicInfoList.picInfo("02",map.get("02")));   //02 - 法人身份证反面
        if(MerModeEnum.PERSON.getCode().equals(merMode)){
            list.add(PicInfoList.picInfo("06",map.get("06")));   //06-收银台
            list.add(PicInfoList.picInfo("07",map.get("07")));   //07-内部环境照
            list.add(PicInfoList.picInfo("08",map.get("08")));   //08-营业场所门头照
            //list.add(PicInfoList.picInfo("03",map.get("03")));   //03-商户联系人身份证正面
            //list.add(PicInfoList.picInfo("04",map.get("04")));   //04-商户联系人身份证反面
        } else if (MerModeEnum.SELF_EMPLOYED.getCode().equals(merMode)) {
            //个体户（1）时必传
            list.add(PicInfoList.picInfo("06",map.get("06")));   //06-收银台
            list.add(PicInfoList.picInfo("07",map.get("07")));   //07-内部环境照
            list.add(PicInfoList.picInfo("08",map.get("08")));   //08-营业场所门头照
            //list.add(PicInfoList.picInfo("09",map.get("09")));   //09-门牌号
            //list.add(PicInfoList.picInfo("10",map.get("10")));   //10-协议
            //list.add(PicInfoList.picInfo("11",map.get("11")));   //11-商户登记表正面
            //list.add(PicInfoList.picInfo("12",map.get("12")));   //12-商户登记表反面
            list.add(PicInfoList.picInfo("05",map.get("05")));   //05-营业执照
        }else if(MerModeEnum.ENTERPRISE.getCode().equals(merMode)){
            //merMode为企业（0）
            list.add(PicInfoList.picInfo("05",map.get("05")));   //05-营业执照
        }
        //	账户类型：00-个人(对私)；10-对公
        if(AccTypeEnum.ENTERPRISE.getCode().equals(accType)){
            //收款银行为对公时，必传
            //list.add(PicInfoList.picInfo("13",map.get("13")));   //13-开户许可证
            //list.add(PicInfoList.picInfo("14",map.get("14")));   //14-银行卡
        }else if(AccTypeEnum.PERSON.getCode().equals(accType)){
            //收款银行为对私时，必传
            //list.add(PicInfoList.picInfo("15",map.get("15")));   //15-清算授权书
            //list.add(PicInfoList.picInfo("14",map.get("14")));   //14-银行卡
//          list.add(PicInfoList.picInfo("16","5df9c8c23e10af3a9a291aca"));   //16-定位照（选填）
//          list.add(PicInfoList.picInfo("17","5df9c8c23e10af3a9a291aca"));   //17-手持身份证照片（选填）
        }

        return list.toString();
    }

}
