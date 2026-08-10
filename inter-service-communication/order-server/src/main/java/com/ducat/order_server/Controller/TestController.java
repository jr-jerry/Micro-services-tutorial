package com.ducat.order_server.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ducat.order_server.Config.FeignClientInterface;

@RestController
@RequestMapping("/order-service")
public class TestController {
    // private RestTemplate restTemplate;
    // private FeignClientInterface feignClientInterface;
    private WebClient webClient;
     
   
    public TestController(RestTemplate restTemplate, FeignClientInterface feignClientInterface, WebClient webClient) {
        // this.restTemplate = restTemplate;
        // this.feignClientInterface = feignClientInterface;
        this.webClient = webClient;
    }


    @GetMapping
    public Map<String,Object> getEndpoint(){
        String message=webClient.get().uri("/inventory/").retrieve().bodyToMono(String.class).block();
        return Map.of("data",message);
    }   
}

