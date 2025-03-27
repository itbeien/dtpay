package cn.itbeien.business.qrcode.mapper;

import cn.itbeien.business.qrcode.domain.QrCode;

import java.util.List;

public interface QrCodeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(QrCode row);

    int insertSelective(QrCode row);

    QrCode selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(QrCode row);

    int updateByPrimaryKey(QrCode row);

    List<QrCode> selectQrCodeList(QrCode qrCode);

    int deleteQrByIds(Integer [] qrIds);

    public int updateQrCode(QrCode qrCode);
}