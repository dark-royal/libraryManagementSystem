package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginStaffResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @BeforeEach
    public void setMemberService(){
        memberService.deleteAll();
    }

    @Test
    public void testRegisterMember() throws MemberExistException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setEmail("praise@gmail.com");
        registerMemberRequest.setUsername("praise");
        registerMemberRequest.setPassword("paul");
        RegisterMemberResponse response = memberService.registerMember(registerMemberRequest);
        RegisterMemberResponse registerMemberResponse = new RegisterMemberResponse();
        registerMemberResponse.setMessage("Registration Successful");
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1,memberService.findAllMember().size());
    }

    @Test
    public void testThatMemberCannotRegisterWithTheSameUsername() throws MemberExistException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setEmail("praise@gmail.com");
        registerMemberRequest.setUsername("praise");
        registerMemberRequest.setPassword("paul");
        RegisterMemberResponse response = memberService.registerMember(registerMemberRequest);
        RegisterMemberResponse registerMemberResponse = new RegisterMemberResponse();
        registerMemberResponse.setMessage("Registration Successful");
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1,memberService.findAllMember().size());

        assertThrows(MemberExistException.class,()-> memberService.registerMember(registerMemberRequest));
    }

    @Test
    public void registerMember_loginMember() throws MemberNotFoundException, MemberExistException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setEmail("praise@gmail.com");
        registerMemberRequest.setUsername("praise");
        registerMemberRequest.setPassword("paul");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        registerMemberResponse.setMessage("Registration Successful");
        assertThat(registerMemberResponse.getMessage()).isNotNull();

        assertEquals(1,memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("praise@gmail.com");
        loginMemberRequest.setPassword("paul");
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());
    }

    @Test
    public void testThatMemberCannotLoginWithoutBeingRegistered_throwMemberNotFoundException() throws IncorrectUsernameException, MemberNotFoundException {
        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("darkRoyals@gmail.com");
        loginMemberRequest.setPassword("passwords");
        assertThrows(MemberNotFoundException.class,()->memberService.login(loginMemberRequest));
    }

    @Test
    void logout() throws MemberExistException, MemberNotFoundException, MemberNotLoggedInException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        registerMemberResponse.setMessage("Registration Successful");
        assertThat(registerMemberResponse.getMessage()).isNotNull();

        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);


        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());
        memberService.logout(registerMemberResponse.getUserID());
        assertFalse(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());
    }

    @Test
    public void testThatMemberCannotLoginWithWrongDetails() throws MemberExistException{
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("israel@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        registerMemberResponse.setMessage("Registration Successful");
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("israel@gmails.com");
        loginMemberRequest.setPassword("passwords");
        assertThrows(MemberNotFoundException.class,()->memberService.login(loginMemberRequest));
    }

    @Test
    public void findAllMember() throws MemberExistException, MemberNotFoundException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        registerMemberResponse.setMessage("Registration Successful");
        assertThat(registerMemberResponse.getMessage()).isNotNull();

        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());

        assertEquals(1,memberService.findAll().size());
    }

    @Test
    public void register_login_logout_findMember() throws MemberExistException, MemberNotFoundException, MemberNotLoggedInException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        RegisterMemberResponse registerMemberResponse  = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);
        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());

        FindMemberRequest findMemberRequest = new FindMemberRequest();
        findMemberRequest.setEmail("tobi@gmail.com");
        FindMemberResponse response = memberService.findMember(findMemberRequest);
        assertEquals("tobi@gmail.com", response.getEmail());



        memberService.logout(response.getId());
        assertFalse(memberService.findMemberById(response.getId()).isLogStatus());


    }

//    @Test
//    public void testMemberCanBorrowBook() throws MemberExistException, IncorrectUsernameException, MemberNotFoundException {
//        Member member = new Member();
//        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
//        registerMemberRequest.setFirstName("kemi");
//        registerMemberRequest.setLastName("yooo");
//        registerMemberRequest.setEmail("tobi@gmail.com");
//        registerMemberRequest.setUsername("darkRoyals");
//        registerMemberRequest.setPassword("passwords");
//        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
//        assertNotNull(registerMemberResponse);
//        assertEquals(1, memberService.findAllMember().size());
//
//        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
//        loginMemberRequest.setUsername("darkRoyals");
//        loginMemberRequest.setPassword("passwords");
//        LoginMemberResponse loginMemberResponse = memberService.loginMember(loginMemberRequest);
//        assertNotNull(loginMemberResponse);
//        assertTrue(member.isLogStatus());
//
//        BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
//        borrowBookRequest.setTitle("The ice twins");
//        borrowBookRequest.setAuthor("My daddy");
//        borrowBookRequest.setIsbn("1234567898765");
//        borrowBookRequest.setDateBorrowed(LocalDate.parse("12/02/2024"));
//        borrowBookRequest.setDueDate(LocalDate.parse("26/02/2024"));
//        BorrowBookResponse borrowBookResponse = memberService.borrowBook(borrowBookRequest);
//        assertEquals(1,memberService.getNumberOfBorrowedBook());
//
//
//
//    }
//




}
