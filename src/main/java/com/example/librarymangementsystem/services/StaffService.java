package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.FindStaffRequest;

public interface StaffService {

    void addStaff(AddStaffRequest addStaffRequest);

    Staff findStaff(FindStaffRequest findStaffRequest);

    void removeStaffByUsername(DeleteStaffRequest deleteStaffRequest);

    Long findAllStaffs();

    void borrowBookToUser(Staff staff, Book book);
    void returnBookFromUser(Staff staff, Book book);

}

