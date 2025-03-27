package cn.itbeien.business.qrcode.service;

import cn.itbeien.business.qrcode.domain.QrCodeMerchant;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IQrCodeDeviceService {
    List<QrCodeMerchant> selectQrCodeDeviceList(QrCodeMerchant qrCodeMerchant);

    public int deleteQrDeviceByIds(Integer[] qrIds);

    public int updateQrCodeDevice(QrCodeMerchant qrCodeMerchant);

    QrCodeMerchant selectQrDeviceById(Integer id);
}
