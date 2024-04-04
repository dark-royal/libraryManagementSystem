package com.example.librarymangementsystem.dtos.responses;

import lombok.Data;

@Data
public class RegisterMemberResponse {
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String date;
}
