package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class RegisterStaffRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
