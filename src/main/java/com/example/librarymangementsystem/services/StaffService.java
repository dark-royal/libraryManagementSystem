package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {


void loginStaff(LoginStaffRequest loginStaffRequest);
    Staff findStaff(FindStaffRequest findStaffRequest);
    Staff findStaffById(Long id);

    void logout(Long id);

    List<Staff> findAllStaffs();

    void borrowBookToUser(Staff staff, Book book);
    void returnBookFromUser(Staff staff, Book book);

    void registerStaff(RegisterStaffRequest registerStaffRequest);

    void removeStaffByEmail(DeleteStaffRequest deleteStaffRequest);


    void addStaff(AddStaffRequest addStaffRequest);

    void removeStaffByUsername(DeleteStaffRequest deleteStaffRequest);

}


