package com.petProject.kafka;

import com.petProject.kafka.interfaces.AddBookKafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.petProject.controller.UserInterfaceController.isAddingSuccessful;

@Service
public class ClientAddBookKafkaConsumer implements AddBookKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAddBookKafkaConsumer.class);

    @KafkaListener(topics = "WonderfulTopic", groupId = "secondGroup")
    public void consume(String message) {
        LOGGER.info("Message was received -> {}", message);

        if (message.equals("Book was added, success")) {
            isAddingSuccessful = true;
        }
    }
}
