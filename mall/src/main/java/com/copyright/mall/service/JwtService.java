package com.copyright.mall.service;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public String doGenerateToken(Map<String, Object> claims, String subject) {
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
        boolean result = new JwtService().isTokenExpired("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZXMiOlsxXSwiZXhwIjoxNTg5MTU3NjAyLCJ1c2VySWQiOjIsImlhdCI6MTU4ODkxNzYwMn0.d0FUa7rSGc5E1cWoGBhmHfyETOpcUfq8ZaytUXuf1iw");
        Map<String,Object> objectMap = new JwtService().getClaimFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZXMiOlsxXSwiZXhwIjoxNTg5MTU3NjAyLCJ1c2VySWQiOjIsImlhdCI6MTU4ODkxNzYwMn0.d0FUa7rSGc5E1cWoGBhmHfyETOpcUfq8ZaytUXuf1iw");
        System.out.println((List<Long>)objectMap.get("roles"));
    }


}