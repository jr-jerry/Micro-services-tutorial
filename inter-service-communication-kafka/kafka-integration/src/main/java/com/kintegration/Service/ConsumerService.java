package com.kintegration.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "my-topic",groupId = "my-group")
    public void consumeMessage(String message){
        System.out.println("message listen here inside listener"+message);
    }
}
