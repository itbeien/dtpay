package cn.itbeien.terminal.service.impl;


import cn.itbeien.common.utils.Base64Util;
import cn.itbeien.common.utils.qr.QrcodeUtil;
import cn.itbeien.terminal.dao.DtQrCodeMapper;
import cn.itbeien.terminal.dao.QrCodeMerchantMapper;
import cn.itbeien.terminal.entity.DtQrCode;
import cn.itbeien.terminal.entity.QrCodeMerchant;
import cn.itbeien.terminal.enums.QREnum;
import cn.itbeien.terminal.service.IQrCodeService;
import cn.itbeien.terminal.util.DtUtil;
import cn.itbeien.terminal.vo.QrCodeMerchantVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
public class QrCodeService implements IQrCodeService {
    @Resource
    private DtQrCodeMapper dtQrCodeMapper;

    @Resource
    private QrCodeMerchantMapper qrCodeMerchantMapper;

    /**
     *
     * @param qrNumber  创建聚合二维码的数量
     * @param qrCodeUrl 聚合二维码中的内容
     * @param baseQrLocation 聚合二维码存放的物理路径
     * @return
     * @throws Exception
     */
    @Override
    public void saveQR(Integer qrNumber,String qrCodeUrl,String baseQrLocation) throws Exception{

        for(int i=0;i<qrNumber;i++){//批量创建聚合二维码

            String qrCode = DtUtil.uuid();
            String qrCodeBase64 = Base64Util.encodeBase64(qrCode.getBytes());
            //二维码内容为域名地址+base64的二维码编号
            //system.qrcode.url=http://外网域名/pay/auth?code=%s
            String  content = String.format(qrCodeUrl,qrCodeBase64);
            //qrcode_location=D:\dtpay\qrimg\%s

            String qrLocation = String.format(baseQrLocation,qrCode+".png");
            Path file = new File(qrLocation).toPath();
            QrcodeUtil.createQr(content,file);

            DtQrCode dtQrCode = new DtQrCode();
            dtQrCode.setQrCode(qrCode);
            dtQrCode.setQrStatus(QREnum.STATUS_ENABLE.getCode());
            dtQrCode.setCreateBy(Long.parseLong(QREnum.SYSTEM.getCode()));
            dtQrCode.setCreateUser(QREnum.SYSTEM.getName());
            dtQrCode.setCreateTime(new Date());
            dtQrCode.setQrUrl(qrLocation);
            dtQrCodeMapper.insertSelective(dtQrCode);
        }
    }

    @Override
    public QrCodeMerchant selectByQrCode(String qrCode) {
        return qrCodeMerchantMapper.selectByQrCode(qrCode);
    }

    @Override
    public int saveQrCodeMerchant(QrCodeMerchant qrCodeMerchant) {
        return this.qrCodeMerchantMapper.insertSelective(qrCodeMerchant);
    }

    @Override
    public List<QrCodeMerchantVO> selectByMerchantCode(String merCode) {
        return this.qrCodeMerchantMapper.selectByMerchantCode(merCode);
    }

    public QrCodeMerchant selectByQrCodeMerchantByNo(String merchantCode) {
        return  this.qrCodeMerchantMapper.selectMerchantByCode(merchantCode);
    }
}
