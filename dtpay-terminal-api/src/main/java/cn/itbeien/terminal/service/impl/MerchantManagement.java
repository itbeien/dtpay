package cn.itbeien.terminal.service.impl;

import cn.itbeien.terminal.enums.SystemEnum;
import cn.itbeien.terminal.service.IMerchantManagement;
import cn.itbeien.terminal.util.DtUtil;
import cn.itbeien.terminal.vo.ImageInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
@Slf4j
public class MerchantManagement implements IMerchantManagement {

    @Value("${dtpay.merchant.upload}")
    private String uploadDir;

    /**
     * 商户图片信息上传(聚合支付系统dtpay)
     * POST multipart/form-data 方式
     * @return
     */
    @Override
    public String merchantImgUpload(MultipartFile multipartFile, ImageInfo merchantImageInfo) {
        if (multipartFile.isEmpty()) {
            return SystemEnum.UPLOAD_IMG_NOT_EXIST.getMessage();
        }
        String result = null;
        try{
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String fildId = merchantImageInfo.getPicMode()+ DtUtil.uuid();
            String fileName = fildId +".png";
            result = fildId;
            String filePath = uploadDir + File.separator + fileName;
            multipartFile.transferTo(new File(filePath));//写文件在本地
        }catch (Exception e){
            log.error("上传商户图片到聚合支付系统异常:{}",e);
        }
        return result;
    }




    public static void main(String[] args) {
        String msg = "{\"sysInfo\":\"[{\\\"merCode\\\":\\\"531000016422292\\\",\\\"bangdingcommercode\\\":\\\"831455353990464\\\",\\\"bangdingtermno\\\":\\\"46984334\\\",\\\"termMode\\\":\\\"2\\\",\\\"termTermcode\\\":\\\"46984334\\\",\\\"termMercode\\\":\\\"531000016422292\\\",\\\"termCode\\\":\\\"46984334\\\",\\\"username\\\":\\\"商户_吴>中昌\\\"}]\",\"funcInfo\":\"[{\\\"funcName\\\":\\\"支付宝\\\",\\\"calcVal\\\":\\\"0.38\\\",\\\"alipayCertUrl\\\":\\\"https://mobilecodec.alipay.com/show.htm?code=2ie18111q04rcry4fcpcr9e\\\",\\\"state\\\":\\\"1\\\",\\\"alipayId\\\":\\\"2088640263480010\\\",\\\"retMsg\\\":\\\"Q-审核中\\\",\\\"funcId\\\":2},{\\\"funcName\\\":\\\"微信\\\",\\\"calcVal\\\":\\\"0.38\\\",\\\"state\\\":\\\"1\\\",\\\"funcId\\\":3,\\\"retMsg\\\":\\\"微信拆\n" +
                "分错误：不允许开通的商户类型:5399[1031]\\\",\\\"CCalcVal\\\":\\\"0.38\\\",\\\"DCalcVal\\\":\\\"0.38\\\"},{\\\"DStlmMaxAmt\\\":\\\"30.00\\\",\\\"funcName\\\":\\\"银联二维码\\\",\\\"CFeeLowLimit\\\":\\\"0.00\\\",\\\"DStlmType\\\":\\\"1\\\",\\\"DFeeLowLimit\\\":\\\"0.00\\\",\\\"state\\\":\\\"1\\\",\\\"funcId\\\":12,\\\"retMsg\\\":\\\"\\\",\\\"DCalcVal\\\":\\\"0.6\\\",\\\"CCalcVal\\\":\\\"0.6\\\"},{\\\"DStlmMaxAmt\\\":\\\"20.00\\\",\\\"funcName\\\":\\\"银联营销业务\\\",\\\"CFeeLowLimit\\\":\\\"33.33\\\",\\\"DStlmType\\\":\\\"1\\\",\\\"DFeeLowLimit\\\":\\\"3.13\\\",\\\"state\\\":\\\"1\\\",\\\"funcId\\\":14,\\\"retMsg\\\":\\\"\\\",\\\"DCalcVal\\\":\\\"0.35\\\",\\\"CCalcVal\\\":\\\"0.35\\\"}]\",\"auditMsg\":\"审核成功[0000]\",\"retCode\":\"0000\",\"retMsg\":\"操作成功\",\"version\":\"1.0\",\"MAC\":\"BBF508C04B5EDC61EBF39899C87D9046\",\"recentMessType\":\"AGENTPATCH\",\"operaTrace\":\"duk68uK1pmNMkxRN\",\"messageType\":\"QUERYAUDMER\",\"clientCode\":\"48312411\",\"auditStatus\":\"0\",\"merTrace\":\"663ee88255546db82034daff\",\"staffId\":\"lnzhang-sxdl\"}";
        JSONObject jsonObject = JSONObject.parseObject(msg);
        JSONArray jsonArray = jsonObject.getJSONArray("sysInfo");
        String json = jsonArray.getString(0);
        JSONObject jsonObj = JSONObject.parseObject(json);
        String merCode = jsonObj.getString("merCode");
        String termTermcode = jsonObj.getString("termTermcode");
        System.out.println(merCode+" "+termTermcode);
    }
}
