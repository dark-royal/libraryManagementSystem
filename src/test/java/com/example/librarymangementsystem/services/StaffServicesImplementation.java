package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.exceptions.StaffExistException;
import com.example.librarymangementsystem.exceptions.StaffNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StaffServicesImplementation {

    @Autowired
    private StaffService staffService;
    @BeforeEach
    public void setStaffService(){
        staffService.deleteAll();
    }


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
        Staff staff = staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(staff.getId()).isLoginStatus());

    }

    @Test
    public void registerStaff_loginStaff_removeStaff(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        Staff staff = staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(staff.getId()).isLoginStatus());
        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
        deleteStaffRequest.setEmail("praise@gmail.com");
        staffService.removeStaffByEmail(deleteStaffRequest);
        assertEquals(0,staffService.findAllStaffs().size());

    }

    @Test
    public void login_without_registering_throwException(){
        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        assertThrows(StaffNotFoundException.class,()->staffService.loginStaff(loginStaffRequest));

    }

    @Test
    public void registerWithTheSameDetails_throwException(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        staffService.registerStaff(registerStaffRequest);

        assertThrows(StaffExistException.class,()->staffService.registerStaff(registerStaffRequest));
        assertEquals(1,staffService.findAllStaffs().size());


    }

    @Test
    public void register_login_logout(){
        RegisterStaffRequest registerStaffRequest = new RegisterStaffRequest();
        registerStaffRequest.setEmail("praise@gmail.com");
        registerStaffRequest.setUsername("praise");
        registerStaffRequest.setPassword("paul");
        Staff staff = staffService.registerStaff(registerStaffRequest);
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail("praise@gmail.com");
        loginStaffRequest.setPassword("paul");
        staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(staff.getId()).isLoginStatus());

        staffService.logout(staff.getId());
        assertFalse(staffService.findStaffById(staff.getId()).isLoginStatus());
    }

    @Test
    public void logout_withoutLogin_orRegistering(){
       assertThrows(RuntimeException.class,()-> staffService.logout(1L));

    }

}
