package com.ducat.order_server.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate getRestTemplateBean(){
        return new RestTemplate();
    }
    @Bean
    public WebClient getWebClientBean(){
        return WebClient.builder()
                        .baseUrl("http://localhost:8081")
                        .build();
    }
}
