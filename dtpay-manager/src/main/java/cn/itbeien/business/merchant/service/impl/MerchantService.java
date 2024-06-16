package cn.itbeien.business.merchant.service.impl;

import cn.itbeien.business.enums.MerModeEnum;
import cn.itbeien.business.enums.MerchantEnum;
import cn.itbeien.business.merchant.domain.ExtMerchantInfo;
import cn.itbeien.business.merchant.domain.MerchantInfo;
import cn.itbeien.business.merchant.domain.easypay.*;
import cn.itbeien.business.merchant.mapper.ExtMerchantInfoMapper;
import cn.itbeien.business.merchant.mapper.MerchantInfoMapper;
import cn.itbeien.business.merchant.service.IMerchantManagement;
import cn.itbeien.business.merchant.service.IMerchantService;
import cn.itbeien.business.util.SnowFlake;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author beien
 * @date 2024-04-18 10:20
 * Copyright© 2024 beien
 */
@Service
@Slf4j
public class MerchantService implements IMerchantService {

    @Value("${yscard.agent.clientCode}")
    private String clientCode;

    @Value("${yscard.agent.version}")
    private String version;

    @Value("${snowFlake.workerId}")
    private long workerId;
    @Value("${snowFlake.datacenterId}")
    private long datacenterId;
    @Value("${snowFlake.sequence}")
    private long sequence;
    @Resource
    private ExtMerchantInfoMapper extMerchantInfoMapper;

    private SnowFlake snowFlake = new SnowFlake(workerId,datacenterId,sequence);

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Autowired
    private IMerchantManagement merchantManagement;

    @Override
    public List<MerchantInfo> selectMerchantInfoList(MerchantInfo merchantInfo) {
        return this.merchantInfoMapper.selectMerchantInfoList(merchantInfo);
    }

    @Override
    public MerchantInfo findMercInfoByMercNo(String mercNo) {
        return this.merchantInfoMapper.selectByPrimaryKey(mercNo);
    }

    /**
     * 上传图片并进行审核
     * @param picMode
     * @return
     */
    private void upload(String picMode){
        try{
            ImageInfo merchantImageInfo = new ImageInfo();
            merchantImageInfo.setVersion(version);
            merchantImageInfo.setClientCode(clientCode);
            merchantImageInfo.setPicMode(picMode);
            String result = merchantManagement.merchantImgUpload(merchantImageInfo);
            String fildId = String.valueOf(JSONObject.parseObject(result).get("fileId"));
        }catch(Exception e){
            log.error("上传图片到上游异常:{}",e);
        }
    }

    @Override
    public int auditMerchant(MerchantInfo merchantInfo) {
        return 0;
    }


