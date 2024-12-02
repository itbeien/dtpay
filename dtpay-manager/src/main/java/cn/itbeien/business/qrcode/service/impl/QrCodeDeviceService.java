package cn.itbeien.business.qrcode.service.impl;

import cn.itbeien.business.qrcode.domain.QrCodeMerchant;
import cn.itbeien.business.qrcode.mapper.QrCodeMerchantMapper;
import cn.itbeien.business.qrcode.service.IQrCodeDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
public class QrCodeDeviceService implements IQrCodeDeviceService {
    @Resource
    private QrCodeMerchantMapper qrCodeMerchantMapper;
    @Override
    public List<QrCodeMerchant> selectQrCodeDeviceList(QrCodeMerchant qrCodeMerchant) {
        return qrCodeMerchantMapper.selectQrCodeDeviceList(qrCodeMerchant);
    }

    @Override
    public int deleteQrDeviceByIds(Integer[] qrIds) {
        return qrCodeMerchantMapper.deleteQrDeviceByIds(qrIds);
    }

    @Override
    public int updateQrCodeDevice(QrCodeMerchant qrCodeMerchant) {
        return qrCodeMerchantMapper.updateByPrimaryKeySelective(qrCodeMerchant);
    }

    @Override
    public QrCodeMerchant selectQrDeviceById(Integer id) {
        return qrCodeMerchantMapper.selectByPrimaryKey(id);
    }
}
