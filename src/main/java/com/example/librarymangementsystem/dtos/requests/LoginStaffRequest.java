package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class LoginStaffRequest {
    private String email;
    private String password;
}
