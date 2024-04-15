package com.example.librarymangementsystem.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private boolean logStatus;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Book> borrowedBooks;



}
