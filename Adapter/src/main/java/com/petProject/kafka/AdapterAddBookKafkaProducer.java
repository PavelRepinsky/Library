package com.petProject.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdapterAddBookKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdapterAddBookKafkaProducer.class);

    private static KafkaTemplate<String, String> kafkaTemplate;

    public AdapterAddBookKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        AdapterAddBookKafkaProducer.kafkaTemplate = kafkaTemplate;
    }

    public static void sendMessage(String message) {
        LOGGER.info("Message was sent to WonderfulTopic successfully -> {}", message);
        kafkaTemplate.send("WonderfulTopic", message);
    }
}
