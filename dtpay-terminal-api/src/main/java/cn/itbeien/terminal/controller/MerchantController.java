package cn.itbeien.terminal.controller;

import cn.itbeien.terminal.entity.MerchantInfo;
import cn.itbeien.terminal.enums.SystemEnum;
import cn.itbeien.terminal.service.IMerchantManagement;
import cn.itbeien.terminal.service.IMerchantService;
import cn.itbeien.terminal.util.JwtUtils;
import cn.itbeien.terminal.vo.ImageInfo;
import cn.itbeien.terminal.vo.ResultVO;
import cn.itbeien.terminal.vo.SmallMerchantVO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.itbeien.terminal.vo.BaseMerchantVO;

import javax.servlet.http.HttpServletRequest;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class MerchantController {

    @Autowired
    private IMerchantManagement merchantManagement;

    @Autowired
    private IMerchantService merchantService;

    @RequestMapping("test")
    public String test(HttpServletRequest request){
        log.info("uid:{}",request.getAttribute("uid"));
        return "ok";
    }


    /**
     * 上传图片到聚合支付系统(dtpay)
     * @param multipartFile 上传的文件
     * @param picMode 图片类型
     * @return
     */
    @PostMapping("upload")
    public ResultVO upload(@RequestParam("file") MultipartFile multipartFile, String picMode){
        ResultVO resultVO = new ResultVO();
        try{
            ImageInfo merchantImageInfo = new ImageInfo();
            merchantImageInfo.setPicMode(picMode);
            String result = merchantManagement.merchantImgUpload(multipartFile,merchantImageInfo);
            resultVO.setData(result);
            resultVO.setCode(SystemEnum.SUCCESS.getCode());
            resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
        }catch(Exception e){
            resultVO.setData("error");
            resultVO.setCode(SystemEnum.ERROR.getCode());
            resultVO.setMessage(SystemEnum.ERROR.getMessage());
        }

        return resultVO;
    }


    @PostMapping("findMerchant")
    public ResultVO findMerchantByCreator(HttpServletRequest request){
        ResultVO<MerchantInfo> resultVO = new ResultVO();
        try{
            String uid  = String.valueOf(request.getAttribute("uid"));
            MerchantInfo merchantInfo = this.merchantService.findMerchantByCreator(uid);
            resultVO.setData(merchantInfo);
            resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
            resultVO.setCode(SystemEnum.SUCCESS.getCode());
        }catch(Exception e){
            log.error("查询小微商户信息异常:{}",e);
            resultVO.setData(null);
            resultVO.setMessage(SystemEnum.ERROR.getMessage());
            resultVO.setCode(SystemEnum.ERROR.getCode());
        }
        return resultVO;
    }

    /**
     * 小程序提交资料在聚合支付系统进件
     * @param data
     * @param request
     * @return
     */
    @PostMapping("initSmallMerchant")
    public ResultVO initSmallMerchant(@RequestBody String data, HttpServletRequest request){
        log.info("small data:{}",data);
        ResultVO resultVO = new ResultVO();
        try{
            SmallMerchantVO smallMerchantVO = JSONObject.toJavaObject(JSONObject.parseObject(data),SmallMerchantVO.class);
            MerchantInfo merchantInfo = new MerchantInfo();
            String creater = String.valueOf(request.getAttribute("uid"));
            merchantInfo.setCreator(creater);//设置商户创建人
            this.merchantService.merchantReg(merchantInfo,smallMerchantVO);

            resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
            resultVO.setCode(SystemEnum.SUCCESS.getCode());
            resultVO.setData(SystemEnum.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("小微商户进件异常:{}",e);
            resultVO.setMessage(SystemEnum.REG_MERCHANT.getMessage());
            resultVO.setCode(SystemEnum.REG_MERCHANT.getCode());
            resultVO.setData(SystemEnum.REG_MERCHANT.getMessage());
        }

        return resultVO;
    }

    @PostMapping("findBaseMerchant")
    public ResultVO findBaseMerchantByCreator(HttpServletRequest request){
        ResultVO<MerchantInfo> resultVO = new ResultVO();
        try{
            String uid = String.valueOf(request.getAttribute("uid"));
            MerchantInfo merchantInfo = this.merchantService.findBaseMerchantByCreator(uid);
            resultVO.setData(merchantInfo);
            resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
            resultVO.setCode(SystemEnum.SUCCESS.getCode());
        }catch(Exception e){
            log.error("查询标准商户信息异常:{}",e);
            resultVO.setData(null);
            resultVO.setMessage(SystemEnum.ERROR.getMessage());
            resultVO.setCode(SystemEnum.ERROR.getCode());
        }
        return resultVO;
    }

    /**
     * 标准商户小程序提交资料在聚合支付系统进件
     * @param data
     * @param request
     * @return
     */
    @PostMapping("initBaseMerchant")
    public ResultVO initBaseMerchant(@RequestBody String data,HttpServletRequest request){
        log.info("base merchant data:{}",data);
        String creater = String.valueOf(request.getAttribute("uid"));
        ResultVO resultVO = new ResultVO();
        try{
            BaseMerchantVO baseMerchantVO = JSONObject.toJavaObject(JSONObject.parseObject(data),BaseMerchantVO.class);
            MerchantInfo merchantInfo = new MerchantInfo();
            merchantInfo.setCreator(creater);//设置商户创建人
            this.merchantService.baseMerchantReg(merchantInfo,baseMerchantVO);
            resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
            resultVO.setCode(SystemEnum.SUCCESS.getCode());
            resultVO.setData("success");
        }catch (Exception e){
            log.error("标准商户进件异常:{}",e);
            resultVO.setMessage(SystemEnum.REG_MERCHANT.getMessage());
            resultVO.setCode(SystemEnum.REG_MERCHANT.getCode());
            resultVO.setData(SystemEnum.REG_MERCHANT.getMessage());
        }

        return resultVO;
    }

}
