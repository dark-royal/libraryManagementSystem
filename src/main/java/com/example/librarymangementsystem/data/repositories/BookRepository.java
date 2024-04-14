package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<List<Book>> findBooksByCategory(Category category);


    List<Book> findBookByTitleAndAuthorAndCategory(String title, String author, Category category);

    Optional<Book>  findBookByTitleAndAuthor(String title, String author);
}
