package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.AdminRepository;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
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
    public Book addBooks(AddBookRequest addBookRequest) {
        bookServices.addBooks(addBookRequest);
        return null;
    }

    @Override
    public void removeBook(Long id) {
        Optional<Admin> admin = adminRepository.findBookById(id);
        bookServices.deleteBookById(admin.get().getId());

    }

    @Override
    public void addStaff(AddStaffRequest addStaffRequest) {
        staffService.addStaff(addStaffRequest);

    }

    @Override
    public void removeStaff(DeleteStaffRequest deleteStaffRequest) {
        staffService.removeStaffByUsername(deleteStaffRequest);

    }

    @Override
    public List<Staff> findAllStaffs() {
        return staffService.findAllStaffs();
    }

    @Override
    public Admin registerAdmin(RegisterAdminRequest registerAdminRequest) throws AdminExistException {
        validate(registerAdminRequest.getEmail());
        Admin admin = new Admin();
        admin.setUsername(registerAdminRequest.getUsername());
        admin.setPassword(registerAdminRequest.getPassword());
        admin.setFirstName(registerAdminRequest.getFirstName());
        admin.setLastName(registerAdminRequest.getLastName());
        admin.setEmail(registerAdminRequest.getEmail());
        adminRepository.save(admin);

        return admin;
    }

    public void validate(String email) throws  AdminExistException {
        Optional<Admin> admin = adminRepository.findAdminByEmail(email);
        if (admin.isPresent()) throw new AdminExistException("%s exists already", email);

    }
        @Override
    public void login(LoginAdminRequest loginAdminRequest) throws AdminNotFoundException {
            Optional<Admin> admin = adminRepository.findAdminByEmail(loginAdminRequest.getEmail());
            if(admin.isPresent()){
                Admin admin1 = admin.get();
                admin1.setLoginStatus(true);
                adminRepository.save(admin1);
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
    public Long findAdmin() {
        return  adminRepository.count();
    }




}
