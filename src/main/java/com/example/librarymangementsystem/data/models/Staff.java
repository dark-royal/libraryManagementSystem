package com.example.librarymangementsystem.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private boolean loginStatus;
    private String password;
    private String email;
    @ManyToOne
    private Admin admin;



}
