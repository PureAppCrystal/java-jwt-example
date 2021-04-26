package com.hoonyb.jwt.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
    final static String key = "bt-jwt";

    // 토큰 생성
    public static String createToken() {

        //Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", ",JWT");
        headers.put("alg", "HS256");

        //payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", "my First JWT !!");

        // expire
        Long expiredTime = 1000 * 60L * 60L * 2L;

        Date ext = new Date();
        ext.setTime(ext.getTime() + expiredTime);


        // builder
        String jwt = Jwts.builder()
                    .setHeader(headers)
                    .setClaims(payloads)
                    .setSubject("user")
                    .setExpiration(ext)
                    .signWith(SignatureAlgorithm.HS256, key.getBytes())
                    .compact();

        return jwt;
    }

    // 토큰 검증
    public static Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                        .setSigningKey(key.getBytes("UTF-8"))
                        .parseClaimsJws(jwt)
                        .getBody();

            claimMap = claims;

        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException : " + e);

        } catch (Exception e ) {
            System.out.println("exception : " + e);
        }

        return claimMap;
    }
}
