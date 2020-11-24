package com.czxy.util;

import com.czxy.domain.Boss;
import com.czxy.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * @ClassName: JwtHelper
 * @Description: token工具类
 * @author: yuanxinqi
 * @date: 2018/8/17 9:59
 * @version: V 2.0.0
 * @since: (jdk_1.8)
 */
public class JWTUtil {


    /**
     * 获取token中的参数
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token, String key) {
        if ("".equals(token)) {
            return null;
        }

        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 生成token
     *
     * @param user,boss
     * @return
     */
    public static String createToken(User user, Boss boss, String key, int expireMinutes) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
//                .setHeaderParam("type", "JWT")
//                .setSubject(userId.toString())
                .claim("id", user != null ? user.getUid() : boss.getBid()) // 设置载荷信息
                .claim("username", user != null ? user.getUsername() : "")
                .claim("bossname", boss != null ? boss.getBossname() : "")
                .claim("password", user != null ? user.getPassword() : boss.getPassword())
                .claim("name", user != null ? user.getName() : boss.getName())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())// 设置超时时间
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }

}
