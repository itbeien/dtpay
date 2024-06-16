package cn.itbeien.business.util;

import java.lang.reflect.Method;

/**
 * @author beien
 * @date 2024-04-04 16:31
 * Copyright© 2024 beien
 */
public class Base64Util {
    /***
     * encode by Base64
     */
    public static String encodeBase64(byte[]input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }
    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, input);
        return (byte[])retObj;
    }

    public static void main(String[] args) throws Exception{
        String str = "ddddd";

       String encode = encodeBase64(str.getBytes());
        System.out.println(encode);
       String decode = new String(decodeBase64(encode));
        System.out.println(decode);
    }

}
