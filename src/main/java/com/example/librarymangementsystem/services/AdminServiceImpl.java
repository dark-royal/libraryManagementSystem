package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminServiceImpl implements AdminServices{
    @Autowired
    private  BookServices bookServices;
    @Autowired
    private AdminR
    @Autowired
    private StaffService staffService;

    @Override
    public void addBooks(AddBookRequest addBookRequest) {
        bookServices.addBooks(addBookRequest);
    }

    @Override
    public void removeBook(Long id) {
        bookServices.deleteBookById(id);

    }

    @Override
    public void addStaff(AddStaffRequest addStaffRequest) {
        staffService.addStaff(addStaffRequest);

    }

    @Override
    public void removeStaff(DeleteStaffRequest deleteStaffRequest) {
        Admin admin = adminRepository
        staffService.removeStaffByUsername(deleteStaffRequest);

    }

    @Override
    public List<Staff> findAllStaffs() {
        return staffService.findAllStaffs();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookServices.findAllBook();
    }


}
