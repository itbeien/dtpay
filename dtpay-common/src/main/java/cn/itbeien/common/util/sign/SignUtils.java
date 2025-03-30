/**
 * Project Name:payment
 * File Name:SignUtils.java
 * Package Name:cn.swiftpass.utils.payment.sign
 * :2014-6-27下午3:22:33
 *
*/

package cn.itbeien.common.util.sign;

import cn.itbeien.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *  Administrator
 *
 */
@Slf4j
public class SignUtils {
	private static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	/**
	 * 公钥加密
	 * @param value
	 * @param publicKey
	 * @return
	 */
	public static String encryptByPublicKey(String value,PublicKey publicKey){
		
		return RSASignature.encryptByPublicKey(value, publicKey);
	}
	
	/**
	 * 私钥解密
	 * @param value
	 * @param privateKey
	 * @return
	 */
	public static String decryptByPrivateKey(String value,PrivateKey privateKey){
		
		return RSASignature.decryptByPrivateKey(value, privateKey);
	}
	

	/**
	 * 生成签名signature字符串
	 * 
	 * @param params
	 *            参数列表（包含signature参数）
	 * @param privateKey
	 *            商户私钥
	 * @return signature字符串
	 */
	public static String generateSignature(Map<String, Object> params, PrivateKey privateKey) {
		try {
			String signStr = generateParamStr(params);
			System.out.println("gate返回加签字符串："+signStr);
			String signature = RSASignature.sign(signStr, privateKey);
			return signature;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 验证服务器返回的信息中签名的正确性
	 * 
	 * @param params
	 *            参数列表（包含mac参数）
	 * @param publicKey
	 *            公钥
	 * @return true-验签通过，false-验签失败
	 */
	public static boolean verifySignature(Map<String, Object> params, PublicKey publicKey) {
		try {
			String signature = (String)params.get("signature");
			String signStr = generateParamStr(params);
			log.info("验签字符串："+signStr);
			return RSASignature.doCheck(signStr, signature, publicKey);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * SHA摘要算法，输入内容将被UTF-8编码
	 * 
	 * @param content
	 *            输入明文
	 * @return 内容摘要，40位16进制字符串
	 */
	public static String encryptBySHA(String content) {
		if (StringUtils.isBlank(content))
			return null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] output = md.digest(content.getBytes("UTF-8"));
			return bytesToHexStr(output);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成用于signature计算的参数字符串。<br>
	 * 
	 * @return 模式为key=value&key=value
	 */
	public static String generateParamStr(Map<String, Object> params) {
		// 取所有非空字段内容（除mac以外），塞入列表
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>(params);
		
		String content = SignUtils.toSignKVStr(sortedMap, new String[]{"signature"}, true);

		return content;
	}
	
	/**
     * 将sortedMap 转换成key/value使用&链接符连接的字符串
     * 
     * @param map
     *            源map
     * @param outStr
     *            连接时需要在map中排除的key
     *            是否排除值为空字符串的键值对
     * @return
     */
    public static String toSignKVStr(SortedMap<String, Object> map, String[] outStr, boolean isOutEmpty)
    {
        StringBuffer sb = new StringBuffer("");
        for (String key : map.keySet())
        {
            String value = String.valueOf(map.get(key));
            // key对应的值为NULL或包含排除key，则跳过
            if (value == null || "null".equals(value) || contains(outStr, key))
            {
                continue;
            }
            if (isOutEmpty && StringUtils.isEmpty(value))
            {
                continue;
            }
            sb.append(key).append("=").append(value).append("&");
        }
        String str = sb.toString().substring(0, sb.toString().length() - 1);
        return str;
    }
    
    /**
     * 过滤特殊字符
     * @param map
     */
    public static void filterSpecChar(Map<String, Object> map){
    	 String regEx="[`~!@#$*(){}';',<>~！@#￥……*（）——{}【】‘；：”“’。，、？]"; 
    	 Pattern p = Pattern.compile(regEx); 
         for (String key : map.keySet()){
        	 if(map.get(key) == null) {
	       		  continue;
	         }
             String value = String.valueOf(map.get(key));
             // key对应的值为NULL或包含排除key，则跳过
             if (value == null || StringUtils.isEmpty(value)|| contains(new String[]{"signature"}, key)) {
                 continue;
             }
             Matcher m = p.matcher(value);
             map.put(key, m.replaceAll("").trim());
         }
    }
    
    public static void main(String args[]) throws UnsupportedEncodingException{
    	SortedMap<String, Object> map = new TreeMap<String, Object>();
    	
    	String tt="http://www.xxxxxxxxx.com:8080/dtpay-client/notify.do";
    	tt = URLEncoder.encode(tt, "utf-8");
    	map.put("charset", tt);
    	System.out.println(map.toString());
    	filterSpecChar(map);
    	System.out.println(map.toString());
    }
    
    /**
     * 判断数组中是否存在目标字符串
     * 
     * @param array
     * @param str
     * @return ture or false
     */
    public static boolean contains(String[] array, String str)
    {
        if (array == null)
        {
            return false;
        }
        for (String temp : array)
        {
            if (temp.equals(str))
            {
                return true;
            }
        }
        return false;
    }

	/**
	 * 将byte数组转换为16进制格式的字符串
	 * 
	 * @param bytes
	 *            待转换数组
	 * @return 16进制格式的字符串
	 */
	private static String bytesToHexStr(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexChar[(bytes[i] & 0xf0) >>> 4]);
			sb.append(hexChar[bytes[i] & 0x0f]);
		}
		return sb.toString();
	}
}
