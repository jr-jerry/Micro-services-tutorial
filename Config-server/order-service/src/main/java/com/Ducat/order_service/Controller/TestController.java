package com.Ducat.order_service.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service")
public class TestController {
    @Value("${application.message}")
    private String message;

    @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }
}
