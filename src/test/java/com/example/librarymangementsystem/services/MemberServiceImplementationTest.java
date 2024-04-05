package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceImplementationTest {

    @Autowired
    MemberService memberService;

    @Test
    public void testRegisterMember() throws MemberExistException {
       RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("praise");
        registerMemberRequest.setLastName("aramide");
        registerMemberRequest.setEmail("praise@gmail.com");
        registerMemberRequest.setUsername("darkRoyal");
        registerMemberRequest.setPassword("password");
        memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());
    }

    @Test
    public void testThatMemberCannotRegisterWithTheSameUsername() throws MemberExistException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("praise");
        registerMemberRequest.setLastName("aramide");
        registerMemberRequest.setEmail("praise@gmail.com");
        registerMemberRequest.setUsername("darkRoyal");
        registerMemberRequest.setPassword("password");
        memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        RegisterMemberRequest registerMemberRequest1 = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("praise");
        registerMemberRequest.setLastName("aramide");
        registerMemberRequest.setEmail("praise@gmail.com");
        registerMemberRequest.setUsername("darkRoyal");
        registerMemberRequest.setPassword("password");
        assertThrows(MemberExistException.class,()-> memberService.registerMember(registerMemberRequest1));


    }

    @Test
    public void registerMember_loginMember() throws MemberNotFoundException, MemberExistException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(1L).isLogStatus());
    }

    @Test
    public void testThatMemberCannotLoginWithoutBeingRegistered_throwMemberNotFoundException() throws IncorrectUsernameException, MemberNotFoundException {
        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("darkRoyals@gmail.com");
        loginMemberRequest.setPassword("passwords");
        assertThrows(MemberNotFoundException.class,()->memberService.login(loginMemberRequest));
    }

    @Test
    void logout(){
        memberService.logout(1L);
        assertFalse(memberService.findMemberById(1L).isLogStatus());
    }

    @Test
    public void testThatMemberCannotLoginWithWrongDetails() throws MemberExistException{
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("israel@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        memberService.registerMember(registerMemberRequest);
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
        memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(1L).isLogStatus());
        assertEquals(1,memberService.findAll().size());
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
