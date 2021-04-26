package com.hoonyb.jwt.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hoonyb.jwt.utils.JWTUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class MainController {
    
    final String key = "btjwt";



    @RequestMapping("/test")
    public String test() {
        return "helloWorld";
    }


    @RequestMapping("/test2")
    public String test2() throws UnsupportedEncodingException {


        String jwtToken = JWTUtil.createToken();
        System.out.println("jwtToken : " + jwtToken);

        Map<String, Object> result = JWTUtil.verifyJWT(jwtToken);
        System.out.println("result : " + result.toString());

        return "helloWorld";
    }

    
}
