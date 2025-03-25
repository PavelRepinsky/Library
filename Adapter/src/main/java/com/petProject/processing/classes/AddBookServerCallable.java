package com.petProject.processing.classes;

import com.petProject.models.AddedBook;
import com.petProject.kafka.AdapterAddBookKafkaProducer;
import com.petProject.processing.interfaces.AddingBook;
import com.petProject.processing.interfaces.ServerCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class AddBookServerCallable implements ServerCallable, AddingBook {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddBookServerCallable.class);

    @Override
    public void addBookToServer(AddedBook book) {

        RestClient client = RestClient.create();

        String resp = client
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(book)
                .retrieve()
                .body(String.class);

        LOGGER.info("HTTP request was performed!");
        assert resp != null;

        AdapterAddBookKafkaProducer.sendMessage(resp);
    }

}
