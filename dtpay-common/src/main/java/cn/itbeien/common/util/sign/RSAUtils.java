package cn.itbeien.common.util.sign;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * RSA证书读取工具类
 * 从文件读取证书、私钥或公钥，不包含签名和验签的方法
 *  Administrator
 *
 */
public class RSAUtils
{
    public static final String X509 = "X.509";

    /**
     * 根据pfx地址及密码得到私钥
     * 
     * @param strPfx    pfx 地址
     * @param strPassword   pfx 密码
     * @return
     */
    public static PrivateKey GetPvkformPfx(String strPfx, String strPassword)
    {
        try
        {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(strPfx);
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals(""))
            {
                nPassword = null;
            } else
            {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())
            {
                keyAlias = (String) enumas.nextElement();
            }
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            return prikey;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 根据pfx证书地址获取证书
     * @param strPfx    pfx证书地址
     * @param strPassword   pfx证书密码
     */
    public static Certificate getCertificateByPfx(String strPfx, String strPassword){
        try
        {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(strPfx);
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals(""))
            {
                nPassword = null;
            } else
            {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())
            {
                keyAlias = (String) enumas.nextElement();
            }
            Certificate cert = ks.getCertificate(keyAlias);

            return cert;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从证书中获取序列号
     * @param cert
     * @return
     */
    public static BigInteger getSerialNumber(Certificate cert){
        X509Certificate c = (X509Certificate)cert;
        return c.getSerialNumber();
    }
    

    /**
     * 根据文件地址，读取文件到二进制数组中
     * 
     * @param filePath
     *            文件地址
     * @return byte数组
     * @throws IOException
     */
    public static byte[] readKey(String filePath) throws IOException
    {
        FileInputStream crls;
        if (filePath == null)
        {
            throw new IllegalArgumentException("Illegal Argument: filePath");
        }
        crls = null;
        byte abyte0[];
        try
        {
            crls = new FileInputStream(filePath);
            byte out[] = new byte[crls.available()];
            byte buffer[] = new byte[1024];
            int rLength;
            for (int offset = 0; (rLength = crls.read(buffer, 0, buffer.length)) != -1; offset += rLength)
            {
                System.arraycopy(buffer, 0, out, offset, rLength);
            }
            abyte0 = out;
        } catch (IOException e)
        {
            throw e;
        } finally
        {
            if (crls != null)
            {
                try
                {
                    crls.close();
                } catch (Exception e)
                {
                }
            }
        }
        return abyte0;
    }

    /**
     * 获得Certificate
     * 
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static Certificate getCertificate(String certificatePath) throws Exception
    {
        CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
        FileInputStream in = new FileInputStream(certificatePath);

        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();

        return certificate;
    }
    

    /**
     * 根据cer地址得到公钥
     * 
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String certificatePath) throws Exception
    {
        Certificate certificate = getCertificate(certificatePath);
        PublicKey key = certificate.getPublicKey();
        return key;
    }
    

}
