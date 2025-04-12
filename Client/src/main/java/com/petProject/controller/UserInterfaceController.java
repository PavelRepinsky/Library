package com.petProject.controller;

import com.petProject.kafka.ClientAddBookKafkaProducer;
import com.petProject.models.AddedBook;
import com.petProject.models.Author;
import com.petProject.kafka.ClientGetBooksKafkaProducer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Controller
public class UserInterfaceController {

    public static List<AddedBook> listOfBooks = new ArrayList<>(10);
    public static boolean isResponseReceived;
    public static boolean outOfBooks;
    public static boolean isAddingSuccessful;

    @GetMapping("/library")
    public String libraryForm() {
        return "welcomePage";
    }

    @GetMapping("/library/getBooks")
    public String getBooksForm(Model model) {
        model.addAttribute("findBooksForm", new Author());
        return "findBooksForm";
    }

    @PostMapping("/library/getBooks")
    public String librarySubmit(@ModelAttribute Author author, Model model) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread thread = new Thread(() -> {
            String response = "success";
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException | RuntimeException e) {
                    e.printStackTrace();
                }
                if (isResponseReceived) {
                    queue.add(response);
                    isResponseReceived = false;
                    break;
                }
            }
        });

        thread.start();

        ClientGetBooksKafkaProducer.sendMessage(author);

        try {
            String response = queue.take();
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }

        if (listOfBooks.isEmpty()) {
            if (outOfBooks) {
                outOfBooks = false;
                return "outOfBooks";
            }
        } else if (!listOfBooks.get(0).getAuthor().equals(author.getValue())) {
            if (outOfBooks) {
                outOfBooks = false;
                return "outOfBooks";
            }
        }

        model.addAttribute("books", listOfBooks);
        return "booksByAuthor";
    }

    /*
    *************************************************************************************
     */

    @GetMapping("/library/addBook")
    public String addBookForm(Model model) {
        model.addAttribute("addingBookForm", new AddedBook());
        return "addingBookForm";
    }

    @PostMapping("/library/addBook")
    public String addBook(@ModelAttribute AddedBook addedBook) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread thread = new Thread(() -> {
            String response = "success";
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException | RuntimeException e) {
                    e.printStackTrace();
                }
                if (isAddingSuccessful) {
                    queue.add(response);
                    break;
                }
            }
        });

        thread.start();

        ClientAddBookKafkaProducer.sendMessage(addedBook);

        try {
            String response = queue.take();
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }

        if (isAddingSuccessful) {
            isAddingSuccessful = false;
            return "bookWasAdded";
        } else {
            return "somethingWentWrong";
        }
    }
}
