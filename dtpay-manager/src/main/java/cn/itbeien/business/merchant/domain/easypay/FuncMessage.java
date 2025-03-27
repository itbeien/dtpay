package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 15:45
 * Copyright© 2024 beien
 * 功能信息
 */
@Data
public class FuncMessage {
    /**
     * 支付宝功能ID：2
     * 微信功能ID:3
     * 银联二维码功能ID：12
     */
    private String funcId;
    private String calcVal;
    private String state;
    private String retMsg;
    /**
     * 借记卡扣率方式 :1-封顶;0-不封顶
     * 银联二维码
     */
    private String DStlmType;

}
