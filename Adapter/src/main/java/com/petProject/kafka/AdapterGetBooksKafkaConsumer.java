package com.petProject.kafka;

import com.petProject.kafka.interfaces.GetBooksKafkaConsumer;
import com.petProject.models.Author;
import com.petProject.processing.classes.Processing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AdapterGetBooksKafkaConsumer implements GetBooksKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdapterGetBooksKafkaConsumer.class);

    Processing processing = new Processing();

    @KafkaListener(topics = "LuckyTopic", groupId = "firstGroup")
    public void consume(Author author) {

        LOGGER.info("Message was received -> {}", author.toString());

        processing.processAuthor(author);
    }
}
