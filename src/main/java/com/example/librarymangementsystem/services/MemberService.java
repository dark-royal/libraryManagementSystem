package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;

import java.util.List;

public interface MemberService {

    List<Member> findAllMember();

    ReturnBookResponse returnBookFromUser(Member member, Book book);

    Member registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException;

    Member findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException;
    List<Member> findAll();

    int getNumberOfBorrowedBook();

    void borrowBook(BorrowBookRequest borrowBookRequest);

    void login(LoginMemberRequest loginMemberRequest) throws MemberNotFoundException;

    void logout(Long id);


    Member findMemberById(Long id);

    void deleteAll();
}
