package cn.itbeien.business.util.zxingqr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author beien
 * @date 2024-03-12 10:39
 * Copyright© 2024 beien
 */
public class ZxingCode {
    public static void main(String[] args) {
        int width=500;
        int height=500;
        String format ="png";
        String content="http://www.csdn.net";
        //定义二维码参数
        Map hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN,2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height);
            Path file = new File("D:\\creator\\creation\\code\\itbeien\\backend\\dtpay\\QR\\123.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,format,file);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}