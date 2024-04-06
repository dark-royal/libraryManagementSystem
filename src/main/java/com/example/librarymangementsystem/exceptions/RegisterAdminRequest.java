package com.example.librarymangementsystem.exceptions;

import lombok.Data;

@Data
public class RegisterAdminRequest {
    private String username;
    private  String email;
    private String  firstName;
    private String password;
    private String lastName;
}
