package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.dtos.AddBookRequest;
import com.example.librarymangementsystem.dtos.AddStaffRequest;
import org.springframework.stereotype.Service;

@Service
public interface AdminServices {

    void addBooks(AddBookRequest addBookRequest);
    void removeBook(Long id);

    void addStaff(AddStaffRequest addStaffRequest);

    void removeStaff(Long id);



}
