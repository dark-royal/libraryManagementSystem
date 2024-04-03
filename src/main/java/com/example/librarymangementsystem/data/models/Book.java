package com.example.librarymangementsystem.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book extends BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String title;

    private String author;
    private Category category;
    @ManyToOne
    private Admin admin;
}
