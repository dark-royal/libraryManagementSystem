package com.example.librarymangementsystem.controllers;

import com.example.librarymangementsystem.dtos.requests.FindMemberRequest;
import com.example.librarymangementsystem.dtos.requests.LoginMemberRequest;
import com.example.librarymangementsystem.dtos.requests.RegisterMemberRequest;
import com.example.librarymangementsystem.exceptions.MemberExistException;
import com.example.librarymangementsystem.exceptions.MemberNotFoundException;
import com.example.librarymangementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public String signUp(@RequestBody RegisterMemberRequest registerMemberRequest) {
        try {
            memberService.registerMember(registerMemberRequest);
        } catch (MemberExistException e) {
            return e.getMessage();
        }
        return "registeration successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginMemberRequest loginMemberRequest) {
        try {
            memberService.login(loginMemberRequest);
        } catch (MemberNotFoundException e) {
            return e.getMessage();
        }
        return "login successful";
    }

    @PostMapping("/logout")
    public String logout(@PathVariable Long id) {
        try {
            memberService.logout(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "logout successful";
    }

    @GetMapping("/findMember")
    public String findMember(@RequestBody FindMemberRequest findMemberRequest) throws MemberNotFoundException {
        try {
            memberService.findMember(findMemberRequest);
        } catch (MemberNotFoundException e) {
            return e.getMessage();
        }
        return STR."\{findMemberRequest.getEmail()}found successful";
    }

    @GetMapping("findAll")
    public String findAll() {
        memberService.findAll();
        return "all member found successfully";
    }
@GetMapping("/findMemberById")
    public String findMemberById(@PathVariable Long id) {
        try {
            memberService.findMemberById(id);

        } catch (Exception e) {
            return e.getMessage();
        }
        return "found succcesfully";
    }
}
