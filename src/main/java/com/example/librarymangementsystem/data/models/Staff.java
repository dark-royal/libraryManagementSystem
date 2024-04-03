package com.example.librarymangementsystem.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Admin admin;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "admin_id")
//    private Admin admin;

}
