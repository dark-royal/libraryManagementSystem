//package com.example.librarymangementsystem.services;
//
//import com.example.librarymangementsystem.data.models.Book;
//import com.example.librarymangementsystem.data.models.Staff;
//import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
//import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
//import com.example.librarymangementsystem.dtos.requests.FindStaffRequest;
//import com.example.librarymangementsystem.dtos.requests.RegisterStaffRequest;
//import com.example.librarymangementsystem.dtos.responses.RegisterStaffResponse;
//import org.springframework.stereotype.Service;
//
//@Service
//public interface StaffService {
//
//    void addStaff(AddStaffRequest addStaffRequest);
//
//    Staff findStaff(FindStaffRequest findStaffRequest);
//
//    void removeStaffByUsername(DeleteStaffRequest deleteStaffRequest);
//
//    Long findAllStaffs();
//
//    void borrowBookToUser(Staff staff, Book book);
//    void returnBookFromUser(Staff staff, Book book);
//
//    RegisterStaffResponse registerStaff(RegisterStaffRequest registerStaffRequest);
//
//
//
//
//}
//
