package com.petProject.repository;

import com.petProject.models.AddedBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "release_year")
    private Integer releaseYear;


    public Books(AddedBook book) {
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.releaseYear = Integer.parseInt(book.getReleaseYear());
    }
}
