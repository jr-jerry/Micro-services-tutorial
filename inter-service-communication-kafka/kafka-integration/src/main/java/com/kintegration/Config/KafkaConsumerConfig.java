package com.kintegration.Config;

import java.util.Map;
import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String,Object> getConsumerFactory(){
        Map<String,Object> config=new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"my-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> getListener(){
        ConcurrentKafkaListenerContainerFactory<String,String> listener=new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(getConsumerFactory());
        return listener;
    }
    
}
