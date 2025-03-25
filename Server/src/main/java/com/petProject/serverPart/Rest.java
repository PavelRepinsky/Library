package com.petProject.serverPart;

import com.petProject.models.AddedBook;
import com.petProject.repository.Books;
import com.petProject.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Rest {

    private final BooksRepository booksRepository;

    final Logger LOGGER = LoggerFactory.getLogger(Rest.class);

    public Rest(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("/books")
    public List<Books> getBooks(@RequestParam(name = "author") String author) {
        var res = booksRepository.findByAuthorIgnoreCase(author);
        LOGGER.info("Response was sent -> {}", author);
        return res;
    }

    @PostMapping(value = "/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addBook(@RequestBody AddedBook book) {

        Books bookToAdd = new Books(book);

        booksRepository.save(bookToAdd);

        LOGGER.info("Book was added");

        var res = booksRepository.findByBookName(book.getBookName());
        if (res.getBookName().equals(bookToAdd.getBookName())) {
            return new ResponseEntity<>("Book was added, success", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
    }
}
