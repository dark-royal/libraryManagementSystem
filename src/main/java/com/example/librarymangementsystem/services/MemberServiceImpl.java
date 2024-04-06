package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Member;
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
    if (member.isPresent()) throw new MemberExistException("%s exists already",email);

}

    @Override
    public Member findMember(FindMemberRequest findMemberRequest) throws MemberNotFoundException {
        Optional<Member> foundMember = memberRepository.findMemberByEmail(findMemberRequest.getEmail());
        if(foundMember.isEmpty()) throw new MemberNotFoundException("%s not found");
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
    public void borrowBook(BorrowBookRequest borrowBookRequest) {
        for (Book book : books) {
            if (book.getTitle().equals(borrowBookRequest.getTitle()) &&
                    book.getAuthor().equals(borrowBookRequest.getAuthor()) &&
                    book.isAvailable()) {
                if (isMemberisPresent(borrowBookRequest.getMemberId().getId())) {
                    book.setBorrowedDate(LocalDate.now());
                    book.setDueDate(borrowBookRequest.getDueDate());
                    book.setAvailable(false);
                }
//                else{
//                    throw new
//                    }





            }

        }
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
    public void logout(Long id) {
        Optional<Member> member = memberRepository.findById(id);
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


    private boolean isMemberisPresent(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.isPresent();
    }

    }

