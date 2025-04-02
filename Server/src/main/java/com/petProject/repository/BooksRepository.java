package com.petProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<Books, Integer> {

    List<Books> findByAuthorIgnoreCase(String author);

    Books findByBookName(String book_name);
}