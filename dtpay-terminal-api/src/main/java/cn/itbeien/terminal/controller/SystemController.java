package cn.itbeien.terminal.controller;

import cn.itbeien.terminal.entity.SysUser;
import cn.itbeien.terminal.enums.BankHbdm;
import cn.itbeien.terminal.enums.SystemEnum;
import cn.itbeien.terminal.service.ISystemService;
import cn.itbeien.terminal.service.IUserService;
import cn.itbeien.terminal.util.DtUtil;
import cn.itbeien.terminal.vo.BankVO;
import cn.itbeien.terminal.vo.CnAreaVO;
import cn.itbeien.terminal.vo.MccVO;
import cn.itbeien.terminal.vo.ResultVO;
import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@RestController
@RequestMapping("api")
@Slf4j
public class SystemController {
    @Value("${alibaba.cloud.access-key}")
    private String accessKey;
    @Value("${alibaba.cloud.secret-key}")
    private String secretKey;
    @Value("${sms.signName}")
    private String signName;

    @Value("${sms.template.code}")
    private String templateCode;

    @Autowired
    private ISmsService smsService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISystemService systemService;


    private static Map<String,String> smsCodeMap = new HashMap<>();

    private static ResultVO resultVO = new ResultVO();

    private static  ResultVO mccResultVO = new ResultVO();

    private static ResultVO wtZfxtInfoVO = new ResultVO();

    /***
     * 手机号登录，如果没有手机号则进行注册
     * 携带参数为手机号和短信
     * 判断当前手机号是否存在注册用户，如果存在则直接登录成功，如果不存在先注册用户
     * 再返回token信息
     * @return
     */
    @PostMapping("login")
    public ResultVO login(@RequestBody String data){
        ResultVO<SysUser> resultVO = null;
        //数据库中不存手机号则进行注册，如果存在手机号则直接登录
        SysUser sysUser = null;
       try{
           JSONObject  jsonObject = JSON.parseObject(data);
           sysUser = JSON.toJavaObject(jsonObject,SysUser.class);
           String smsCode = smsCodeMap.get(jsonObject.get("phone"));//对客户端传入的验证码进行验证
           resultVO = new ResultVO<>();

           if(smsCode.equals(sysUser.getUserCode())){
               SysUser dbSysUser = userService.regUser(sysUser);
               dbSysUser.setNickName(desensitizationData(dbSysUser.getNickName()));
               dbSysUser.setMessage(SystemEnum.SUCCESS.getMessage());
               resultVO.setData(dbSysUser);
               resultVO.setCode(SystemEnum.SUCCESS.getCode());
               resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
           }else{
               sysUser.setMessage(SystemEnum.SMS_ERROR.getMessage());
               resultVO.setCode(SystemEnum.SMS_ERROR.getCode());
               resultVO.setMessage(SystemEnum.SMS_ERROR.getMessage());
               resultVO.setData(sysUser);
               log.error("验证码错误,验证码为:{},用户输入的验证码为:{}",smsCode,sysUser.getUserCode());
           }
       }catch (Exception e){
           sysUser.setMessage(SystemEnum.LOGIN_ERROR.getMessage());
           resultVO.setCode(SystemEnum.LOGIN_ERROR.getCode());
           resultVO.setMessage(SystemEnum.LOGIN_ERROR.getMessage());
           resultVO.setData(sysUser);
           log.error("系统登录异常:{}",e);
       }
        return resultVO;
    }

    private static String desensitizationData(String phone){
        //截取前三位字符
        String tou = phone.substring(0, 3);
        //定义中间四位用****表示
        String zhong = "****";
        //截取后三位字符
        String hou = phone.substring(7, 11);
        String result = tou + zhong + hou;
        return result;
    }


    @RequestMapping(value = "/send.do",method = RequestMethod.POST)
    public ResultVO sendSms(@RequestBody String data){
        ResultVO<String> resultVO = new ResultVO<>();
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        String phone = String.valueOf(JSON.parseObject(data).get("userPhone"));
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"【企业级分布式应用服务】,您的验证码为${code}"时,此处的值为

        int smsCode  = DtUtil.random();
        request.setTemplateParam("{\"code\":\"" + smsCode + "\"}");
        SendSmsResponse sendSmsResponse ;
        try {
            sendSmsResponse = smsService.sendSmsRequest(request);
            smsCodeMap.put(phone,String.valueOf(smsCode));
            log.info("该手机号{} 发送的验证码为:{}",phone,smsCode);
            log.info("sms 响应数据code:{} message:{}",sendSmsResponse.getCode(),sendSmsResponse.getMessage());
            resultVO.setData("success");
            resultVO.setCode("200");
            resultVO.setMessage("success");
        }
        catch (ClientException e) {
            resultVO.setData("error");
            resultVO.setCode("500");
            resultVO.setMessage("error");
            log.error("发送短信异常:",e);
        }
        return resultVO ;
    }

