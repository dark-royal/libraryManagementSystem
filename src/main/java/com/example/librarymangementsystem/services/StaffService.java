package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.LoginStaffResponse;
import com.example.librarymangementsystem.dtos.responses.RemoveStaffResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {


LoginStaffResponse loginStaff(LoginStaffRequest loginStaffRequest);
    void findStaff(FindStaffRequest findStaffRequest);
    Staff findStaffById(Long id);

    void logout(Long id);

    List<Staff> findAllStaffs();

    void borrowBookToUser(Staff staff, Book book);
    void returnBookFromUser(Staff staff, Book book);

    Staff registerStaff(RegisterStaffRequest registerStaffRequest);

    RemoveStaffResponse removeStaffByEmail(DeleteStaffRequest deleteStaffRequest);


    Staff addStaff(AddStaffRequest addStaffRequest);


    void deleteAll();

}


