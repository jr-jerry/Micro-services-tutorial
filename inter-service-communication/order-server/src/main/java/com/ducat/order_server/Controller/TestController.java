package com.ducat.order_server.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ducat.order_server.Config.FeignClientInterface;

@RestController
@RequestMapping("/order-service")
public class TestController {
    // private RestTemplate restTemplate;
    private FeignClientInterface feignClientInterface;
    // private WebClient webClient;
     
   
    public TestController(RestTemplate restTemplate, FeignClientInterface feignClientInterface) {
        // this.restTemplate = restTemplate;
        this.feignClientInterface = feignClientInterface;
        // this.webClient = webClient;
    }


    @GetMapping
    public Map<String,Object> getEndpoint(){

        //Using FeignClient 
        String message=feignClientInterface.getRequest();
        return Map.of("data",message);
        // String message=webClient.get().uri("/inventory").retrieve().bodyToMono(String.class).block();//http://localhost:8081/inventory
        // return Map.of("data",message);
    }   
}

