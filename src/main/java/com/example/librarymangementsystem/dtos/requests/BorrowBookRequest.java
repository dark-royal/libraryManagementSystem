package com.example.librarymangementsystem.dtos.requests;

import com.example.librarymangementsystem.data.models.Book;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BorrowBookRequest {
    private String title;
    private String author;
    private String isbn;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private String staffName;


}
