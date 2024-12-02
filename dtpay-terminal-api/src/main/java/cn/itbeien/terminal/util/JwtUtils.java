package cn.itbeien.terminal.util;

import cn.itbeien.terminal.entity.SysUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 基于jwt实现接口访问权限的控制
 * 1.生成token(令牌)
 * 2.对token进行校验
 * Copyright© 2024 itbeien
 */

public class JwtUtils {
    public static final long EXPIRE_TIME = 60L* 60 * 1000*25*365;

    public static final String SECRET = "dt-union-pay20230922";

    /**
     * <p>根据用户类生成token</p>
     * @param user
     * @return
     */
    public static String sign(SysUser user, String secret){
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withClaim("name", user.getPhonenumber())
                .withClaim("uid",user.getUserId())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * <p>根据用户类生成token</p>
     * @param phone
     * @return
     */
    public static String signUid(String uid, String secret){
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withClaim("uid", uid)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 校验token,校验成功返回true,校验失败返回false
     * @param token
     * @return
     */
    public static boolean verify(String token,String secret){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT  = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 根据客户端传入的token获取用户信息
     * @param token
     * @return
     */
    public static String getUserByToken(String token){
        String uid = null;
        try {
            DecodedJWT decodedJWT =  JWT.decode(token);
            uid = decodedJWT.getClaim("uid").asString();
            return uid;
        }catch (Exception e){
            return uid;
        }
    }

    public static void main(String[] args) {
        SysUser user = new SysUser();
        user.setUserName("abc");
        user.setUserId(11l);
        //生成token
        String token  = JwtUtils.signUid("11111",JwtUtils.SECRET);
        System.out.println(token);
        getUserByToken(token);//获取uid
        //System.out.println(JwtUtils.verify(token));
    }
}
