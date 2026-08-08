package com.Ducat.api_gateway.Config;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.Ducat.api_gateway.Services.JwtService;

import reactor.core.publisher.Mono;

public class CustomReactiveAuthenticationManger implements ReactiveAuthenticationManager{
    private JwtService jwtService;

    public CustomReactiveAuthenticationManger(JwtService jwtService){
        this.jwtService=jwtService;
    }
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
       String token=authentication.getCredentials().toString();
       if(!jwtService.isTokenValid(token)){
        return Mono.error(new BadCredentialsException("Invalid JWT token"));
       }
       String username=jwtService.extractEmail(token);
       return Mono.just(new UsernamePasswordAuthenticationToken(username, null,List.of()));
    }
    
}
