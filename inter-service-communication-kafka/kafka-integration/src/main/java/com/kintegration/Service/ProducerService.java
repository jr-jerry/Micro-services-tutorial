package com.kintegration.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private KafkaTemplate<String,String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic,String message){
        kafkaTemplate.send(topic,message);
        System.out.println("Message send "+message+" via producer ");
    }

}
