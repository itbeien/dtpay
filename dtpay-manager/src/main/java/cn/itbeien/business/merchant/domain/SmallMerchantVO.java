package cn.itbeien.business.merchant.domain;

import lombok.Data;

/**
 * @author beien
 * @date 2024-04-24 20:02
 * Copyright© 2024 beien
 */
@Data
public class SmallMerchantVO {
    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 开户行行号
     */
    private String bankCode;
    /**
     * 银行账号
     */
    private String account;
    /**
     * 账户名
     */
    private String accName;
    /**
     * 银行账户类型(结算账户)
     */
    private String accType;
    /**
     * 法人身份证人像面
     */
    private String rxm;
    /**
     * 法人身份证国徽面
     */
    private String ghm;

    /**
     * 证件类型
     */
    private String controlerLegalType;
    /**
     * 姓名,联系人
     */
    private String merLegal;
    /**
     * 证件号码
     */
    private String legalCode;
    /**
     * 联系人电话
     */
    private String telphone;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 店铺地址 areacode
     */
    private String address;
    /**
     * 店铺详细地址
     */
    private String addressDetail;
    /**
     * 所属行业 mcc
     */
    private String industries;
    /**
     * 营业场所门头照
     */
    private String mtz;
    /**
     * 内部环境照
     */
    private String hjz;

    /**
     * 收银台
     */
    private String syt;
    /**
     *清算授权书
     */
    private String qssqs;

    /**
     * 证件有效期
     */
    private String legalValidity;

    /**
     * 公司规模:1：0-50人；2：50-100人；3:100以上
     */
    private String employeeNum;
    /**
     * 注册资本
     */
    private String capital;
    /**
     * 经营形态 经营形态：02-普通店、01-连锁店
     */
    private String businForm;


}
