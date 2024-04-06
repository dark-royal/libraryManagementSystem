package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.exceptions.AdminExistException;
import com.example.librarymangementsystem.exceptions.BookNotFoundException;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.RegisterAdminRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {

    Book addBooks(AddBookRequest addBookRequest);

    void removeBook(Long id);

    void addStaff(AddStaffRequest addStaffRequest);

    void removeStaff(DeleteStaffRequest deleteStaffRequest);


   List<Staff> findAllStaffs();

   Admin registerAdmin(RegisterAdminRequest registerAdminRequest) throws MemberExistException, AdminExistException;
   void login(LoginAdminRequest loginAdminRequest);


    List<Book> findAllBooks();

    Book findBook(Long id) throws BookNotFoundException;

    Long findAdmin();



}
