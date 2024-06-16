package cn.itbeien.business.qrcode.service;

import cn.itbeien.business.qrcode.domain.QrCodeMerchant;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 11:17
 * Copyright© 2024 beien
 */
public interface IQrCodeDeviceService {
    List<QrCodeMerchant> selectQrCodeDeviceList(QrCodeMerchant qrCodeMerchant);

    public int deleteQrDeviceByIds(Integer[] qrIds);

    public int updateQrCodeDevice(QrCodeMerchant qrCodeMerchant);

    QrCodeMerchant selectQrDeviceById(Integer id);
}
