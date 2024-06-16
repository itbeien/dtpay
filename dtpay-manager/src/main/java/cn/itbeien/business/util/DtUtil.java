package cn.itbeien.business.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author beien
 * @date 2024-04-04 19:07
 * Copyright© 2024 beien
 */
public class DtUtil {

    public static String uuid(){
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        String uuidNoHyphenStr = uuidStr.replaceAll("-", "");
        return uuidNoHyphenStr;
    }

    public static Integer random(){
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000); // 生成一个6位数，范围在100000到999999之间
        return randomNumber;
    }
}
