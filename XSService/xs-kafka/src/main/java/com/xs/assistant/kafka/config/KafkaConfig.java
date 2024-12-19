package com.xs.assistant.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Value("${my.topics}")
    String topic;

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(topic).build();
    }

}
