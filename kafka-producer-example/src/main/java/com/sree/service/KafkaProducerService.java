package com.sree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> template;
    @Value("${kafka.topic}")
    private String kafkaTopic;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send(kafkaTopic, message);
        future.whenComplete((result, ex) -> {

            if (ex == null) {
                System.out.println("Sent message=["+message+"] with offset=["+result.getRecordMetadata().offset()+"]");
            } else {
                System.out.println("Unable to send message=["+message+"] due to : "+ex.getMessage());
            }
        });

    }
}
