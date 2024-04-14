package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.data.repositories.MemberRepository;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Book returnBookFromUser(ReturnBookRequest returnBookRequest) throws MemberNotLoggedInException, BookNotFoundException {
        validateLoginStatus(returnBookRequest.getEmail());
        Member member = memberRepository.findMemberByEmail(returnBookRequest.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));


        Book book = bookRepository.findBookByTitleAndAuthor(returnBookRequest.getTitle(), returnBookRequest.getAuthor())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.setAvailable(true);

        List<Book> borrowBookList = member.getReturnedBooks();
        borrowBookList.remove(book);
        member.setBorrowedBooks(borrowBookList);

        member.setBorrowedDate(returnBookRequest.getReturnedDate());


        memberRepository.save(member);

        return bookRepository.save(book);

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
            Member member = memberRepository.findMemberByEmail(borrowBookRequest.getEmail())
                    .orElseThrow(() -> new MemberNotFoundException("Member not found"));


            Book book = bookRepository.findBookByTitleAndAuthor(borrowBookRequest.getTitle(), borrowBookRequest.getAuthor())
                    .orElseThrow(() -> new BookNotFoundException("Book not found"));

            book.setAvailable(false);

            List<Book> borrowBookList = member.getBorrowedBooks();
            borrowBookList.add(book);
            member.setBorrowedBooks(borrowBookList);

            member.setBorrowedDate(borrowBookRequest.getDateBorrowed());
            member.setDueDate(borrowBookRequest.getDueDate());

            memberRepository.save(member);

        return bookRepository.save(book);
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
    public Book findBorrowedBook(String email) {

        return null;
    }

    @Override
    public List<Book> findAllBorrowedBooks(String email){
        Member member = memberRepository.findMemberByEmail(email).orElseThrow(()-> new MemberNotFoundException("Member not found"));
        return member.getBorrowedBooks();

    }

    @Override
    public List<Book> findAllReturnedBooks(String email) {
        Member member = memberRepository.findMemberByEmail(email).orElseThrow(()-> new MemberNotFoundException("Member not found"));
        return member.getReturnedBooks();
    }

    public void validateLoginStatus(String email) throws MemberNotLoggedInException {
        Member member = memberRepository.findMemberByEmail(email).orElseThrow(() -> new MemberNotFoundException("Member not found"));
        if (!member.isLogStatus()) {
            throw new MemberNotLoggedInException("pls login in");

        }
    }
}
