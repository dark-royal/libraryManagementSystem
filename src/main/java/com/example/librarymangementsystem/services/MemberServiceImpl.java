package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.data.repositories.MemberRepository;
import com.example.librarymangementsystem.dtos.requests.BorrowBookRequest;
import com.example.librarymangementsystem.dtos.requests.FindMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
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

    private List<Book> books;

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }


    @Override
    public ReturnBookResponse returnBookFromUser(Member member, Book book) {

        return null;
    }

    @Override
    public Member registerMember(RegisterMemberRequest registerMemberRequest) throws MemberExistException {
        validate(registerMemberRequest.getEmail());
        Member member1 = new Member();
        member1.setUsername(registerMemberRequest.getUsername());
        member1.setPassword(registerMemberRequest.getPassword());
        member1.setFirstName(registerMemberRequest.getFirstName());
        member1.setLastName(registerMemberRequest.getLastName());
        member1.setEmail(registerMemberRequest.getEmail());
        return memberRepository.save(member1);
    }
public void validate(String email) throws MemberExistException {
    Optional<Member> member = memberRepository.findMemberByEmail(email);
    if (member.isPresent()) throw new MemberExistException("%s exists already", email);

}

    @Override
    public Member findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException {
        validateLoginStatus();
        Optional<Member> foundMember = memberRepository.findMemberByEmail(findMemberRequest.getEmail());
        if(!foundMember.isPresent()) throw new MemberNotFoundException("%s not found");
        return foundMember.get();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Override

    public int getNumberOfBorrowedBook() {
        return 0;
    }

@Override
public Book borrowBook(BorrowBookRequest borrowBookRequest) throws MemberNotLoggedInException {
        validateLoginStatus();
    List<Book> bookBorrow = bookRepository.findBookByTitleAndAuthorAndCategory(borrowBookRequest.getTitle(),borrowBookRequest.getAuthor(),borrowBookRequest.getCategory());
    for(Book book:bookBorrow) {
        if (!book.isAvailable()) {
            throw new BookNotFoundAvailableException("Book is not available to borrow");
        }
    }

        for(Book book1: bookBorrow){
            book1.setAvailable(false);
        bookRepository.save(book1);
    }
        borrowBookRequest.getBorrowBooks().addAll(bookBorrow);




    return (Book) bookBorrow;
        }



    @Override
    public void login(LoginMemberRequest loginMemberRequest) throws MemberNotFoundException {
        Optional<Member> member = memberRepository.findMemberByEmail(loginMemberRequest.getEmail());
        if (member.isPresent()){
           Member member1 = member.get();
           member1.setLogStatus(true);
           memberRepository.save(member1);
       }else {
           throw new MemberNotFoundException(STR."\{loginMemberRequest.getEmail()} not found");
       }

    }

    @Override
    public void logout(Long id) throws MemberNotLoggedInException {
        Optional<Member> member = memberRepository.findById(id);
        validateLoginStatus();
        if (member.isPresent()){
            Member member1 = member.get();
            member1.setLogStatus(false);
            memberRepository.save(member1);
        }else {
            throw new RuntimeException("Kosi");
        }
    }

    @Override
    public Member findMemberById(Long id ) {
      return  memberRepository.findById(id).get();
    }

    @Override
    public void deleteAll() {
        memberRepository.deleteAll();
    }

    }

    public void validateLoginStatus() throws MemberNotLoggedInException {
        Staff staff = new Staff();
        if(!staff.isLoginStatus()){
            throw new MemberNotLoggedInException("pls login in");

        }
    }

    }

