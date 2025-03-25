package com.petProject.processing.classes;

import com.petProject.models.Author;
import com.petProject.processing.interfaces.GettingBooks;

import java.io.IOException;
import java.net.URISyntaxException;

public class Processing {

    GettingBooks getBooksFromServer = new GetBooksServerCall();

    public void processAuthor(Author author) {
        try {
            if (author.getValue() != null) {
                getBooksFromServer.getBooksFromServer(author);
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
