package cn.itbeien.business.qrcode.service.impl;

import cn.itbeien.business.enums.QREnum;
import cn.itbeien.business.qrcode.domain.QrCode;
import cn.itbeien.business.qrcode.mapper.QrCodeMapper;
import cn.itbeien.business.qrcode.service.IQrCodeService;
import cn.itbeien.business.util.Base64Util;
import cn.itbeien.business.util.DtUtil;
import cn.itbeien.business.util.zxingqr.QrcodeUtil;
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
    private QrCodeMapper qrCodeMapper;
    @Override
    public List<QrCode> selectQrCodeList(QrCode qrCode) {
        return qrCodeMapper.selectQrCodeList(qrCode);
    }

    /**
     *
     * @param qrNumber  创建聚合二维码的数量
     * @param qrCodeUrl 聚合二维码中的内容
     * @param qrLocation 聚合二维码存放的物理路径
     * @return
     * @throws Exception
     */
    @Override
    public void saveQR(Integer qrNumber,String qrCodeUrl,String baseQrLocation) {

        for(int i=0;i<qrNumber;i++){//批量创建聚合二维码

            String qrCode = DtUtil.uuid();
            String qrCodeBase64 = null;
            try {
                qrCodeBase64 = Base64Util.encodeBase64(qrCode.getBytes());

            //二维码内容为域名地址+base64的二维码编号
            //system.qrcode.url=http://外网域名/pay/auth?code=%s
                String  content = String.format(qrCodeUrl,qrCodeBase64);
                //qrcode_location=D:\dtpay\qrimg\%s

                String qrLocation = String.format(baseQrLocation+"/"+qrCode+".png");
                Path file = new File(qrLocation).toPath();
                QrcodeUtil.createQr(content,file);

                String profile = "/profile/"+qrCode+".png";

                QrCode dtQrCode = new QrCode();
                dtQrCode.setQrCode(qrCode);
                dtQrCode.setQrStatus(QREnum.STATUS_ENABLE.getCode());
                dtQrCode.setCreateBy(Long.parseLong(QREnum.SYSTEM.getCode()));
                dtQrCode.setCreateUser(QREnum.SYSTEM.getName());
                dtQrCode.setCreateTime(new Date());
                //dtQrCode.setQrUrl(qrLocation);
                dtQrCode.setQrUrl(profile);
                qrCodeMapper.insertSelective(dtQrCode);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int deleteQrByIds(Integer[] qrIds) {
        return qrCodeMapper.deleteQrByIds(qrIds);
    }

    @Override
    public int updateQrCode(QrCode qrCode) {
        return qrCodeMapper.updateQrCode(qrCode);
    }

    @Override
    public QrCode selectQrById(Integer id) {
        return qrCodeMapper.selectByPrimaryKey(id);
    }
}
