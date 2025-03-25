package com.petProject.processing.classes;

import com.petProject.models.Author;
import com.petProject.kafka.AdapterGetBooksKafkaProducer;
import com.petProject.processing.interfaces.GettingBooks;
import com.petProject.processing.interfaces.ServerCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

public class GetBooksServerCall implements ServerCallable, GettingBooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetBooksServerCall.class);

    @Override
    public void getBooksFromServer(Author author) {

        RestClient client = RestClient.builder()
                .baseUrl(url)
                .build();

        String resp = client
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("author", author.getValue())
                        .build())
                .retrieve()
                .body(String.class);

        LOGGER.info("HTTP request was performed!");
        assert resp != null;

        AdapterGetBooksKafkaProducer.sendMessage(resp);
    }

}
