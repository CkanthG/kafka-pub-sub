package com.sree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(String message) {
        log.info("Consumer receives message : {}", message);
    }

}
