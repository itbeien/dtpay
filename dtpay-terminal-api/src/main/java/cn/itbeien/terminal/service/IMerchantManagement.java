package cn.itbeien.terminal.service;


import cn.itbeien.terminal.vo.ImageInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IMerchantManagement {

    public String merchantImgUpload(MultipartFile multipartFile, ImageInfo imageInfo);

}
