package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;

import java.util.List;

public interface BookServices {
    void addBooks(AddBookRequest addBookRequest);

    void deleteBookById(Long id);

    Book findBook(String title,String author);

    List<Book> findAllBook();
}
