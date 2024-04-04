package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.BorrowBookResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
import com.example.librarymangementsystem.exceptions.IncorrectUsernameException;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;

import java.util.List;

public interface MemberService {

    List<Member> findAllMember();

    BorrowBookResponse borrowBookToUser(Staff staff, Book book);

    ReturnBookResponse returnBookFromUser(Staff staff, Book book);

    RegisterMemberResponse registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException;

    Member findMember(FindMemberRequest findMemberRequest);

//    LoginMemberResponse loginStaff(LoginMemberRequest loginMemberRequest);

    LoginMemberResponse loginMember(LoginMemberRequest loginMemberRequest) throws IncorrectUsernameException, MemberNotFoundException;

    Long count();

    int getNumberOfBorrowedBook();

    BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest);
}
