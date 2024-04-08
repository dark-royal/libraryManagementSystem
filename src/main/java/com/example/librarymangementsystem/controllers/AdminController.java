package com.example.librarymangementsystem.controllers;

import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.exceptions.*;
import com.example.librarymangementsystem.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServices adminServices;
    @PostMapping("/signup")
    public String register(@RequestBody RegisterAdminRequest registerAdminRequest) throws MemberExistException {
        try{
            adminServices.registerAdmin(registerAdminRequest);
            return " registration successful";
        }catch (AdminExistException e){
            return e.getMessage();
        }

    }

    @PostMapping("/login")
    public String login(@RequestBody LoginAdminRequest loginAdminRequest){
        try{
            adminServices.login(loginAdminRequest);
            return "login successful";
        }catch (AdminNotFoundException e){
            return e.getMessage();
        }
    }
    @GetMapping("/findMemberById{id}")
    public String findAdminById(@PathVariable Long id){
        try{
            adminServices.findAdminById(id);
            return "Admin found successfully";
        }catch(Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/addBook")
    public String addBook(AddBookRequest addBookRequest){
        try{
            adminServices.addBooks(addBookRequest);
            return "book returned successful";
        }catch (InvalidCategoryException e){
            return e.getMessage();
        }



        }
        @PostMapping("/removeBook/{id}")
    public String removeBook(@PathVariable Long id){
        try{
            adminServices.removeBook(id);
            return "book removed successfully";
        }catch (BookNotFoundException e){
            return  e.getMessage();
        }



    }
    @PostMapping("/addStaff")
    public String addStaff(AddStaffRequest addStaffRequest){
        try{
            adminServices.addStaff(addStaffRequest);
            return "staff added successfully";
        }catch (Exception e){
            return e.getMessage();
        }


    }
    @PostMapping("/removeStaff")
    public String removeStaff(@RequestBody DeleteStaffRequest deleteStaffRequest){
        try{
            adminServices.removeStaff(deleteStaffRequest);
            return "staff removed successfully";
        }catch (StaffNotFoundException e){
            return e.getMessage();
        }
    }
    @GetMapping("/findAllStaff")
    public String findAllStaffs(){
        adminServices.findAllStaffs();
        return "all member found successfully";
    }
    @GetMapping("findAllBook")
    public String findAllBook(){
        adminServices.findAllBooks();
        return "All books found successfully";
    }
   @GetMapping("/findBookById")
    public String findBookById(@PathVariable Long id) throws BookNotFoundException {
        try{
            adminServices.findBook(id);
            return "book found successfully";
        }catch (BookNotFoundException e){
            return e.getMessage();
        }
    }

}
