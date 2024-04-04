package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class LoginMemberRequest {
    private String username;
    private String password;
}
