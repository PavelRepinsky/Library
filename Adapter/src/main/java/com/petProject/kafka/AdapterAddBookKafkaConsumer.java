package com.petProject.kafka;

import com.petProject.kafka.interfaces.AddBookKafkaConsumer;
import com.petProject.models.AddedBook;
import com.petProject.processing.classes.AddBookServerCallable;
import com.petProject.processing.interfaces.AddingBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AdapterAddBookKafkaConsumer implements AddBookKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdapterAddBookKafkaConsumer.class);

    AddedBook bookToProcess;

    @Override
    @KafkaListener(topics = "BeautifulTopic", groupId = "firstGroup")
    public void consume(AddedBook book) {
        LOGGER.info("Message was received -> {}", book.toString());

        this.bookToProcess = book;
        processBook();
    }

    @Override
    public void processBook() {
        AddingBook call = new AddBookServerCallable();
        call.addBookToServer(bookToProcess);
        bookToProcess = null;
    }
}
