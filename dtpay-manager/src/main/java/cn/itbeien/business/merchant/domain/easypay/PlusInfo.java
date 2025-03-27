package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 15:29
 * Copyright© 2024 beien
 * 附加信息
 * 该字段为商户的法人身份证等信息，JSON格式字符串，详情如下：
 */
@Data
public class PlusInfo {
    /**
     * 法定代表人姓名
     */
    private String merLegal;
    /**
     * 证件类型：
     * 0-居民身份证或临时身份证;
     * 1-外国公民护照;
     * 2-港澳居民来往大陆通行证或其他有效旅游证件；
     * 3-其他类个人身份有效证件；
     * 4-单位证件；
     * 5-军人或武警身份证件 accType为个人时必填
     */
    private String legalType;
    /**
     * 法人证件号码
     */
    private String legalCode;

    private String legalValidity;
    /**
     * 手机号
     */
    private  String legalPhone;
    /**
     * 商户联系人姓名
     */
    private String linkMan;
    /**
     * 证件类型：
     * 0-居民身份证或临时身份证;
     * 1-外国公民护照;
     * 2-港澳居民来往大陆通行证或其他有效旅游证件；
     * 3-其他类个人身份有效证件；
     * 4-单位证件；
     * 5-军人或武警身份证件
     */
    private String linkmanType;
    /**
     * 证件号码
     */
    private String linkmanCode;
    /**
     * 证件有效期 格式：[“起始日期”,”截止日期”],
     * JSON格式字符串 ，若为长期，都传2039.01.01
     */
    private String linkmanValidity;
    /**
     * 手机号
     */
    private String linkmanPhone;

}
