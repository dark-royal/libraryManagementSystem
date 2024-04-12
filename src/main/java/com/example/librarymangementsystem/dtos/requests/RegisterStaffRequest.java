package com.example.librarymangementsystem.dtos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterStaffRequest {
    private String username;
    private String password;
    private String email;
}
