package com.sree.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic}")
    private String topicName;
    @Value("${kafka.partitions}")
    private int partitions;
    @Value("${kafka.replication-factor}")
    private short replicationFactor;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, partitions, replicationFactor);
    }
}
