package cn.itbeien.terminal.controller;

import cn.itbeien.common.utils.Base64Util;
import cn.itbeien.terminal.entity.MerchantInfo;
import cn.itbeien.terminal.entity.QrCodeMerchant;
import cn.itbeien.terminal.enums.MerModeEnum;
import cn.itbeien.terminal.enums.SystemEnum;
import cn.itbeien.terminal.service.IMerchantManagement;
import cn.itbeien.terminal.service.IMerchantService;
import cn.itbeien.terminal.service.IQrCodeService;
import cn.itbeien.terminal.util.JwtUtils;
import cn.itbeien.terminal.vo.*;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import cn.itbeien.terminal.enums.QREnum;


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

    @Autowired
    private IQrCodeService qrCodeService;


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


    /**
     * 查询聚合支付系统当前商户绑定的所有设备
     * @return
     */
    @PostMapping("findBindDevice")
    public ResultVO findBindDevice(HttpServletRequest request){
        ResultVO resultVO = new ResultVO();
        try{
            String creater = String.valueOf(request.getAttribute("uid"));
            //查询uid对应的商户号
            List<MerchantInfo> merchantInfos = this.merchantService.findMerchantAllByCreator(creater);
            if(merchantInfos==null || merchantInfos.size()==0){
                resultVO.setCode(SystemEnum.BIND_DEVICE_QUERY_SUCCESS.getCode());
                resultVO.setMessage(SystemEnum.BIND_DEVICE_QUERY_SUCCESS.getMessage());
                resultVO.setData(null);
                return resultVO;
            }
            //根据商户号获取所有绑定设备信息
            List<QrCodeMerchantVO> qrCodeMerchantVOS =  this.qrCodeService.selectByMerchantCode(merchantInfos.get(0).getMercNo());
            for(QrCodeMerchantVO qrCodeMerchantVO : qrCodeMerchantVOS){
                qrCodeMerchantVO.setProName("SN:"+qrCodeMerchantVO.getProName());
                qrCodeMerchantVO.setStage(QREnum.getNameByCode(qrCodeMerchantVO.getStage()));
            }
            resultVO.setCode(SystemEnum.BIND_DEVICE_QUERY_SUCCESS.getCode());
            resultVO.setMessage(SystemEnum.BIND_DEVICE_QUERY_SUCCESS.getMessage());
            resultVO.setData(qrCodeMerchantVOS);
        }catch(Exception e){
            resultVO.setCode(SystemEnum.BIND_DEVICE_QUERY_ERROR.getCode());
            resultVO.setMessage(SystemEnum.BIND_DEVICE_QUERY_ERROR.getMessage());
            resultVO.setData(e.getMessage());
            log.error("根据商户号获取绑定设备异常:{}",e);
        }

        return resultVO;
    }


    /**
     * 通过二维码code绑定商户
     * @param code
     * @return
     */
    @PostMapping("bindDevice")
    public ResultVO bindDevice(@RequestBody String code,HttpServletRequest request){
        ResultVO resultVO = new ResultVO();
        log.info("code:{}",code);
        //base64 decode
        try {
            String creater = String.valueOf(request.getAttribute("uid"));
            String qrCode = new String(Base64Util.decodeBase64(String.valueOf(JSONObject.parseObject(code).get("code"))));
            //查询次二维码是否有绑定设备
            QrCodeMerchant qrCodeMerchant = this.qrCodeService.selectByQrCode(qrCode);
            if(qrCodeMerchant!=null){ //已经绑定其他商户
                resultVO.setCode(SystemEnum.BIND_DEVICE.getCode());
                resultVO.setMessage(SystemEnum.BIND_DEVICE.getMessage());
                resultVO.setData(SystemEnum.BIND_DEVICE.getMessage());
            }else{
                //查询uid对应的商户号，再通过商户号二维码code进行设备绑定
                MerchantInfo merchantInfo = null;
                String merType  = String.valueOf(JSONObject.parseObject(code).get("merType"));
                String deviceNo  = String.valueOf(JSONObject.parseObject(code).get("deviceNo"));

                if(MerModeEnum.PERSON.getCode().equals(merType)){//小微绑定二维码
                    merchantInfo = this.merchantService.findMerchantByCreator(creater);
                }else {//企业绑定二维码
                    merchantInfo = this.merchantService.findBaseMerchantByCreator(creater);
                }
                if(merchantInfo == null){
                    log.info("还未创建商户，不能进行设备绑定");
                    resultVO.setCode(SystemEnum.BIND_DEVICE_ERROR.getCode());
                    resultVO.setMessage(SystemEnum.BIND_DEVICE_ERROR.getMessage());
                    resultVO.setData(SystemEnum.BIND_DEVICE_ERROR.getMessage());
                    return resultVO;
                }
                QrCodeMerchant qrCodeMerchantDb = new QrCodeMerchant();
                qrCodeMerchantDb.setQrCode(qrCode);
                qrCodeMerchantDb.setMerchantNo(merchantInfo.getMercNo());
                qrCodeMerchantDb.setMerchantName(merchantInfo.getMercName());
                qrCodeMerchantDb.setTerminalNumber(deviceNo);
                //二维码绑定商户
                this.qrCodeService.saveQrCodeMerchant(qrCodeMerchantDb);
                log.info("查询uid对应的商户号，再通过商户号二维码code进行设备绑定");
                resultVO.setCode(SystemEnum.BIND_DEVICE_SUCCESS.getCode());
                resultVO.setMessage(SystemEnum.BIND_DEVICE_SUCCESS.getMessage());
                resultVO.setData(SystemEnum.BIND_DEVICE_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            resultVO.setCode(SystemEnum.BIND_DEVICE_FAIL.getCode());
            resultVO.setMessage(SystemEnum.BIND_DEVICE_FAIL.getMessage());
            resultVO.setData(SystemEnum.BIND_DEVICE_FAIL.getMessage());
            log.error("二维码绑定设备异常:{}",e);
        }
        return resultVO;
    }


    /**
     * 审核状态为未审核/审核未通过可以对资料进行编辑(小微商户)
     * @param request
     * @return
     */
    @PostMapping("editSmallMerchant")
    public ResultVO editSmallMerchantByCreator(HttpServletRequest request){
        ResultVO<SmallMerchantVO> resultVO = new ResultVO();
        try{
            String uid  = String.valueOf(request.getAttribute("uid"));
            SmallMerchantVO smallMerchantVO = this.merchantService.selectEditSmallMerchantAllCreator(uid);
            smallMerchantVO.setCode(SystemEnum.SUCCESS.getMessage());
            resultVO.setData(smallMerchantVO);
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
     * 审核状态为未审核/审核未通过可以对资料进行编辑(标准商户)
     * @param request
     * @return
     */
    @PostMapping("editBaseMerchant")
    public ResultVO editBaseMerchantByCreator(HttpServletRequest request){
        ResultVO<BaseMerchantVO> resultVO = new ResultVO();
        try{
            String token = request.getHeader("Authorization");
            String uid  = JwtUtils.getUserByToken(token);
            BaseMerchantVO baseMerchantVO = this.merchantService.selectEditBaseMerchantAllCreator(uid);
            baseMerchantVO.setCode(SystemEnum.SUCCESS.getMessage());
            resultVO.setData(baseMerchantVO);
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
     * 查询小微商户审核状态
     * @param request
     * @return
     */
    @PostMapping("queryAuditResult")
    public ResultVO queryAuditResult(HttpServletRequest request,@RequestBody String merchantType){
        ResultVO resultVO = new ResultVO();
        String token = request.getHeader("Authorization");
        JSONObject jsonObject = JSONObject.parseObject(merchantType);
        try {
            String creater = JwtUtils.getUserByToken(token);
            MerchantInfo merchantInfo = null;
            if(MerModeEnum.PERSON.getCode().equals(jsonObject.get("merchantType"))){
                merchantInfo = this.merchantService.findMerchantByCreator(creater);
            }else{
                merchantInfo = this.merchantService.findBaseMerchantByCreator(creater);
            }
            //String result  = this.merchantManagement.queryAuditResult(merchantInfo.getMercNo());//调用上游机构接口
            resultVO.setCode(SystemEnum.QUERY_MER_AUDIT.getCode());
            resultVO.setMessage(SystemEnum.QUERY_MER_AUDIT.getMessage());
            resultVO.setData(SystemEnum.AUDITRESULT_TODO.getMessage());
        }catch (Exception e){
            resultVO.setCode(SystemEnum.QUERY_MER_AUDIT_ERROR.getCode());
            resultVO.setMessage(SystemEnum.QUERY_MER_AUDIT_ERROR.getMessage());
            resultVO.setData(SystemEnum.QUERY_MER_AUDIT_ERROR.getMessage());
            log.error("查询小微商户审核异常:{}",e);
        }
        return resultVO;
    }

}
