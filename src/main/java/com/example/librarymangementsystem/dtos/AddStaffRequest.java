package com.example.librarymangementsystem.dtos;

import com.example.librarymangementsystem.data.models.Admin;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AddStaffRequest {
private String username;
private String password;
private String email;

}
