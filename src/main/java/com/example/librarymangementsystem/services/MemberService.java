package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.exceptions.BookNotFoundException;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;
import com.example.librarymangementsystem.exceptions.MemberNotLoggedInException;

import java.util.List;

public interface MemberService {

    List<Member> findAllMember();



    Book returnBook(ReturnBookRequest returnBookRequest) throws MemberNotLoggedInException, BookNotFoundException;

    RegisterMemberResponse registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException;

    FindMemberResponse findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException, MemberNotLoggedInException;
    List<Member> findAll();


    Book borrowBook(BorrowBookRequest borrowBookRequest) throws MemberNotLoggedInException, BookNotFoundException;

    LoginMemberResponse login(LoginMemberRequest loginMemberRequest) throws MemberNotFoundException;



    void logout(LogoutMemberRequest logoutMemberRequest) throws MemberNotLoggedInException;

    Member findMemberById(Long id);

    void deleteAll();


    Book findBorrowedBook(String email);

    List<Book> findAllBorrowedBooks(String email);

    //List<Book> findAllReturnedBooks(String email);
}
