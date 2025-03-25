package com.petProject.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdapterGetBooksKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdapterGetBooksKafkaProducer.class);

    private static KafkaTemplate<String, String> kafkaTemplate;

    public AdapterGetBooksKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        AdapterGetBooksKafkaProducer.kafkaTemplate = kafkaTemplate;
    }

    public static void sendMessage(String message) {
        LOGGER.info(String.format("Message was sent to HappyTopic successfully -> %s", message));
        kafkaTemplate.send("HappyTopic", message);
    }
}
