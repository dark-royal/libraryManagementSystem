package com.example.librarymangementsystem.controllers;

import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;
import com.example.librarymangementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @PostMapping("/signup")
    public String signUp(@RequestBody RegisterMemberRequest registerMemberRequest){
        try{
            memberService.registerMember(registerMemberRequest);
        }catch (MemberExistException e){
            return e.getMessage();
        }
        return "registeration successful";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginMemberRequest loginMemberRequest)  {
        try{
            memberService.login(loginMemberRequest);
        }catch (MemberNotFoundException e){
            return e.getMessage();
        }
       return "login successful";
    }
}
