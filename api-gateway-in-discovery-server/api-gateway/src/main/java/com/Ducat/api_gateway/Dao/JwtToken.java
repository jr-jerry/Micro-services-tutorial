package com.Ducat.api_gateway.Dao;

import java.util.Collections;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtToken extends AbstractAuthenticationToken {
    private final String token;

    public JwtToken(String token){
        super(Collections.emptyList());
        this.token=token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
    
}
