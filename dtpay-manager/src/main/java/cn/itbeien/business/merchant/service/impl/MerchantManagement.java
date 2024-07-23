package cn.itbeien.business.merchant.service.impl;

import cn.itbeien.business.merchant.domain.easypay.ChannelMerchantInfo;
import cn.itbeien.business.merchant.domain.easypay.ImageInfo;
import cn.itbeien.business.merchant.domain.easypay.parameter.PublicFun;
import cn.itbeien.business.merchant.service.IMerchantManagement;
import cn.itbeien.business.util.HttpClient;
import cn.itbeien.business.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static cn.itbeien.business.merchant.domain.easypay.RunApp.doPost;

/**
 * @author beien
 * @date 2024-03-26 11:50
 * Copyright© 2024 beien
 * 商户入网进件管理
 * 入网上游支付机构
 *
 * 1.图片上传
 * 2.商户信息录入
 * 3.商户信息补件
 * 4.商户异步通知回调
 * 5.商户审核结果查询
 * 6.商户信息查询
 * 7.商户签约申请接口 电子签约必调
 */
@Service
@Slf4j
public class MerchantManagement implements IMerchantManagement {

   // @Value("${yscard.agent.key}")
    private String key;

    //@Value("${yscard.uploadImg.url}")
    String imgUrl;

    @Value("${dtpay.merchant.upload}")
    private String uploadDir;

    //@Value("${yscard.agent.gen}")
    private String genUrl;

    /**
     * 商户图片信息上传
     * POST multipart/form-data 方式
     * @return
     */
    @Override
    public String merchantImgUpload(ImageInfo merchantImageInfo) {
        String result = null;
        try{
            HashMap params = new HashMap();
            params.put("version",merchantImageInfo.getVersion());
            params.put("clientCode",merchantImageInfo.getClientCode());
            params.put("picMode",merchantImageInfo.getPicMode());
            String mac = MD5Util.getSign(key,params,"UTF-8");
            params.put("MAC",mac);

            String fildId = merchantImageInfo.getFileId();
            String fileName = fildId +".png";
            result = fildId;
            String filePath = uploadDir + "\\" + fileName;

            File file = new File(filePath);
            log.info("上传图片请求数据:{}",params.toString());
            //文件
            result  = HttpClient.sendMuiltiPartFile(imgUrl,file,"fileName",params);
            log.info("上传图片响应数据:{}",result);
        }catch (Exception e){
            log.error("上传商户图片到上游机构异常:{}",e);
        }
        return result;
    }

    /**
     * 商户信息录入
     * @return
     */
    @Override
    public String merchantInfoEntering() {
        String url = genUrl;
        return null;
    }


    @Override
    public String initSmallMerchant(ChannelMerchantInfo merchantInfo, Map<String,String> picMap) {
        String result  = doPost(PublicFun.getAGMERAPPLY(merchantInfo,picMap),genUrl);     //5.2-商户信息录入后，去商户管理中台-->商户审核，进行审核
        //doPost(PublicFun.getAGENTPATCH());     //5.3-商户信息补件
        // doPost(PublicFun.getQUERYAUDMER());    //5.5 商户审核结果查询
        // doPost(PublicFun.getQUERYMER());       //5.6 商户信息查
        return result;
    }

    @Override
    public String initBaseMerchant(ChannelMerchantInfo merchantInfo, Map<String,String> picMap) {
        String result  = doPost(PublicFun.getAGMERAPPLY(merchantInfo,picMap),genUrl);     //5.2-商户信息录入后，去商户管理中台-->商户审核，进行审核
        //doPost(PublicFun.getAGENTPATCH());     //5.3-商户信息补件
        // doPost(PublicFun.getMerchantSigned());     //5.3-商户签约申请
        // doPost(PublicFun.getQUERYAUDMER());    //5.5 商户审核结果查询
        // doPost(PublicFun.getQUERYMER());       //5.6 商户信息查询
        return result;
    }
}
