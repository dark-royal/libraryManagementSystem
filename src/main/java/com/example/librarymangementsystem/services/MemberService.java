package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.BorrowBookResponse;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
import com.example.librarymangementsystem.exceptions.IncorrectPassword;
import com.example.librarymangementsystem.exceptions.IncorrectUsernameException;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAllMember();

    ReturnBookResponse returnBookFromUser(Member member, Book book);

    void registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException;

    Member findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException;
    List<Member> findAll();

    int getNumberOfBorrowedBook();

    void borrowBook(BorrowBookRequest borrowBookRequest);

    void login(LoginMemberRequest loginMemberRequest) throws MemberNotFoundException;

    void logout(Long id);


    Member findMemberById(Long id);
}
