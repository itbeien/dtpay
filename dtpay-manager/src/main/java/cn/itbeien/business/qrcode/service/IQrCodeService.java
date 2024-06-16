package cn.itbeien.business.qrcode.service;

import cn.itbeien.business.qrcode.domain.QrCode;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 11:17
 * Copyright© 2024 beien
 */
public interface IQrCodeService {
    List<QrCode> selectQrCodeList(QrCode qrCode);

    public void saveQR(Integer qrNumber,String qrCodeUrl,String qrLocation);

    public int deleteQrByIds(Integer[] qrIds);

    public int updateQrCode(QrCode post);

    QrCode selectQrById(Integer id);
}
