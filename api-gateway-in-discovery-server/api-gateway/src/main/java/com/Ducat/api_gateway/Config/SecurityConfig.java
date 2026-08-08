package com.Ducat.api_gateway.Config;

import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    public SecurityConfig() {
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity,
            AuthenticationWebFilter jwtAuthenticationWebFilter) {
        return httpSecurity
                .csrf(ServerHttpSecurity -> ServerHttpSecurity.disable())
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("*/auth/**").permitAll()
                        .anyExchange().authenticated())
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .addFilterAt(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }

    @Bean
    public AuthenticationWebFilter jwtAuthenticationWebFilter(
            CustomReactiveAuthenticationManger customReactiveAuthenticationManger,
            JwtServerAuthenticationConverter converter) {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(customReactiveAuthenticationManger);
        filter.setServerAuthenticationConverter(converter);
        return filter;

    }
}
