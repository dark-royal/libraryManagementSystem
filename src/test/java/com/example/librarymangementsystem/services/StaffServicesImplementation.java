//package com.example.librarymangementsystem.services;
//
//import com.example.librarymangementsystem.data.models.Staff;
//import com.example.librarymangementsystem.dtos.requests.RegisterStaffRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class StaffServicesImplementation {
//
//    @Autowired
//    private RegisterStaffRequest registerStaffRequest;
//    @Autowired
//    private StaffService staffService;
//
//
//    @Test
//    public void registerStaff(){
//        Staff staff = new Staff();
//        registerStaffRequest.setFirstName("praise");
//        registerStaffRequest.setLastName("oyewole");
//        registerStaffRequest.setEmail("praise@gmail.com");
//        registerStaffRequest.setPassword("paul");
//        staffService.registerStaff(registerStaffRequest);
//        assertEquals(1,staffService.findAllStaffs());
//
//    }
//
//}
