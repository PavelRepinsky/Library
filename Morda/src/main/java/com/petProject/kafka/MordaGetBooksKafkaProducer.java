package com.petProject.kafka;

import com.petProject.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MordaGetBooksKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MordaGetBooksKafkaProducer.class);

    private static KafkaTemplate<String, Author> kafkaTemplate;

    public MordaGetBooksKafkaProducer(KafkaTemplate<String, Author> kafkaTemplate) {
        MordaGetBooksKafkaProducer.kafkaTemplate = kafkaTemplate;
    }
    public static void sendMessage(Author author) {

        LOGGER.info("Message was sent to LuckyTopic successfully -> {}", author);

        Message<Author> message = MessageBuilder
                .withPayload(author)
                .setHeader(KafkaHeaders.TOPIC, "LuckyTopic")
                .build();

        kafkaTemplate.send(message);
    }
}
