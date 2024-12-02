package cn.itbeien.business.qrcode.service;

import cn.itbeien.business.qrcode.domain.QrCode;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface IQrCodeService {
    List<QrCode> selectQrCodeList(QrCode qrCode);

    public void saveQR(Integer qrNumber,String qrCodeUrl,String qrLocation);

    public int deleteQrByIds(Integer[] qrIds);

    public int updateQrCode(QrCode post);

    QrCode selectQrById(Integer id);
}
