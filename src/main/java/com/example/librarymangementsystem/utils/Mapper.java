package com.example.librarymangementsystem.utils;

import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.RegisterStaffRequest;
import com.example.librarymangementsystem.dtos.responses.RegisterStaffResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {

    public static Staff map(RegisterStaffRequest registerStaffRequest) {
        Staff staff = new Staff();
        staff.setFirstName(registerStaffRequest.getFirstName());
        staff.setLastName(registerStaffRequest.getLastName());
        staff.setUsername(registerStaffRequest.getUsername());
        staff.setPassword(registerStaffRequest.getPassword());
        return staff;

    }

    public static RegisterStaffResponse map(Staff saveStaff){
        RegisterStaffResponse registerStaffResponse = new RegisterStaffResponse();
        registerStaffResponse.setUsername(saveStaff.getUsername());
        registerStaffResponse.setDate(String.valueOf(DateTimeFormatter.ofPattern(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()))));
        return registerStaffResponse;

    }
}
