package cn.itbeien.common.utils.sign;
import com.alibaba.fastjson2.JSONObject;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;


/**
 * @author beien
 * @date 2024-03-10 21:25
 * Copyright© 2024 beien
 */
public class RSAUtil {

    public static final String SIGN_ALGORITHMS = "SHA256WithRSA";
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;


    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }


    /**
     * 签名
     *
     * @param content
     * @param privateKey
     * @return
     */
    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));
            byte[] signed = signature.sign();
            return Base64.getEncoder().encodeToString(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验签
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean verify(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.getDecoder().decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 公钥分段加密
     *
     * @param content
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static String publicEncrpyt(String content, String publicKeyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, getPublicKey(publicKeyStr));
        byte[] bytes = content.getBytes(DEFAULT_CHARSET);

        int inputLen = bytes.length;
        int offSet = 0;
        byte[] cache;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);
    }


    /**
     * 私钥分段加密
     *
     * @param content
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static String privateEncrpyt(String content, String privateKeyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, getPrivateKey(privateKeyStr));
        byte[] bytes = content.getBytes(DEFAULT_CHARSET);

        int inputLen = bytes.length;
        int offSet = 0;
        byte[] cache;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 私钥分段解密
     *
     * @param content
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static String privateDecrypt(String content, String privateKeyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, getPrivateKey(privateKeyStr));
        byte[] bytes = Base64.getDecoder().decode(content);
        int inputLen = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    /**
     * 公钥分段解密
     *
     * @param content
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static String publicDecrypt(String content, String publicKeyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, getPublicKey(publicKeyStr));
        byte[] bytes = Base64.getDecoder().decode(content);
        int inputLen = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }


    /**
     * 排序拼接需要签名的数据
     * @param data
     * @return
     */
    public static String getReqStr(JSONObject data){
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            // 参数值为null，则不参与签名
            if (data.get(k) != null ) {
                sb.append(k).append("=").append(data.get(k).toString().trim()).append("&");
            }
        }
        return sb.toString().substring(0,sb.toString().lastIndexOf("&"));
    }


    public static void main(String[] args) throws Exception {
        String content = "SYxxkj123@";
        // 公钥216
        String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaLRe493205T6+xpTqD3q2xtLEut8GPS6ter1mp507S8MutmRJ8mn8oBykNVFz8ZKCsOGI1UJZS2jNpEBOuCTu7GXZ/uTCBmLDigS5rG3YzlMkg0EdnyKxTRRLjoPUyIWSVKGZlU5VC/zBvB03qKZ3HpzHZHxGG/3z6E8aoXoPRwIDAQAB";
        //私钥844
//        String privateKey ="";

        String s = publicEncrpyt(content, publicKey);
        System.out.println("公钥加密后"+s);

    }
}
