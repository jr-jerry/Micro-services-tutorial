package com.Ducat.api_gateway.Config;

import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
 import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private final CustomReactiveAuthenticationManger customReactiveAuthenticationManger;
    private final CustomServerSecurityContextRepository customServerSecurityContextRepository;
     

    public SecurityConfig(CustomReactiveAuthenticationManger customReactiveAuthenticationManger,
            CustomServerSecurityContextRepository customServerSecurityContextRepository) {
        this.customReactiveAuthenticationManger = customReactiveAuthenticationManger;
        this.customServerSecurityContextRepository = customServerSecurityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(ServerHttpSecurity -> ServerHttpSecurity.disable())
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(customReactiveAuthenticationManger)
                .securityContextRepository(customServerSecurityContextRepository)
                
                .authorizeExchange(
                    exchange->exchange
                    .pathMatchers("/auth/login","/auth/register").permitAll()
                    .pathMatchers("/orders/**").hasAuthority("ROLE_USER")
                    .anyExchange().authenticated()
                )
                .build();
                
    }
}
