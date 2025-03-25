package com.petProject.processing.interfaces;

import com.petProject.models.Author;

import java.io.IOException;
import java.net.URISyntaxException;

public interface GettingBooks {

    void getBooksFromServer(Author author) throws URISyntaxException, IOException, InterruptedException;
}
