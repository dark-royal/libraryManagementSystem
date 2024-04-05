package com.example.librarymangementsystem.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private boolean available;
    private Category category;
}
