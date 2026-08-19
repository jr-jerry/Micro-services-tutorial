package com.kintegration.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kintegration.Service.ProducerService;

@RestController
public class DemoController {
    private ProducerService producerService;

    public DemoController(ProducerService producerService) {
        this.producerService = producerService;
    }
    @GetMapping("/publish/{message}")
    public void publishMessage(@PathVariable String message){
        producerService.sendMessage("my-topic", message);
        System.out.println("message publish "+message);
    }
    
}
