package com.example.librarymangementsystem.dtos.responses;

import com.example.librarymangementsystem.data.models.Book;
import lombok.Data;

@Data
public class AddBookResponse extends Book {
    private String message;
}
