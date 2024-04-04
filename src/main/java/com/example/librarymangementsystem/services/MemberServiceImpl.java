package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.BorrowedBook;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.MemberRepository;
import com.example.librarymangementsystem.dtos.requests.BorrowBookRequest;
import com.example.librarymangementsystem.dtos.requests.FindMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.dtos.responses.BorrowBookResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.librarymangementsystem.utils.Mapper.map;
import static com.example.librarymangementsystem.utils.Mapper.maps;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public BorrowBookResponse borrowBookToUser(Staff staff, Book book) {
//        if (borrowedBooks.contains(book)) {
//            throw new BookAlreadyBorrowedException("Book '" + book.getTitle() + "' is already borrowed.");
//        } else if (availableBooks.contains(book)) {
//            availableBooks.remove(book);
//            borrowedBooks.add(book);
//            System.out.println("Book '" + book.getTitle() + "' borrowed successfully by " + staff.getUsername());
//        } else {
//            throw new BookNotBorrowedException("Book '" + book.getTitle() + "' is currently unavailable.");
//        }
        return null;
    }


    @Override
    public ReturnBookResponse returnBookFromUser(Staff staff, Book book) {
//        if (!borrowedBooks.contains(book)) {
//            throw new BookNotBorrowedException("Book '" + book.getTitle() + "' is not borrowed.");
//        } else {
//            borrowedBooks.remove(book);
//            availableBooks.add(book);
//            System.out.println("Book '" + book.getTitle() + "' returned successfully by " + staff.getUsername());
//        }
        return null;
    }

    @Override
    public RegisterMemberResponse registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException {
        registerMemberRequest.setUsername(registerMemberRequest.getUsername().toLowerCase());
        validate(registerMemberRequest.getUsername());
        Member member = map(registerMemberRequest);
        RegisterMemberResponse result = map(member);

        memberRepository.save(member);
        return result;


    }

    @Override
    public Member findMember(FindMemberRequest findMemberRequest) {
        return null;
    }

    private void validate(String username) throws MemberExistException {
        boolean exist =  memberRepository.existsByUsername(username);
        if (exist) throw new MemberExistException("staff exist already");

    }


    //      @Ov  erride
//        public Staff findStaff(FindStaffRequest findStaffRequest) {
//            Staff foundStaff = staffRepository.findStaffByUsername(findStaffRequest.getUsername());
//            if (foundStaff == null) throw new StaffNotFoundException("staff not found");
//            return foundStaff;
//        }
    @Override
    public LoginMemberResponse loginMember(LoginMemberRequest loginMemberRequest) throws IncorrectUsernameException, MemberNotFoundException {
        validateLogin(loginMemberRequest);
        Member member = map(loginMemberRequest);
        LoginMemberResponse result = maps(member);
        memberRepository.save(member);
        return result;


    }

    public void validateLogin(LoginMemberRequest loginMemberRequest) throws IncorrectUsernameException, MemberNotFoundException {
        Member member = memberRepository.findMemberByUsername(loginMemberRequest.getUsername());
        if (member == null) throw new MemberNotFoundException("%s not found",loginMemberRequest.getUsername());
        if (!member.getUsername().equalsIgnoreCase(loginMemberRequest.getUsername()) && member.getPassword().equalsIgnoreCase(loginMemberRequest.getPassword()))throw new IncorrectUsernameException("invalid username or password");

        else{
            member.setLogStatus(true);
            memberRepository.save(member);
        }

    }

    public Long count(){
        return memberRepository.count();
    }

    @Override
    public int getNumberOfBorrowedBook() {
        return 0;
    }

    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest) {

    }
}

