package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class LoginAdminRequest {
    private String email;
    private String password;
}
