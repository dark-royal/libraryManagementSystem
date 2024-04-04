package com.example.librarymangementsystem.dtos.responses;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BorrowBookResponse {
    private String username;
    private String password;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
}
