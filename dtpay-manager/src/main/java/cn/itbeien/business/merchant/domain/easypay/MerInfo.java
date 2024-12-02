package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 15:16
 * Copyright© 2024 beien
 * 商户信息
 */
@Data
public class MerInfo {
    private String merCode;
    private String merMode;
    private String merName;
    private String businName;
    private String merType;
    private String standardFlag;
    private String merArea;
    private String merAddr;
    private String businBegtime;
    private String businEndtime;
    private String employeeNum;
    private String businForm;

}
