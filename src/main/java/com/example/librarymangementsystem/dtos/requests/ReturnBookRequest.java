package com.example.librarymangementsystem.dtos.requests;

import com.example.librarymangementsystem.data.models.Category;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReturnBookRequest {
    private String author;
    private String title;
    private LocalDate returnedDate;
    private Category category;
    private String email;
    private Long bookId;


}