    /**
     *
     * @param merchantInfo
     * @return
     */
    @Override
    public void merchantReg(MerchantInfo merchantInfo, ChannelMerchantInfo channelMerchantInfo) throws Exception{
        MerInfo merInfo = new MerInfo();
        merInfo.setMerMode(MerModeEnum.PERSON.getCode());//商户类型
        merInfo.setMerName(merchantInfo.getMercName());//注册名称
        merInfo.setBusinName(merchantInfo.getMercNickName());//经营名称(店名)
        merInfo.setMerType(merchantInfo.getMccId());//mcc 商户类型
        //merInfo.setMerArea(smallMerchantVO.getAddress());//店铺地址 银联地区码
        merInfo.setMerArea("1022");//店铺地址 银联地区码,需要调整为从数据库查询
        merInfo.setMerAddr(merchantInfo.getCorpAddr());//注册地址  不得少于10个字符
        merInfo.setBusinBegtime("09:00");//开始营业时间
        merInfo.setBusinEndtime("18:00");//结束营业时间
        merInfo.setEmployeeNum(MerchantEnum.EMPLOYEE_NUM_1.getCode());//公司规模:1：0-50人；2：50-100人；3:100以上
        merInfo.setBusinForm(MerchantEnum.BUSIN_FORM_02.getCode());//经营形态：02-普通店、01-连锁店

        PlusInfo plusInfo = new PlusInfo();
        plusInfo.setMerLegal(merchantInfo.getMerLegal());//法定代表人姓名
        plusInfo.setLegalType(merchantInfo.getLegalType());//证件类型
        plusInfo.setLegalCode(merchantInfo.getLegalCode());//证件号码
        String legaValidity = merchantInfo.getLegalValidity().replace("-",".");
        plusInfo.setLegalValidity(legaValidity);//证件有效期
        plusInfo.setLegalPhone(merchantInfo.getTelphone());//手机号
        plusInfo.setLinkMan(merchantInfo.getMerLegal());//联系人
        plusInfo.setLinkmanType(merchantInfo.getLegalType());//证件类型
        plusInfo.setLinkmanCode(merchantInfo.getLegalCode());//联系人证件号码
        plusInfo.setLinkmanValidity(legaValidity);//联系人证件有效期
        plusInfo.setLinkmanPhone(merchantInfo.getTelphone());//联系人手机号码

        LicInfo licInfo = new LicInfo();
        licInfo.setLicName(merchantInfo.getMercName());//注册名称
        licInfo.setBusinScope(merchantInfo.getMccId());//主营业务 mccid
        licInfo.setCapital("10");//注册资本 注册资本：单位-万元
        licInfo.setLicAddr(merchantInfo.getCorpAddr());//注册地址
        licInfo.setControlerName(merchantInfo.getMerLegal());//控股股东或实际控制人姓名或名称
        licInfo.setControlerLegalCode(merchantInfo.getLegalCode());//控股股东或实际控制人证件号码
        licInfo.setControlerLegalType(merchantInfo.getLegalType());//控股股东或实际控制人证件种类：0-身份证;1-护照;2-港澳通行证
        licInfo.setControlerLegalValidity(legaValidity);//控股股东或实际控制人证件有效期 ["2012.01.19", "2032.01.19"]

        AccInfo accInfo = new AccInfo();
        accInfo.setBankName(merchantInfo.getMercSettBankNm());
        //accInfo.setBankCode(smallMerchantVO.getBankCode());//银行行号
        accInfo.setBankCode("308584001856");//银行行号,调整为从数据库中查
        accInfo.setAccount(merchantInfo.getMercSettAcct());
        accInfo.setAccName(merchantInfo.getMercSettAcctNm());
        accInfo.setAccType(merchantInfo.getMercSettAcctType());
        accInfo.setAccArea(merchantInfo.getAddress());
        accInfo.setLegalType(merchantInfo.getLegalType());
        accInfo.setLegalCode(merchantInfo.getLegalCode());
        accInfo.setAccPhone(merchantInfo.getTelphone());


        channelMerchantInfo.setMerInfo(merInfo);
        channelMerchantInfo.setPlusInfo(plusInfo);
        channelMerchantInfo.setLicInfo(licInfo);
        channelMerchantInfo.setAccInfo(accInfo);//结算账户
        //构造参数
        Map<String,String> picMap = new HashMap<>();
        //01 - 法人身份证正面
        picMap.put("01",merchantInfo.getMerIdRxm());
        //02 - 法人身份证反面
        picMap.put("02",merchantInfo.getMerIdGhm());
        picMap.put("06",merchantInfo.getMerSyt());// 收银台
        picMap.put("07",merchantInfo.getMerHjz());//内部环境照
        picMap.put("08",merchantInfo.getMerMtz());//门头照
        //03-商户联系人身份证正面
        //picMap.put("03",smallMerchantVO.getRxm());
        //04-商户联系人身份证反面
        //picMap.put("04",smallMerchantVO.getGhm());
        //15-清算授权书
        //picMap.put("15",smallMerchantVO.getQssqs());
        //14-银行卡
        //picMap.put("04",smallMerchantVO.getAccount());

        String result  = merchantManagement.initSmallMerchant(channelMerchantInfo,picMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String retCode = String.valueOf(jsonObject.get("retCode"));
        if("0000".equals(retCode)){
            String merTrace = String.valueOf(jsonObject.get("merTrace"));//商户唯一标识
            log.info("小微商户进件返回商户唯一标识:{}",merTrace);
            /*resultVO.setMessage(SystemEnum.SUCCESS.getMessage());
            resultVO.setCode(SystemEnum.SUCCESS.getCode());
            resultVO.setData("success");*/
        }else{
            String retMsg = String.valueOf(jsonObject.get("retMsg"));
           /* resultVO.setMessage(retMsg);
            resultVO.setCode(retCode);
            resultVO.setData(retMsg);*/
        }
    }


    /**
     * 商户进件
     * @param merchantInfo
     * @param channelMerchantInfo
     * @return
     */
    @Override
    public void baseMerchantReg(MerchantInfo merchantInfo, ChannelMerchantInfo channelMerchantInfo) throws Exception{


    }

    @Override
    public int deleteMercByNos(String[] mercNos) {
        return this.merchantInfoMapper.deleteMercByNos(mercNos);
    }

    @Override
    public List<ExtMerchantInfo> queryExtMerchantInfo(ExtMerchantInfo extMerchantInfo) {
        return this.extMerchantInfoMapper.selectChannelMerchantList(extMerchantInfo);
    }


}
