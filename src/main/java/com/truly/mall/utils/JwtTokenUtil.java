package com.truly.mall.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author truly
 * @date 2022/10/23 11:38
 * JwtToken生成工具类
 */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负载生成JWT的token
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 从token中获取JWT的负载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token){
        Claims claims=null;
        try{
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.debug("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 生成token过期时间
     * @return
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims=getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username=null;
            LOGGER.debug("获取用户名失败：token  {}",token);
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails 从数据库中查出的用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String usernmae = getUserNameFromToken(token);
        return usernmae.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    /**
     * 获取token的过期时间
     * @param token
     * @return
     */
    private Date getExpiradDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否已经时效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiradDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 根据用户消息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
}
