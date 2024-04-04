package com.example.librarymangementsystem.utils;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.dtos.requests.BorrowBookRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.dtos.responses.BorrowBookResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {

    public static Member map(RegisterMemberRequest registerMemberRequest) {
        Member member = new Member();
        member.setFirstName(registerMemberRequest.getFirstName());
        member.setLastName(registerMemberRequest.getLastName());
        member.setUsername(registerMemberRequest.getUsername());
        member.setPassword(registerMemberRequest.getPassword());
        return member;

    }


    public static RegisterMemberResponse map(Member saveMember){
        RegisterMemberResponse registerMemberResponse = new RegisterMemberResponse();
        registerMemberResponse.setUsername(saveMember.getUsername());
        registerMemberResponse.setDate(String.valueOf(DateTimeFormatter.ofPattern(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()))));
        return registerMemberResponse;

    }

    public static Member map(LoginMemberRequest loginMemberRequest) {
        Member member = new Member();
        member.setUsername(loginMemberRequest.getUsername());
        member.setPassword(loginMemberRequest.getPassword());
        return member;

    }


    public static LoginMemberResponse maps(Member saveMember){
        LoginMemberResponse loginMemberResponse = new LoginMemberResponse();
        loginMemberResponse.setUsername(saveMember.getUsername());
        loginMemberResponse.setPassword(saveMember.getPassword());
                return loginMemberResponse;

    }

    public static BorrowBookResponse mapps(Member borrowBook){
        BorrowBookResponse borrowBookResponse = new BorrowBookResponse();
        borrowBookResponse.setUsername(borrowBook.getUsername());
        borrowBookResponse.setPassword(borrowBook.getPassword());
        borrowBookResponse.setBorrowedDate(borrowBook.getBorrowedDate());
        borrowBookResponse.setDueDate(borrowBook.getDueDate());
        return borrowBookResponse;

    }

    public static Member map(BorrowBookRequest borrowBookRequest){
        Book book = new Book();
        book.setTitle(borrowBookRequest.getTitle());
        book.setAuthor(borrowBookRequest.getAuthor());
        book.setBooks(borrowBookRequest.getBooks());
        book.

    }
}
public static Member map(LoginMemberRequest loginMemberRequest) {
    Member member = new Member();
    member.setUsername(loginMemberRequest.getUsername());
    member.setPassword(loginMemberRequest.getPassword());
    return member;
