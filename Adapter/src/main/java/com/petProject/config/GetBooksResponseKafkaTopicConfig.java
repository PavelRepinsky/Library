package com.petProject.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

@Service
public class GetBooksResponseKafkaTopicConfig {

    @Bean
    public NewTopic happyTopic() {
        return TopicBuilder.name("HappyTopic")
                .build();
    }

}
