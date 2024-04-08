package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.data.repositories.MemberRepository;
import com.example.librarymangementsystem.dtos.requests.BorrowBookRequest;
import com.example.librarymangementsystem.dtos.requests.FindMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.dtos.responses.RegisterMemberResponse;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
import com.example.librarymangementsystem.exceptions.BookNotFoundAvailableException;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;
import com.example.librarymangementsystem.exceptions.MemberNotLoggedInException;
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

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public ReturnBookResponse returnBookFromUser(Member member, Book book) {
        return null;
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
         response.setMessage("Member "+ member1.getFirstName()+ " Created sucessfully");
         response.setUserID(member1.getId());
        return response;
    }

    public void validate(String email) throws MemberExistException {
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if (member.isPresent()) throw new MemberExistException("%s exists already", email);

    }

    @Override
    public FindMemberResponse findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException, MemberNotLoggedInException {
        Optional<Member> foundMember = memberRepository.findMemberByEmail(findMemberRequest.getEmail());
        validateLoginStatus();
        if (foundMember.isEmpty()) throw new MemberNotFoundException("%s not found");
        FindMemberResponse findMemberResponse = new FindMemberResponse();
        findMemberResponse.setMessage(" Member found successful");
        findMemberResponse.setEmail(foundMember.get().getEmail());
        findMemberResponse.setUsername(foundMember.get().getUsername());
        findMemberResponse.setFirstName(foundMember.get().getFirstName());
        findMemberResponse.setLogStatus(foundMember.get().isLogStatus());
        findMemberResponse.setLastName(foundMember.get().getLastName());
        findMemberResponse.setBorrowedBooks(foundMember.get().getBorrowedBooks());
        findMemberResponse.setDueDate(foundMember.get().getDueDate());
        findMemberResponse.setBorrowedDate(foundMember.get().getBorrowedDate());
        findMemberResponse.setId(foundMember.get().getId());
        return findMemberResponse;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public int getNumberOfBorrowedBook() {
        return 0;
    }

    @Override
    public Book borrowBook(BorrowBookRequest borrowBookRequest) throws MemberNotLoggedInException {
        validateLoginStatus();
        List<Book> bookBorrow = bookRepository.findBookByTitleAndAuthorAndCategory(borrowBookRequest.getTitle(), borrowBookRequest.getAuthor(), borrowBookRequest.getCategory());
        for (Book book : bookBorrow) {
            if (!book.isAvailable()) {
                throw new BookNotFoundAvailableException("Book is not available to borrow");
            }
        }

        for (Book book1 : bookBorrow) {
            book1.setAvailable(false);
            bookRepository.save(book1);
        }
        borrowBookRequest.getBorrowBooks().addAll(bookBorrow);


        return (Book) bookBorrow;
    }

    @Override
    public LoginMemberResponse login(LoginMemberRequest loginMemberRequest) throws MemberNotFoundException {
        Optional<Member> member = memberRepository.findMemberByEmail(loginMemberRequest.getEmail());
        if (member.isPresent()) {
            Member member1 = member.get();
            member1.setLogStatus(true);
            memberRepository.save(member1);
        } else {
            throw new MemberNotFoundException(STR."\{loginMemberRequest.getEmail()} not found");
        }

        return null;
    }

    @Override
    public void logout(Long id) throws MemberNotLoggedInException {
        Optional<Member> member = memberRepository.findById(id);
        validateLoginStatus();

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

    public void validateLoginStatus() throws MemberNotLoggedInException {
        Member member = new Member();
        if (!member.isLogStatus()) {
            throw new MemberNotLoggedInException("pls login in");

        }
    }
}
