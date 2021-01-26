package com.hacker.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hacker.exception.ParameterException;
import com.hacker.exception.UnAuthenticatedException;
import com.hacker.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Slf4j
@Component
public class JwtAuthUtil {
    public static final String HEADER = "Authorization";
    // 密钥
    private static final String SECRET = "never to guess the secret!";
    // access_token 过期时间
    private static final Integer ACCESS_TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000 * 10;
    // refresh_token 过期时间
    private static final Integer REFRESH_TOKEN_EXPIRE_TIME = 20 * 24 * 60 * 60 * 1000;

    /**
     * 获取token
     *
     * @param customerId 用户id
     * @param expireTime 过期时间
     * @return token
     */
    public static String getToken(Integer customerId, Date expireTime) {
        // 使用单向散列函数加密
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 设置header信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "hs256");
        return JWT.create().withAudience(String.valueOf(customerId)).withHeader(header).
                withClaim("userId", customerId).
                withIssuedAt(new Date()).
                withExpiresAt(expireTime).sign(algorithm);
    }

    /**
     * 刷新token
     *
     * @param refreshToken
     * @return
     */
    public static HashMap<String, String> refreshToken(String refreshToken) {
        Integer userId = getCustomerId(refreshToken);
        String accessToken = getToken(userId, new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME));
        return new HashMap<String, String>() {{
            put("access_token", accessToken);
            put("refresh_token", refreshToken);
        }};
    }

    /**
     * 生成access_token和refresh_token
     *
     * @param user
     * @return
     */
    public static UserVO sign(UserVO user) {
        // 获取access_token
        Date expireTime = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = getToken(user.getId(), expireTime);
        user.setAccessToken(accessToken);
        user.setAccessTokenExpiration(expireTime.getTime());
        // 获取refresh_token
        expireTime = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME);
        String refreshToken = getToken(user.getId(), expireTime);
        user.setRefreshToken(refreshToken);
        user.setRefreshTokenExpiration((expireTime.getTime()));
        return user;
    }

    public static String generateToken(Integer userId) {
        Date expireTime = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);
        return getToken(userId, expireTime);
    }

    /**
     * token验证
     *
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 获取token中的用户id
     *
     * @param token
     * @return
     */
    public static Integer getCustomerId(String token) {
        try {
            log.info("token:{}", token);

            if (token.startsWith("duodian")) {
                token = token.replace("duodian", "");
            }
            DecodedJWT jwt = JWT.decode(token);
            log.info("customer_id:{}", jwt.getClaim("userId").asInt());
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            log.error("token解码失败！ 详细信息：" + e.getMessage());
            e.printStackTrace();
            throw new UnAuthenticatedException(10004);
        }
    }

    /**
     * 获取token中的用户id 【该方法与getCustomerId 本质没什么区别，最大区别就一个token解析错误则会返回null,另一个则会报异常】
     *
     * @param token
     * @return
     */
    public static Integer parseCustomerId(String token) {
        DecodedJWT jwt;
        log.info(token);
        if (token == null || token.equals(""))
            return null;
        if (!token.startsWith("duodian"))
            throw new ParameterException(10004);
        token = token.replace("duodian", "");
        try {
            jwt = JWT.decode(token);
        } catch (JWTDecodeException e) {
            log.error("token解码失败！ 详细信息：" + e.getMessage());
            e.printStackTrace();
            throw new UnAuthenticatedException(10004);
        }
        return jwt.getClaim("userId").asInt();
    }


    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }
}
