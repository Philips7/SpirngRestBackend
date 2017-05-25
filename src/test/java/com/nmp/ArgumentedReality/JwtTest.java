package com.nmp.ArgumentedReality;

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

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_AUDIENCE = "audience";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String AUDIENCE_UNKNOWN = "unknown";

    @Test
    public void generateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, "Dominik");
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
