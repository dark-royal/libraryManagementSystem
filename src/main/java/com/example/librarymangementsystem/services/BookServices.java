package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.exceptions.BookNotFoundException;
import com.example.librarymangementsystem.exceptions.InvalidCategoryException;

import java.util.List;
import java.util.Optional;

public interface BookServices {
    Book addBooks(AddBookRequest addBookRequest) throws InvalidCategoryException;

    void deleteBookById(Long id);


    Book findBook(Long id) throws BookNotFoundException;

    List<Book> findAllBook();

    void deleteAll();

}
