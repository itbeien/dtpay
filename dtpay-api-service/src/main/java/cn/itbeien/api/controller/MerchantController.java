package cn.itbeien.api.controller;

import cn.itbeien.api.entity.merchant.MerchantInfo;
import cn.itbeien.api.enums.SystemEnum;
import cn.itbeien.api.service.merchant.IMerchantService;
import cn.itbeien.api.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class MerchantController {

    @Autowired
    private IMerchantService  merchantService;

    @RequestMapping("/test/merchant/merchantSave")
    public ResultVO testMerchantSave(@RequestBody String data){
        ResultVO<String> resultVO = new ResultVO<>();
        try{
            MerchantInfo merchantInfo = JSONObject.toJavaObject(JSONObject.parseObject(data), MerchantInfo.class);
            this.merchantService.saveMerchant(merchantInfo);
        }catch (Exception e){
            log.error("新增商户异常:{}",e);
            resultVO.setData(null);
            resultVO.setMessage(SystemEnum.ERROR.getMessage());
            resultVO.setCode(SystemEnum.ERROR.getCode());
        }
        return resultVO;
    }

    public static void main(String[] args) {
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setMercNo("100000001");
        merchantInfo.setAgentNo("1001");
        merchantInfo.setMercName("测试商户001");
        merchantInfo.setMercNickName("测试商户001");
        merchantInfo.setClearRate("0.30");
        merchantInfo.setStatus("01");
        merchantInfo.setProvince("110000");
        merchantInfo.setCity("110101");
        merchantInfo.setCorpAddr("北京市朝阳区");
        merchantInfo.setCreateTime(new java.util.Date());
        merchantInfo.setCreator("jmeter");
        System.out.println(JSON.toJSONString(merchantInfo));
    }
}
