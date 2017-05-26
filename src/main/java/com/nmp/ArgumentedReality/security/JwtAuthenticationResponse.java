package com.nmp.ArgumentedReality.security;

import java.io.Serializable;

/**
 * Created by Dominik on 2017-05-26.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID=1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
