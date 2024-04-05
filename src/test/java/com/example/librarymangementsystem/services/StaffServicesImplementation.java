package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.exceptions.StaffExistException;
import com.example.librarymangementsystem.exceptions.StaffNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StaffServicesImplementation {

    @Autowired
    private StaffService staffService;


    @Test
    public void registerStaff(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

    }

    @Test
    public void registerStaff_loginStaff(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(1L).isLoginStatus());

    }

    @Test
    public void registerStaff_loginStaff_removeStaff(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(1L).isLoginStatus());
        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();

        staffService.removeStaffByEmail(deleteStaffRequest);
        assertEquals(0,staffService.findAllStaffs().size());

    }

    @Test
    public void login_without_registering_throwException(){
        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        assertThrows(StaffNotFoundException.class,()->staffService.loginStaff(loginStaffRequest));
        //assertFalse(staffService.findStaffById(1L).isLoginStatus());
    }

    @Test
    public void registerWithTheSameDetails_throwException(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        staffService.registerStaff(registerStaffRequest);

        RegisterStaffRequest registerStaffRequest1 = new RegisterStaffRequest();
        registerStaffRequest1.setEmail("praise@gmail.com");
        registerStaffRequest1.setUsername("praise");
        registerStaffRequest1.setPassword("paul");
        assertThrows(StaffExistException.class,()->staffService.registerStaff(registerStaffRequest1));
        assertEquals(1,staffService.findAllStaffs().size());


    }

    @Test
    public void register_login_logout(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(1L).isLoginStatus());

        staffService.logout(1L);
        assertFalse(staffService.findStaffById(1L).isLoginStatus());
    }

}
