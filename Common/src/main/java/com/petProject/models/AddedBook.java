package com.petProject.models;

import lombok.*;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddedBook {

    String bookName;
    String author;
    String releaseYear;


    public AddedBook(HashMap<String, String> map) {
        this.bookName = map.get("bookName");
        this.author = map.get("author");
        this.releaseYear = map.get("releaseYear");
    }
}
