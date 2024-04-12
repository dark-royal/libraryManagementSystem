package com.example.librarymangementsystem.controllers;

import com.example.librarymangementsystem.dtos.requests.FindMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LogoutMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.dtos.responses.FindMemberResponse;
import com.example.librarymangementsystem.dtos.responses.LoginMemberResponse;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;
import com.example.librarymangementsystem.exceptions.MemberNotLoggedInException;
import com.example.librarymangementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody RegisterMemberRequest registerMemberRequest) throws MemberExistException {
       try{
           memberService.registerMember(registerMemberRequest);
           return "registered successfully";
       }catch (MemberExistException e){
           return e.getMessage();
       }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody LoginMemberRequest loginMemberRequest) {
        LoginMemberResponse response = memberService.login(loginMemberRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);



        }

    @PostMapping("/logout")
    public String logout(@RequestBody LogoutMemberRequest logoutMemberRequest) {
        try {
            memberService.logout(logoutMemberRequest);
            return "logout successful";
        } catch (Exception | MemberNotLoggedInException e) {
            return e.getMessage();
        }

    }

    @GetMapping("/findMember")
    public ResponseEntity<FindMemberResponse> findMember(@RequestBody FindMemberRequest findMemberRequest) throws MemberNotFoundException, MemberNotLoggedInException {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findMember(findMemberRequest));


    }

    @GetMapping("findAll")
    public String findAll() {
        memberService.findAll();
        return "all member found successfully";
    }
@GetMapping("/findMemberById{id}")
    public String findMemberById(@PathVariable("id") Long id) {
    try {
        memberService.findMemberById(id);
        return "member found successfully";

    } catch (MemberNotFoundException e) {
        return e.getMessage();
    }
}




}
