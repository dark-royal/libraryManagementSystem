package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.exceptions.BookNotFoundException;

import java.util.List;

public interface BookServices {
    Book addBooks(AddBookRequest addBookRequest);

    void deleteBookById(Long id);


    Book findBook(Long id) throws BookNotFoundException;

    List<Book> findAllBook();

    void deleteAll();

}
