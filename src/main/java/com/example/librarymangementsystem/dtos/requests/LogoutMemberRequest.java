package com.example.librarymangementsystem.dtos.requests;

import lombok.Data;

@Data
public class LogoutMemberRequest {
    private Long id;

    private String email;
}
