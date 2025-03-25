package com.petProject.kafka.interfaces;

import com.petProject.models.AddedBook;

public interface AddBookKafkaConsumer {

    void consume(AddedBook book);

    void processBook();
}
