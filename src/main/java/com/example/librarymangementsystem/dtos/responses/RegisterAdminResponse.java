package com.example.librarymangementsystem.dtos.responses;

import com.example.librarymangementsystem.data.models.Admin;
import lombok.Data;

@Data
public class RegisterAdminResponse extends Admin {
    private String message;
}
