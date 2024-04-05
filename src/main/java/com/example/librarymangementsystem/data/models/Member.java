package com.example.librarymangementsystem.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    @JsonIgnore
    private LocalDate borrowedDate;
    @JsonIgnore
    private LocalDate dueDate;
    private boolean logStatus = false;
    @OneToMany
    private List<Book> borrowedBooks;

}
