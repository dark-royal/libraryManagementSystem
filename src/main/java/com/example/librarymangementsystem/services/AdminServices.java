package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.dtos.responses.AddBookResponse;
import com.example.librarymangementsystem.dtos.responses.AddStaffResponse;
import com.example.librarymangementsystem.dtos.responses.RemoveBookResponse;
import com.example.librarymangementsystem.dtos.responses.RemoveStaffResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {

    Book addBooks(AddBookRequest addBookRequest);

    RemoveBookResponse removeBook(Long id);

    AddStaffResponse addStaff(AddStaffRequest addStaffRequest);

    RemoveStaffResponse removeStaff(DeleteStaffRequest deleteStaffRequest);


   List<Staff> findAllStaffs();

   Admin registerAdmin(RegisterAdminRequest registerAdminRequest) throws MemberExistException, AdminExistException;
   void login(LoginAdminRequest loginAdminRequest) throws AdminNotFoundException;


    List<Book> findAllBooks();

    Book findBook(Long id) throws BookNotFoundException;

    Long findAdmin();



}
