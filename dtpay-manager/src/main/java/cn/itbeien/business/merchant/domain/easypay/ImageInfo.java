package cn.itbeien.business.merchant.domain.easypay;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-27 6:24
 * Copyright© 2024 beien
 * 调用图片上传接口报备商户的图片信息
 */
@Data
public class ImageInfo {
    /**
     * 版本号1.0
     */
    private String version;
    /**
     * 图片文件
     */
    private String fileName;

    private String clientCode;
    /**
     * MAC值
     */
    private String MAC;
    /**
     * 图片类型
     */
    private String picMode;
    /**
     * 返回码
     */
    private String retCode;
    /**
     * 错误描述
     */
    private String retMsg;
    /**
     * 图片唯一ID 返回码为0000时，返回该字段
     */
    private String fileId;

}
