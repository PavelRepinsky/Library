package com.petProject.bookCreator;

import com.petProject.models.AddedBook;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
public class BookParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookParser.class);

    public static List<AddedBook> createBooksToDisplay(String message) {

        String clearMessage = message.substring(1, message.indexOf("]"));
        List<AddedBook> createdBooks = new ArrayList<>(10);
        List<HashMap<String, String>> listOfBooks = new ArrayList<>();

        String[] books = clearMessage.split("},\\{");

        for (String book : books) {
            HashMap<String, String> bookMap = new HashMap<>();
            String[] bookFields = book.split(",");
            for (int i = 0; i < bookFields.length; i++) {
                bookFields[i] = bookFields[i].replace("{", "");
                bookFields[i] = bookFields[i].replace("}", "");
            }

            for (String splittedBook : bookFields) {
                String[] splittedFields = splittedBook.split(",");
                for (String field : splittedFields) {
                    String[] piece = field.split(":");
                    for (int i = 0; i < piece.length; i++) {
                        piece[i] = piece[i].replace("\"", "");
                    }
                    bookMap.put(piece[0], piece[1]);
                }
            }
            listOfBooks.add(bookMap);
        }

        for (HashMap<String, String> book : listOfBooks) {
            AddedBook createdBook = new AddedBook(book);
            createdBooks.add(createdBook);
        }

        LOGGER.info("Содержимое createdBooks -> {}", createdBooks);

        return createdBooks;
    }
}
