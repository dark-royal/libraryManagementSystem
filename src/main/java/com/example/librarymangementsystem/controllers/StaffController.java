package com.example.librarymangementsystem.controllers;

import com.example.librarymangementsystem.dtos.requests.FindStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginStaffRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterStaffRequest;
import com.example.librarymangementsystem.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffService staffService;
    @PostMapping("/signup")
    public String register(@RequestBody RegisterStaffRequest registerStaffRequest){
        try{
            staffService.registerStaff(registerStaffRequest);

        }catch (Exception e){
            return e.getMessage();
        }

        return "registered Successfully";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginStaffRequest loginStaffRequest){
        try{
            staffService.loginStaff(loginStaffRequest);

        }catch (Exception e){
            return e.getMessage();
        }
        return "login successfully";
    }
    @GetMapping("/findStaff")
    public String findStaff(@RequestBody FindStaffRequest findStaffRequest){
        try{
            staffService.findStaff(findStaffRequest);
        }catch (Exception e){
            return e.getMessage();
        }
        return STR."\{findStaffRequest.getEmail()} found successfully";

    }
    @GetMapping("/findAllStaff")
    public String findAllStaff(){
        staffService.findAllStaffs();
        return "All staff found successfully";
    }
    @GetMapping("/findStaffById")
    public String findStaffById(@PathVariable Long id){
        try{
            staffService.findStaffById(id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "successfully";
    }



}
