package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class RegisterStaffResponse {
    private String message;
    private Long id;

    public Long getId() {
        return ++id;
    }
}
