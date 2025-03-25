package com.petProject.kafka;

import com.petProject.models.AddedBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MordaAddBookKafkaProducer {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MordaAddBookKafkaProducer.class);

    private static KafkaTemplate<AddedBook, AddedBook> addBookKafkaTemplate;

    public MordaAddBookKafkaProducer(KafkaTemplate<AddedBook, AddedBook> addBookKafkaTemplate) {
        MordaAddBookKafkaProducer.addBookKafkaTemplate = addBookKafkaTemplate;
    }

    public static void sendMessage(AddedBook book) {

        Message<AddedBook> message = MessageBuilder
                .withPayload(book)
                .setHeader(KafkaHeaders.TOPIC, "BeautifulTopic")
                .build();

        addBookKafkaTemplate.send(message);

        LOGGER.info("Message was sent to BeautifulTopic successfully -> {}", book);
    }
}
