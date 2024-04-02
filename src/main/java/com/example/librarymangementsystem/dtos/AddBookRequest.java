package com.example.librarymangementsystem.dtos;

import com.example.librarymangementsystem.data.models.Category;
import lombok.Data;

@Data
public class AddBookRequest {
    private Long id;
    private String title;
    private String author;
    private Category category;
}
