package com.nmp.ArgumentedReality;

import com.nmp.ArgumentedReality.security.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dominik on 2017-05-25.
 */


public class JwtTest {

    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_AUDIENCE = "audience";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String AUDIENCE_UNKNOWN = "unknown";

    @Test
    public void generateToken() {
        String username = jwtTokenUtil.getUsernameFromToken("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE0OTU3NjY5MjksImV4cCI6MTUyNzMwMjkyNiwiYXVkIjoiIiwic3ViIjoiUGhpbGlwczcifQ.BfQCZcG3yu0ztIx6X-ls5PNG8Dab1ph6c3r_ANg054g");

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, "Philips7");
        claims.put(CLAIM_KEY_AUDIENCE, AUDIENCE_UNKNOWN);
        claims.put(CLAIM_KEY_CREATED, new Date());
        String token =  generateToken(claims);
        System.out.println(token);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS512, "Secret")
                .compact();
    }
}
