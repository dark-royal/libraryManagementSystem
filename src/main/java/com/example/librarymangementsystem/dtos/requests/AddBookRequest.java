package com.example.librarymangementsystem.dtos.requests;

import com.example.librarymangementsystem.data.models.Category;
import lombok.Data;

@Data
public class AddBookRequest {
    private String title;
    private String author;
    private Category category;
}
