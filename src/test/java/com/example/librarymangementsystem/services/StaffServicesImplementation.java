package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.LoginStaffResponse;
import com.example.librarymangementsystem.exceptions.StaffExistException;
import com.example.librarymangementsystem.exceptions.StaffNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
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
        RegisterStaffRequest registerStaffRequest = registerRequest("praise","israel@gmail.com","myPassword");
        RegisterStaffResponse staffResponse = staffService.registerStaff(registerStaffRequest);
        assertThat(staffResponse.getMessage()).isNotNull();
        assertEquals(1,staffService.findAllStaffs().size());

    }

    @Test
    public void registerStaff_loginStaff(){
        RegisterStaffRequest registerStaffRequest = registerRequest("praise","israel1@gmail.com","myPassword");
        RegisterStaffResponse staffResponse = staffService.registerStaff(registerStaffRequest);
        assertThat(staffResponse.getMessage()).isNotNull();
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail(registerStaffRequest.getEmail());
        loginStaffRequest.setPassword(registerStaffRequest.getPassword());
        LoginStaffResponse loginStaffResponse = staffService.loginStaff(loginStaffRequest);
        assertThat(loginStaffResponse.getMessage()).isNotNull();
        assertTrue(staffService.findStaffById(staffResponse.getStaffId()).isLoginStatus());

    }

    @Test
    public void registerStaff_loginStaff_removeStaff(){
        RegisterStaffRequest registerStaffRequest = registerRequest("praise1","israe@gmail.com","myPassword1");
        RegisterStaffResponse staffResponse = staffService.registerStaff(registerStaffRequest);
        assertThat(staffResponse.getMessage()).isNotNull();
        assertEquals(1,staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail(registerStaffRequest.getEmail());
        loginStaffRequest.setPassword(registerStaffRequest.getPassword());
        LoginStaffResponse response = staffService.loginStaff(loginStaffRequest);
        assertTrue(staffService.findStaffById(staffResponse.getStaffId()).isLoginStatus());
        assertThat(response.getMessage()).isNotNull();

        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
        deleteStaffRequest.setEmail(registerStaffRequest.getEmail());
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
        RegisterStaffRequest registerStaffRequest = registerRequest("praise2","israel2@gmail.ocm","myPassword2");
        RegisterStaffResponse staffResponse = staffService.registerStaff(registerStaffRequest);
        assertThat(staffResponse.getMessage()).isNotNull();

        assertThrows(StaffExistException.class,()->staffService.registerStaff(registerStaffRequest));
        assertEquals(1,staffService.findAllStaffs().size());


    }

    @Test
    public void register_login_logout(){
        RegisterStaffRequest registerStaffRequest = registerRequest("praise3","israel3@gmail.com","myPassword3");
        RegisterStaffResponse staffResponse= staffService.registerStaff(registerStaffRequest);
        assertThat(staffResponse.getMessage()).isNotNull();
        assertEquals(1, staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail(registerStaffRequest.getEmail());
        loginStaffRequest.setPassword(registerStaffRequest.getPassword());
        LoginStaffResponse loginStaffResponse = staffService.loginStaff(loginStaffRequest);
        assertThat(loginStaffResponse.getMessage()).isNotNull();
        assertTrue(staffService.findStaffById(staffResponse.getStaffId()).isLoginStatus());

        staffService.logout(staffResponse.getStaffId());
        assertFalse(staffService.findStaffById(staffResponse.getStaffId()).isLoginStatus());
    }

    @Test
    public void logout_withoutLogin_orRegistering(){
       assertThrows(RuntimeException.class,()-> staffService.logout(1L));

    }

    @Test
    public void register_login_findStaff(){
        RegisterStaffRequest registerStaffRequest = registerRequest("praise4","israel@gmail.com","myPassword4");
        RegisterStaffResponse staffResponse = staffService.registerStaff(registerStaffRequest);
        assertThat(staffResponse.getMessage()).isNotNull();
        assertEquals(1, staffService.findAllStaffs().size());

        LoginStaffRequest loginStaffRequest = new LoginStaffRequest();
        loginStaffRequest.setEmail(registerStaffRequest.getEmail());
        loginStaffRequest.setPassword(registerStaffRequest.getPassword());
        LoginStaffResponse loginStaffResponse = staffService.loginStaff(loginStaffRequest);
        assertThat(loginStaffResponse.getMessage()).isNotNull();
        assertTrue(staffService.findStaffById(staffResponse.getStaffId()).isLoginStatus());

        FindStaffRequest findStaffRequest = new FindStaffRequest();
        findStaffRequest.setEmail(registerStaffRequest.getEmail());
        assertEquals("israel@gmail.com",registerStaffRequest.getEmail());
    }

    public RegisterStaffRequest registerRequest(String username, String email, String password){
        return RegisterStaffRequest.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }

}
