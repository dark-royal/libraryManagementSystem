package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import org.springframework.stereotype.Service;

@Service
public interface AdminServices {

    void addBooks(AddBookRequest addBookRequest);

    void removeBook(Long id);

    void addStaff(AddStaffRequest addStaffRequest);

    void removeStaff(DeleteStaffRequest deleteStaffRequest);


    Long countStaffs();

    Long countBooks();
}
