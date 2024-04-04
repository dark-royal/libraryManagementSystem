package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.BorrowBookRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.dtos.responses.BorrowBookResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.exceptions.IncorrectUsernameException;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertNotNull(registerMemberResponse);
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
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertNotNull(registerMemberResponse);
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
    public void registerMember_loginMember() throws MemberExistException, IncorrectUsernameException, MemberNotFoundException {
        Member member = new Member();
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertNotNull(registerMemberResponse);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setUsername("darkRoyals");
        loginMemberRequest.setPassword("passwords");
        LoginMemberResponse loginMemberResponse = memberService.loginMember(loginMemberRequest);
        assertNotNull(loginMemberResponse);
        assertTrue(member.isLogStatus());
    }

    @Test
    public void testThatMemberCannotLoginWithoutBeingRegistered_throwMemberNotFoundException() throws IncorrectUsernameException, MemberNotFoundException {
        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setUsername("darkRoyals");
        loginMemberRequest.setPassword("passwords");
        assertThrows(MemberNotFoundException.class,()->memberService.loginMember(loginMemberRequest));


    }

    @Test
    public void testMemberCanBorrowBook() throws MemberExistException, IncorrectUsernameException, MemberNotFoundException {
        Member member = new Member();
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertNotNull(registerMemberResponse);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setUsername("darkRoyals");
        loginMemberRequest.setPassword("passwords");
        LoginMemberResponse loginMemberResponse = memberService.loginMember(loginMemberRequest);
        assertNotNull(loginMemberResponse);
        assertTrue(member.isLogStatus());

        BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
        borrowBookRequest.setTitle("The ice twins");
        borrowBookRequest.setAuthor("My daddy");
        borrowBookRequest.setIsbn("1234567898765");
        borrowBookRequest.setDateBorrowed(LocalDate.parse("12/02/2024"));
        borrowBookRequest.setDueDate(LocalDate.parse("26/02/2024"));
        BorrowBookResponse borrowBookResponse = memberService.borrowBook(borrowBookRequest);
        assertEquals(1,memberService.getNumberOfBorrowedBook());



    }





}
