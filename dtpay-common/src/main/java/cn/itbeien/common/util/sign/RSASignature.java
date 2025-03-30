package cn.itbeien.common.util.sign;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * RSA签名验签类
 */
public class RSASignature {

	/**
	 * 签名算法
	 */
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	
	

	/**
	 * RSA签名
	 * 
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            商户私钥
	 * @param encode
	 *            字符集编码
	 * @return 签名值
	 */
	public static String sign(String content, PrivateKey privateKey, String encode) {
		try {
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(privateKey);
			signature.update(content.getBytes(encode));
			byte[] signed = signature.sign();
			return Base64.encodeBase64String(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sign(String content, PrivateKey privateKey) {
		return sign(content, privateKey, "UTF-8");
	}
	
	/**
	 * 公玥加密
	 * @param value
	 * @param publicKey
	 * @return
	 */
	public static String encryptByPublicKey(String value,PublicKey publicKey, String encode){
		String encryp = "";
		try {
			Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // 定义算法：RSA  
		    c.init(Cipher.ENCRYPT_MODE, publicKey);
		    byte[] rsaBytes = c.doFinal(value.getBytes(encode));
		    encryp = new String(Base64.encodeBase64(rsaBytes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryp;
	}
	
	public static String encryptByPublicKey(String value,PublicKey publicKey){
		return encryptByPublicKey(value,publicKey,"UTF-8");
	}
	
	/**
	 * 私玥解密
	 * @param value
	 * @param privateKey
	 * @return
	 */
	public static String decryptByPrivateKey(String value,PrivateKey privateKey, String encode){
		String decryp = "";
		try {
			Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");  
		    c.init(Cipher.DECRYPT_MODE, privateKey);  
		    byte[] rsaBytes = c.doFinal(Base64.decodeBase64(value));
		    decryp = new String(rsaBytes,encode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decryp;
	}
	
	public static String decryptByPrivateKey(String value,PrivateKey privateKey){
		return decryptByPrivateKey(value,privateKey,"UTF-8");
	}
	

	/**
	 * RSA验签名检查
	 * 
	 * @param content
	 *            待签名数据
	 * @param sign
	 *            签名值
	 * @param publicKey
	 *            分配给开发商公钥
	 * @param encode
	 *            字符集编码
	 * @return 布尔值
	 */
	public static boolean doCheck(String content, String sign, PublicKey publicKey, String encode) {
		try {
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initVerify(publicKey);
			signature.update(content.getBytes(encode));
			boolean bverify = signature.verify(Base64.decodeBase64(sign));
			return bverify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean doCheck(String content, String sign, PublicKey publicKey) {
		return doCheck(content,sign,publicKey,"UTF-8");
	}
}