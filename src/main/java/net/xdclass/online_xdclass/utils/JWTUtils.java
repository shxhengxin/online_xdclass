package net.xdclass.online_xdclass.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xdclass.online_xdclass.model.entity.User;

import java.util.Date;

/**
 * @ClassName : JWTUtils  //类名
 * @Description : jwt工具类  //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 20:53  //时间
 */
public class JWTUtils {
    private static final long EXPIRE = 60000 * 60 *24 * 7; //过期时间 一周
    private static final String SECRET = "xdclass.net168";//加密秘钥
    private static final String TOKEN_PREFIX = "xdclass";//令牌前缀
    private static final String SUBJECT = "xdclass";//subject

    /***
     * @author shenhengxin
     * @description 根据用户信息生成令牌
     * @Date 20:58 2020/10/21
     * @Param [user]
     * @return java.lang.String
     */
    public static String geneJsonWebToken(User user) {
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE ))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }
    /***
     * @author shenhengxin
     * @description 校验token的方法
     * @Date 21:12 2020/10/21
     * @Param [token]
     * @return io.jsonwebtoken.Claims
     */
    public static Claims checkJWT(String token) {
       try {
           final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                   .getBody();
           return claims;
       }catch (Exception e) {
           return  null;
       }
    }
}
