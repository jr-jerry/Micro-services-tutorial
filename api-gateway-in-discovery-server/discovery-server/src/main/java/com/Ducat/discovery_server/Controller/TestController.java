package com.Ducat.discovery_server.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get")
    public Map<String,Object> getEndpoint(){
        return Map.of("message","demo-service working ");
    }
}
