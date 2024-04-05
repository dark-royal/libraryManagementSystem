package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {

    void addBooks(AddBookRequest addBookRequest);

    void removeBook(Long id);

    void addStaff(AddStaffRequest addStaffRequest);

    void removeStaff(DeleteStaffRequest deleteStaffRequest);


   List<Staff> findAllStaffs();


    List<Book> findAllBooks();
}
