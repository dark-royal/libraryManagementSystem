package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.FindMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


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
        registerMemberRequest.setFirstName("praisi");
        registerMemberRequest.setLastName("aramidi");
        registerMemberRequest.setEmail("praisi@gmail.com");
        registerMemberRequest.setUsername("darkRoyali");
        registerMemberRequest.setPassword("passwords");
        memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        assertThrows(MemberExistException.class,()-> memberService.registerMember(registerMemberRequest));


    }

    @Test
    public void registerMember_loginMember() throws MemberNotFoundException, MemberExistException {
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest();
        registerMemberRequest.setFirstName("kemi");
        registerMemberRequest.setLastName("yooo");
        registerMemberRequest.setEmail("tobi@gmail.com");
        registerMemberRequest.setUsername("darkRoyals");
        registerMemberRequest.setPassword("passwords");
        Member member = memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(member.getId()).isLogStatus());
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
        Member member = memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(member.getId()).isLogStatus());
        memberService.logout(member.getId());
        assertFalse(memberService.findMemberById(member.getId()).isLogStatus());
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
        Member member = memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);
        assertTrue(memberService.findMemberById(member.getId()).isLogStatus());

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
        Member member = memberService.registerMember(registerMemberRequest);
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("tobi@gmail.com");
        loginMemberRequest.setPassword("passwords");
        memberService.login(loginMemberRequest);
        assertTrue(memberService.findMemberById(member.getId()).isLogStatus());

        FindMemberRequest findMemberRequest = new FindMemberRequest();
        findMemberRequest.setEmail("tobi@gmail.com");
        Member member1 = memberService.findMember(findMemberRequest);
        assertEquals("tobi@gmail.com", member.getEmail());



        memberService.logout(member.getId());
        assertFalse(memberService.findMemberById(member.getId()).isLogStatus());


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