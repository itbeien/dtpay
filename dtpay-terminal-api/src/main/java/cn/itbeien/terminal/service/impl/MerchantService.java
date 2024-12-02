package cn.itbeien.terminal.service.impl;


import cn.itbeien.terminal.dao.MerchantInfoMapper;
import cn.itbeien.terminal.entity.MerchantInfo;
import cn.itbeien.terminal.enums.MerchantEnum;
import cn.itbeien.terminal.service.IMerchantService;
import cn.itbeien.terminal.util.SnowFlake;
import cn.itbeien.terminal.vo.BaseMerchantVO;
import cn.itbeien.terminal.vo.MerchantConvert;
import cn.itbeien.terminal.vo.SmallMerchantVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
@Slf4j
public class MerchantService implements IMerchantService {

    @Value("${snowFlake.workerId}")
    private long workerId;
    @Value("${snowFlake.datacenterId}")
    private long datacenterId;
    @Value("${snowFlake.sequence}")
    private long sequence;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    private SnowFlake snowFlake = new SnowFlake(workerId,datacenterId,sequence);


    /**
     * 商户在小程序端自助发起进件
     * @param merchantInfo
     * @return
     */
    @Override
    public void merchantReg(MerchantInfo merchantInfo, SmallMerchantVO smallMerchantVO) throws Exception{

        String creator = merchantInfo.getCreator();
        // 进行转换
        merchantInfo = MerchantConvert.INSTANCE.convertMerchant(smallMerchantVO);
        merchantInfo.setCreator(creator);

        //商户编号如果为空则执行新增
        int result = 0;
        if(StringUtils.isEmpty(smallMerchantVO.getMerchantNo())){
            //生成商户号
            long mercNo =  snowFlake.nextId();
            //商户初始状态为待审核
            merchantInfo.setStatus(MerchantEnum.STATE_AUDIT.getCode());
            merchantInfo.setMercNo(String.valueOf(mercNo));
            result  = this.merchantInfoMapper.insertSelective(merchantInfo);
            log.info("小微商户进件返回商户唯一标识:{}",merchantInfo.getMercNo());
        }else{//有商户号，则之前保存过，进行修改
            //设置商户编号
            merchantInfo.setMercNo(smallMerchantVO.getMerchantNo());
            //商户信息修改后修改状态为驳回状态
            merchantInfo.setStatus(MerchantEnum.STATE_NO_PASS.getCode());
            result = this.merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
        }


        if(result<=0){
            throw new Exception("商户进件失败");
        }
    }

    @Override
    public MerchantInfo findMerchantByCreator(String uid) {
        return this.merchantInfoMapper.selectSmallByCreator(uid);
    }



    /**
     * 商户在小程序端发起进件---标准商户
     * @param merchantInfo
     * @param baseMerchantVO
     * @return
     */
    @Override
    public void baseMerchantReg(MerchantInfo merchantInfo, BaseMerchantVO baseMerchantVO) throws Exception{

        //buildBaseMerchantInfo(merchantInfo,baseMerchantVO);
        String creator = merchantInfo.getCreator();
        // 进行转换
        merchantInfo = MerchantConvert.INSTANCE.convertMerchant(baseMerchantVO);
        merchantInfo.setCreator(creator);
        int result = 0;
        //商户编号如果为空则执行新增
        if(StringUtils.isEmpty(baseMerchantVO.getMerchantNo())){
            //生成商户号
            long mercNo =  snowFlake.nextId();
            merchantInfo.setMercNo(String.valueOf(mercNo));
            //商户初始状态为待审核
            merchantInfo.setStatus(MerchantEnum.STATE_AUDIT.getCode());
            result  = this.merchantInfoMapper.insertSelective(merchantInfo);
            log.info("标准商户进件返回商户唯一标识:{}",merchantInfo.getMercNo());
        }else{//有商户号，则之前保存过，进行修改
            //设置商户编号
            merchantInfo.setMercNo(baseMerchantVO.getMerchantNo());
            //商户信息修改后修改状态为驳回状态
            merchantInfo.setStatus(MerchantEnum.STATE_NO_PASS.getCode());
            result = this.merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
        }

        if(result<=0){
            throw new Exception("标准商户进件失败");
        }
    }


    @Override
    public MerchantInfo findBaseMerchantByCreator(String uid) {
        return this.merchantInfoMapper.selectBaseByCreator(uid);
    }




    public static void main(String[] args) {

        SmallMerchantVO smallMerchantVO = new SmallMerchantVO();
        smallMerchantVO.setRegName("测试");

        // 进行转换
        MerchantInfo merchantInfo = MerchantConvert.INSTANCE.convertMerchant(smallMerchantVO);
        System.out.println(merchantInfo.getMercName());
        System.out.println(merchantInfo.getMerMode());
        System.out.println(merchantInfo.getCreateTime());
    }



}
