package com.copyright.mall.service;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 21:46
 */
@Slf4j
@Service
public class JwtService {

    /**
     * token生命周期[分钟]
     */
    private Integer expiration = 4000 * 60;

    private String secret = "mall";


    public String generateToken(String userId){
        Map<String,Object> claims = Maps.newHashMap();
        claims.put("user_id",userId);
        return doGenerateToken(claims,userId);
    }

    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getClaimFromToken(token).getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }


    /**
     * 生成token
     */
    private String doGenerateToken(Map<String,Object> claims, String subject) {
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + expiration * 1000L);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static void main(String[] args) {
        boolean result = new JwtService().isTokenExpired("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjpudWxsLCJleHAiOjE1NzUxNzE1ODYsImlhdCI6MTU3NTE3MTM0Nn0.I_8kF6XeZChg7Cp3W1op9oaqleesxfpOFqASYlGf3fQ");
        System.out.println();;
    }


}