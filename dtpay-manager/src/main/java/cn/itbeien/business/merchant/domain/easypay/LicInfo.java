package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 6:17
 * Copyright© 2024 beien
 * 营业执照信息-商户的营业执照信息
 */
@Data
public class LicInfo {

    private String licName;
    /**
     * 工商注册号/统一社会信用码（营业执照）,merMode为0,1时，必传
     */
    private String merLic;
    /**
     * 默认：01 ；
     * 营业执照类型 01：
     * 统一社会信用码，
     * 02：其他证明文件，
     * 03：工商注册号 04：
     * 事业单位法人证书 05：
     * 民办非企业单位登记证书
     * 06：基金会法人登记证书
     */
    private String licenseType;
    /**
     * 营业执照有效期 格式：[“起始日期”,”截止日期”],JSON格式字符串 ,
     * merMode为0,1时，必传
     */
    private String licValidity;
    /**
     * 主营业务
     */
    private String businScope;
    /**
     * 注册资本：单位-万元
     */
    private String capital;
    /**
     * 注册地址
     */
    private String licAddr;
    /**
     * 控股股东或实际控制人姓名或名称
     */
    private String controlerName;
    /**
     * 控股股东或实际控制人证件号码
     */
    private String controlerLegalCode;
    /**
     * 控股股东或实际控制人证件种类：
     * 0-居民身份证或临时身份证;
     * 1-外国公民护照;
     * 2-港澳居民来往大陆通行证或其他有效旅游证件；
     * 3-其他类个人身份有效证件；
     * 4-单位证件；
     * 5-军人或武警身份证件
     */
    private String controlerLegalType;
    /**
     * 控股股东或实际控制人证件有效期
     */
    private String controlerLegalValidity;

}
