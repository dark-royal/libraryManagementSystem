package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class ReturnBookRequest {
    private String email;
    private Long bookId;
}
