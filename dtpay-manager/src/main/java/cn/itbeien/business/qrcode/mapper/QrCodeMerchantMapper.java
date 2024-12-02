package cn.itbeien.business.qrcode.mapper;

import cn.itbeien.business.qrcode.domain.QrCodeMerchant;

import java.util.List;

public interface QrCodeMerchantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QrCodeMerchant row);

    int insertSelective(QrCodeMerchant row);


    QrCodeMerchant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QrCodeMerchant row);

    int updateByPrimaryKey(QrCodeMerchant row);
    List<QrCodeMerchant> selectQrCodeDeviceList(QrCodeMerchant qrCodeMerchant);

    int deleteQrDeviceByIds(Integer [] ids);
}