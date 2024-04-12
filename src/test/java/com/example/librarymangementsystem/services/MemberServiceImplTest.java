package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

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
       RegisterMemberRequest registerMemberRequest = registerRequests("praise", "oyewole","praise@gmail.com");

        RegisterMemberResponse response = memberService.registerMember(registerMemberRequest);
        assertThat(response.getMessage()).isNotNull();
        assertEquals(1,memberService.findAllMember().size());
    }

    @Test
    public void testThatMemberCannotRegisterWithTheSameUsername() throws MemberExistException {
        RegisterMemberRequest registerMemberRequest = registerRequests("yii", "yoo","praise1@gmail.com");
        RegisterMemberResponse response = memberService.registerMember(registerMemberRequest);
        assertThat(response.getMessage()).isNotNull();
        assertEquals(1,memberService.findAllMember().size());

        assertThrows(MemberExistException.class,()-> memberService.registerMember(registerMemberRequest));
    }

    @Test
    public void registerMember_loginMember() throws MemberNotFoundException, MemberExistException {
        RegisterMemberRequest registerMemberRequest = registerRequests("ned", "nedo","praise2@gmail.com");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();

        assertEquals(1,memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("praise2@gmail.com");
        loginMemberRequest.setPassword(registerMemberRequest.getPassword());
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
        RegisterMemberRequest registerMemberRequest = registerRequests("heritage", "yoo","praise1@gmail.com");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail(registerMemberRequest.getEmail());
        loginMemberRequest.setPassword(registerMemberRequest.getPassword());
        memberService.login(loginMemberRequest);


        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());
        LogoutMemberRequest logoutMemberRequest = new LogoutMemberRequest();
        logoutMemberRequest.setEmail(registerMemberRequest.getEmail());
        memberService.logout(logoutMemberRequest);
        assertFalse(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());
    }

    @Test
    public void testThatMemberCannotLoginWithWrongDetails() throws MemberExistException{
        RegisterMemberRequest registerMemberRequest = registerRequests("yinola", "kemi","praise13@gmail.com");

        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail("ned");
        loginMemberRequest.setPassword("name");
        assertThrows(MemberNotFoundException.class,()->memberService.login(loginMemberRequest));
    }

    @Test
    public void findAllMember() throws MemberExistException, MemberNotFoundException {
        RegisterMemberRequest registerMemberRequest = registerRequests("marvel", "mercy","praise14@gmail.com");
        RegisterMemberResponse registerMemberResponse = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();

        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail(registerMemberRequest.getEmail());
        loginMemberRequest.setPassword(registerMemberRequest.getPassword());
        memberService.login(loginMemberRequest);

        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());

        assertEquals(1,memberService.findAll().size());
    }

    @Test
    public void register_login_logout_findMember() throws MemberExistException, MemberNotFoundException, MemberNotLoggedInException {
        RegisterMemberRequest registerMemberRequest = registerRequests("precious", "preshy","precious@gmail.com");
        RegisterMemberResponse registerMemberResponse  = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail(registerMemberRequest.getEmail());
        loginMemberRequest.setPassword(registerMemberRequest.getPassword());
        memberService.login(loginMemberRequest);
        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());

        FindMemberRequest findMemberRequest = new FindMemberRequest();
        findMemberRequest.setEmail(registerMemberRequest.getEmail());
        FindMemberResponse response = memberService.findMember(findMemberRequest);
        assertEquals(registerMemberRequest.getEmail(), response.getMembers().getEmail());



    }

    @Test
    public void testMemberCanBorrowBook() throws MemberExistException, MemberNotLoggedInException, BookNotFoundException {
        RegisterMemberRequest registerMemberRequest = registerRequests("gideon", "bare","bare@gmail.com");
        RegisterMemberResponse registerMemberResponse  = memberService.registerMember(registerMemberRequest);
        assertThat(registerMemberResponse.getMessage()).isNotNull();
        assertEquals(1, memberService.findAllMember().size());

        LoginMemberRequest loginMemberRequest = new LoginMemberRequest();
        loginMemberRequest.setEmail(registerMemberRequest.getEmail());
        loginMemberRequest.setPassword(registerMemberRequest.getPassword());
        memberService.login(loginMemberRequest);
        assertTrue(memberService.findMemberById(registerMemberResponse.getUserID()).isLogStatus());

       BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
       borrowBookRequest.setTitle("Ada my love");
       borrowBookRequest.setAuthor("china achebe");
       borrowBookRequest.setCategory(Category.HORROR);
       borrowBookRequest.setDateBorrowed(LocalDate.now());
       borrowBookRequest.setDueDate(LocalDate.now().plusDays(3));
       memberService.borrowBook(borrowBookRequest);
       assertEquals(1,memberService.findBorrowedBook());

    }


    public RegisterMemberRequest registerRequests(String firstName, String lastName, String email){
        return RegisterMemberRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username("my name")
                .password("your head")
                .build();


    }



}
