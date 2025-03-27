package cn.itbeien.terminal.dao;

import java.util.List;

import cn.itbeien.terminal.entity.QrCodeMerchant;
import cn.itbeien.terminal.vo.QrCodeMerchantVO;

public interface QrCodeMerchantMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(QrCodeMerchant row);

    int insertSelective(QrCodeMerchant row);

    QrCodeMerchant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QrCodeMerchant row);

    int updateByPrimaryKey(QrCodeMerchant row);

    QrCodeMerchant selectByQrCode(String qrCode);

    public List<QrCodeMerchantVO> selectByMerchantCode(String merCode);


    public QrCodeMerchant selectMerchantByCode(String merNo);

}