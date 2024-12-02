package cn.itbeien.terminal.service;



import cn.itbeien.terminal.entity.MerchantInfo;
import cn.itbeien.terminal.vo.BaseMerchantVO;
import cn.itbeien.terminal.vo.SmallMerchantVO;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IMerchantService {

    /**
     * 小微商户进件---小程序端进件
     * @param merchantInfo
     * @return
     */
    void merchantReg(MerchantInfo merchantInfo, SmallMerchantVO smallMerchantVO) throws Exception;
    void baseMerchantReg(MerchantInfo merchantInfo, BaseMerchantVO baseMerchantVO) throws Exception;

    MerchantInfo findMerchantByCreator(String uid);

    MerchantInfo findBaseMerchantByCreator(String uid);


}
