package com.petProject.kafka.interfaces;

import com.petProject.models.Author;

public interface GetBooksKafkaConsumer {

    void consume(Author author);
}