    @PostMapping("area")
    public ResultVO area(){
       /* ResultVO resultVO = new ResultVO();
        List<CnAreaVO> cnAreaVOS = this.systemService.selectCnArea("0");//省
        for(CnAreaVO cnAreaVO : cnAreaVOS){

            List<CnAreaVO> cnCityVOS = this.systemService.selectCnArea(cnAreaVO.getValue());//市
            cnAreaVO.setChildren(cnCityVOS);
            for (CnAreaVO city:cnCityVOS){
                List<CnAreaVO> cnRegions =  this.systemService.selectCnArea(city.getValue());//区/县
                city.setChildren(cnRegions);
            }
        }
        resultVO.setCode(SystemEnum.SUCCESS.getCode());
        resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
        resultVO.setData(cnAreaVOS);*/
        return resultVO;
    }


    @PostMapping("initBank")
    public ResultVO initBank(@RequestBody String data){

        return wtZfxtInfoVO;
    }

    /**
     * 初始化所有银行数据
     */
    @PostConstruct
    public void initWtZfxtInfo(){
        //ResultVO wtZfxtInfoVO = new ResultVO();
        List<BankVO> bankVOS = new ArrayList<>();
        for(BankHbdm bankHbdm:BankHbdm.values()){
            BankVO bankVO = new BankVO();
            bankVO.setText(bankHbdm.getDesc());
            bankVO.setValue(bankHbdm.getCode());
            List<BankVO> wtZfxtInfoVOS = this.systemService.selectBankByHbdm(bankHbdm.getCode());
            bankVO.setChildren(wtZfxtInfoVOS);
            bankVOS.add(bankVO);
        }
        wtZfxtInfoVO.setData(bankVOS);
        wtZfxtInfoVO.setCode(SystemEnum.SUCCESS.getCode());
        wtZfxtInfoVO.setCode(SystemEnum.SUCCESS.getMessage());
    }

    @PostConstruct
    public void postConstruct(){
        List<CnAreaVO> cnAreaVOS = this.systemService.selectCnArea("0");//省
        for(CnAreaVO cnAreaVO : cnAreaVOS){
            List<CnAreaVO> cnCityVOS = this.systemService.selectCnArea(cnAreaVO.getId());//市
            cnAreaVO.setChildren(cnCityVOS);
            for (CnAreaVO city:cnCityVOS){
                List<CnAreaVO> cnRegions =  this.systemService.selectCnArea(city.getId());//区/县
                city.setChildren(cnRegions);
            }
        }
        resultVO.setCode(SystemEnum.SUCCESS.getCode());
        resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
        resultVO.setData(cnAreaVOS);
    }

    @PostMapping("mcc")
    public ResultVO mcc(){
       /* List<MccVO> mccParents =  systemService.selectMccParent();
        ResultVO resultVO = new ResultVO();
        for(MccVO mccParent : mccParents){
            List<MccVO> mccChildrens =  systemService.selectMccByParent(mccParent.getValue());
            mccParent.setChildren(mccChildrens);
        }
        resultVO.setData(mccParents);
        resultVO.setCode(SystemEnum.SUCCESS.getCode());
        resultVO.setMessage(SystemEnum.SUCCESS.getMessage());*/
        return mccResultVO;

    }


    @PostConstruct
    public void mccPostConstruct(){
        List<MccVO> mccParents =  systemService.selectMccParent();
        for(MccVO mccParent : mccParents){
            List<MccVO> mccChildrens =  systemService.selectMccByParent(mccParent.getValue());
            mccParent.setChildren(mccChildrens);
        }
        mccResultVO.setData(mccParents);
        mccResultVO.setCode(SystemEnum.SUCCESS.getCode());
        mccResultVO.setMessage(SystemEnum.SUCCESS.getMessage());
    }
}
