package cn.itbeien.common.util.sign;

import java.security.MessageDigest;

public class MD5Signature {

	/**
	 * 签名算法
	 */
	public static final String SIGN_ALGORITHMS = "MD5";
	
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * MD5签名
	 * @param content
	 * @param privateKey
	 * @param encode
	 * @return
	 */
	public static String sign(String content, String privateKey, String encode) {
		try {
			byte[] btInput = (content+privateKey).getBytes(encode);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String content = "charset=UTF-8&feeType=CNY&interfaceCode=pay&ip=127.0.0.1&locale=CN&mercNo=RX1538841210584&mercOrderNo=215400254505454455872¬iceStr=da2d605158c2ca38e205fb1d707211a9¬ifyUrl=https%3A%2F%2Fwww.baidu.com%2F&orderPeriod=15&paywayCode=alipay&returnUrl=http%3A%2F%2Fwww.baidu.com&sceneCode=h5&signType=MD5&subject=%E6%B5%8B%E8%AF%95%E4%B8%8B%E5%8D%95&termType=wap&tradeAmt=0.01&tradeTime=2018-10-17+15%3A27%3A57&tradeType=01&version=1.0";
		String privateKey = "B44E02B5F7DE41D7A7913CCBE8BF6768";
		String encode = "UTF-8";
		System.out.println(sign(content, privateKey, encode));
	}
	
	public static String sign(String content, String privateKey) {
		return sign(content, privateKey, "UTF-8");
	}
	
	/**
	 * MD5验签
	 * @param content
	 * @param sign
	 * @param privateKey
	 * @param encode
	 * @return
	 */
	public static boolean doCheck(String content, String sign, String privateKey, String encode) {
		try {
			String genSignature = sign(content,privateKey,encode);
			boolean bverify = genSignature.equals(sign)?true:false;
			return bverify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean doCheck(String content, String sign, String privateKey) {
		return doCheck(content,sign,privateKey,"UTF-8");
	}
}
