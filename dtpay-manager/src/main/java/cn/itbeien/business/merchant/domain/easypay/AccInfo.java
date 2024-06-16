package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 15:44
 * Copyright© 2024 beien
 * 结算账户信息
 */
@Data
public class AccInfo {
    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 开户行行号
     */
    private String bankCode;
    private String account;
    /**
     * 账户名称
     */
    private String accName;
    /**
     * 	账户类型：00-个人(对私)；10-对公
     */
    private String accType;

    private String accArea;

    private String legalType;

    private String legalCode;

    private String accPhone;
}
