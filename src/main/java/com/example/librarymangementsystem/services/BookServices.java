package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.dtos.requests.AddBookRequest;

public interface BookServices {
    void addBooks(AddBookRequest addBookRequest);

    void deleteBookById(Long id);

    void removeBook(Long id);

    Long countAllBooks();
}
