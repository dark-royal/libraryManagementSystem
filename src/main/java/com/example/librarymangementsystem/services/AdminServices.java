package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.dtos.responses.*;
import com.example.librarymangementsystem.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {

    Admin findAdminById(Long id);

    AddBookResponse addBooks(AddBookRequest addBookRequest) throws InvalidCategoryException;

    RemoveBookResponse removeBook(Long id);

    AddStaffResponse addStaff(AddStaffRequest addStaffRequest);

    RemoveStaffResponse removeStaff(DeleteStaffRequest deleteStaffRequest);


   List<Staff> findAllStaffs();

   RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) throws MemberExistException, AdminExistException;
   LoginAdminResponse login(LoginAdminRequest loginAdminRequest) throws AdminNotFoundException;


    List<Book> findAllBooks();

    Book findBook(Long id) throws BookNotFoundException;



    Long countAdmin();
}
