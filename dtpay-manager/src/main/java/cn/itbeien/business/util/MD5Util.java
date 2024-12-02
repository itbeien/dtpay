package cn.itbeien.business.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 上游机构MD5
 * Copyright© 2024 itbeien
 */
@Slf4j
public class MD5Util {
    /**
     * 鉴权签名
     */
    public static String getSign(String key, Map<String, String> map, String encode) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String>entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {//空值不参与加密
                list.add(entry.getKey() + "=" + entry.getValue() + "&");// key=value+
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        try {
            log.info("md5之前：\n"+result);
            result = MD5Util.crypt(result, encode);
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Encodes a string 2 MD5
     *
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String crypt(String str, String encode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(encode));
        byte[] hash = md.digest();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString().toUpperCase();
    }
}
