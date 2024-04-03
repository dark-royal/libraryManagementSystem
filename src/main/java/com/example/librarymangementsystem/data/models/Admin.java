package com.example.librarymangementsystem.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity

@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

}
