package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.data.repositories.MemberRepository;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookServices bookServices;

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public ReturnBookResponse returnBookFromUser(ReturnBookRequest returnBookRequest) throws MemberNotLoggedInException, BookNotFoundException {
        //validateLoginStatus(returnBookRequest.);
        Member member = new Member();
        Book book = bookServices.findBook(returnBookRequest.getBookId());
        if (book == null) throw new BookNotFoundException("book  not found ooooooh");
        book.setAvailable(true);
        bookRepository.save(book);

        List<Book> borrowBookList = member.getBorrowedBooks();
        borrowBookList.remove(book);
        bookRepository.save(book);

        ReturnBookResponse response = new ReturnBookResponse();
        response.setMessage("returned book successfully");

        return response;

    }

    @Override
    public RegisterMemberResponse registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException {
        validate(registerMemberRequest.getEmail());
        Member member1 = new Member();
        member1.setUsername(registerMemberRequest.getUsername());
        member1.setPassword(registerMemberRequest.getPassword());
        member1.setFirstName(registerMemberRequest.getFirstName());
        member1.setLastName(registerMemberRequest.getLastName());
        member1.setEmail(registerMemberRequest.getEmail());
        memberRepository.save(member1);
        RegisterMemberResponse response = new RegisterMemberResponse();
        response.setMessage(STR."Member \{member1.getFirstName()} Created sucessfully");
        response.setUserID(member1.getId());
        return response;
    }

    public void validate(String email) throws MemberExistException {
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if (member.isPresent()) throw new MemberExistException("%s exists already", email);

    }

    @Override
    public FindMemberResponse findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException, MemberNotLoggedInException {
        validateLoginStatus(findMemberRequest.getEmail());
        Optional<Member> foundMember = memberRepository.findMemberByEmail(findMemberRequest.getEmail());
        if (foundMember.isEmpty()) throw new MemberNotFoundException("%s not found");
        FindMemberResponse findMemberResponse = new FindMemberResponse();
        findMemberResponse.setMessage(" Member found successful");
        findMemberResponse.setMembers(foundMember.get());
        return findMemberResponse;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    @Override
    public Book borrowBook(BorrowBookRequest borrowBookRequest) throws MemberNotLoggedInException, BookNotFoundException {
        validateLoginStatus(borrowBookRequest.getEmail());

        Member member= memberRepository.findMemberByEmail(borrowBookRequest.getEmail()).orElseThrow(()->new MemberNotFoundException("Member not found"));
        Book book  = bookServices.findBook(borrowBookRequest.getBookId());
        book.setAvailable(false);
        bookRepository.save(book);

        List<Book> borrowBookList = member.getBorrowedBooks();
        borrowBookList.add(book);

        member.setBorrowedDate(LocalDate.now());
        member.setDueDate(LocalDate.now().plusDays(3));

        return book;
    }

    @Override
    public LoginMemberResponse login(LoginMemberRequest loginMemberRequest) throws MemberNotFoundException {
        Optional<Member> member = memberRepository.findMemberByEmail(loginMemberRequest.getEmail());
        if (member.isPresent()) {
            Member member1 = member.get();
            member1.setLogStatus(true);
            memberRepository.save(member1);
            LoginMemberResponse response = new LoginMemberResponse();
            response.setMessage("Login Successful");
            return response;
        } else {
            throw new MemberNotFoundException(STR."\{loginMemberRequest.getEmail()} not found");
        }

    }

    @Override
    public void logout(LogoutMemberRequest logoutMemberRequest) throws MemberNotLoggedInException {
        validateLoginStatus(logoutMemberRequest.getEmail());
        Optional<Member> member = memberRepository.findMemberByEmail(logoutMemberRequest.getEmail());

        if (member.isPresent()) {
            Member member1 = member.get();
            member1.setLogStatus(false);
            memberRepository.save(member1);
        } else {
            throw new RuntimeException("Kosi");
        }
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public void deleteAll() {
        memberRepository.deleteAll();
    }

    @Override
    public int findBorrowedBook() {
        return 0;
    }

    public void validateLoginStatus(String email) throws MemberNotLoggedInException {
        Member member = memberRepository.findMemberByEmail(email).orElseThrow(() -> new MemberNotFoundException("Member not found"));
        if (!member.isLogStatus()) {
            throw new MemberNotLoggedInException("pls login in");

        }
    }
}
