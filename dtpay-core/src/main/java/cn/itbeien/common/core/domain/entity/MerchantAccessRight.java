package cn.itbeien.common.core.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MerchantAccessRight implements Serializable {


    private static final long serialVersionUID = 1L;

    private String id;

    private String mercNo;

    private String interfaceCode;

    private String status;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;



}