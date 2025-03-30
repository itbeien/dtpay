package cn.itbeien.common.util;

import java.util.ArrayList;
import java.util.List;


/**
 *  Administrator
 *
 */
public class EncryptionField {
	public static List<String> encryptFields = new ArrayList<String>();
	
	static{
		encryptFields.add("toAcctNo");
		encryptFields.add("toAcctName");
		encryptFields.add("cardNo");
		encryptFields.add("accountCard");
		encryptFields.add("accountName");
		encryptFields.add("mobileNo");
	}
	
}
