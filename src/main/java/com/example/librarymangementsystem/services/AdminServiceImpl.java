package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.AdminRepository;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.dtos.responses.*;
import com.example.librarymangementsystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AdminServiceImpl implements AdminServices{
    @Autowired
    private  BookServices bookServices;
    @Autowired
    private StaffService staffService;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findAdminById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public AddBookResponse addBooks(AddBookRequest addBookRequest) throws InvalidCategoryException {
        bookServices.addBooks(addBookRequest);
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setMessage("book added successfully");
        return addBookResponse;

    }

    @Override
    public RemoveBookResponse removeBook(Long id) {
        Optional<Admin> admin = adminRepository.findBookById(id);
        bookServices.deleteBookById(admin.get().getId());
        RemoveBookResponse removeBookResponse = new RemoveBookResponse();
        removeBookResponse.setMessage("removed book successfully");
        return removeBookResponse;
    }

    @Override
    public AddStaffResponse addStaff(AddStaffRequest addStaffRequest) {
        staffService.addStaff(addStaffRequest);
        AddStaffResponse addStaffResponse = new AddStaffResponse();
        addStaffResponse.setMessage("Successful");

        return addStaffResponse;
    }

    @Override
    public RemoveStaffResponse removeStaff(DeleteStaffRequest deleteStaffRequest) {
        staffService.removeStaffByEmail(deleteStaffRequest);
        RemoveStaffResponse removeStaffResponse = new RemoveStaffResponse();
        removeStaffResponse.setMessage("Removed staff succesfully");
        return removeStaffResponse;
    }

    @Override
    public List<Staff> findAllStaffs() {
        return staffService.findAllStaffs();
    }

    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) throws AdminExistException {
        validate(registerAdminRequest.getEmail());
        Admin admin = new Admin();
        admin.setUsername(registerAdminRequest.getUsername());
        admin.setPassword(registerAdminRequest.getPassword());
        admin.setFirstName(registerAdminRequest.getFirstName());
        admin.setLastName(registerAdminRequest.getLastName());
        admin.setEmail(registerAdminRequest.getEmail());
        adminRepository.save(admin);
        RegisterAdminResponse registerAdminResponse = new RegisterAdminResponse();
        registerAdminResponse.setMessage("register successfully");

        return registerAdminResponse;
    }

    public void validate(String email) throws  AdminExistException {
        Optional<Admin> admin = adminRepository.findAdminByEmail(email);
        if (admin.isPresent()) throw new AdminExistException("%s exists already", email);

    }
        @Override
    public LoginAdminResponse login(LoginAdminRequest loginAdminRequest) throws AdminNotFoundException {
            Optional<Admin> admin = adminRepository.findAdminByEmail(loginAdminRequest.getEmail());
            if(admin.isPresent()){
                Admin admin1 = admin.get();
                admin1.setLoginStatus(true);
                adminRepository.save(admin1);
                LoginAdminResponse loginAdminResponse = new LoginAdminResponse();
                loginAdminResponse.setMessage("login successfully");
                return loginAdminResponse;
            }
            else {
                throw new AdminNotFoundException(STR."\{loginAdminRequest.getEmail()}not found");
            }

    }

    @Override
    public List<Book> findAllBooks() {
        return bookServices.findAllBook();
    }

    @Override
    public Book findBook(Long id) throws BookNotFoundException {
        return bookServices.findBook(id);
    }

    @Override
    public Long countAdmin() {
        return  adminRepository.count();
    }




}
