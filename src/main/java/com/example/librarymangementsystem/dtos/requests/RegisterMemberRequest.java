package com.example.librarymangementsystem.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
//@Component
public class RegisterMemberRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
