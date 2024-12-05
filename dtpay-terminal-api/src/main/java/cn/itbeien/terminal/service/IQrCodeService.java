package cn.itbeien.terminal.service;


import cn.itbeien.terminal.entity.QrCodeMerchant;
import cn.itbeien.terminal.vo.QrCodeMerchantVO;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IQrCodeService {

    public void saveQR(Integer qrNumber,String qrCodeUrl,String qrLocation) throws Exception;


    public QrCodeMerchant selectByQrCode(String qrCode);


    public int saveQrCodeMerchant(QrCodeMerchant qrCodeMerchant);

    public List<QrCodeMerchantVO> selectByMerchantCode(String merCode);

}
