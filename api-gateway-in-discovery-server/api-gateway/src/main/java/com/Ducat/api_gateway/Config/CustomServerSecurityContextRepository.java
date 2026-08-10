package com.Ducat.api_gateway.Config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;

import com.Ducat.api_gateway.Dao.JwtToken;

import reactor.core.publisher.Mono;

public class CustomServerSecurityContextRepository implements ServerSecurityContextRepository {
    private final CustomReactiveAuthenticationManger reactiveAuthenticationManager;
    
    public CustomServerSecurityContextRepository(CustomReactiveAuthenticationManger reactiveAuthenticationManager) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                    .filter(authHeader->authHeader.startsWith("Bearer "))
                    .map(authHeader->authHeader.substring(7))
                    .flatMap(token->this.reactiveAuthenticationManager.authenticate(new JwtToken(token))
                    .map(SecurityContextImpl->new SecurityContextImpl()));
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        //No need to save context for stateless authentication
      return Mono.empty();
    }
    
}
