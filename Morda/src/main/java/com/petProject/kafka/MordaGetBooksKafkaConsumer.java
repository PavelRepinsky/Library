package com.petProject.kafka;

import com.petProject.bookCreator.BookParser;
import com.petProject.kafka.interfaces.GetBooksKafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.petProject.controller.UserInterfaceController.*;

@Service
public class MordaGetBooksKafkaConsumer implements GetBooksKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MordaGetBooksKafkaConsumer.class);

    @KafkaListener(topics = "HappyTopic", groupId = "secondGroup")
    public void consume(String message) {
        LOGGER.info("Message was received -> {}", message);

        if (message.equals("[]")) {
            outOfBooks = true;
            isResponseReceived = true;
        }
        else {
            listOfBooks = BookParser.createBooksToDisplay(message);
            isResponseReceived = true;
        }
    }
}
