package com.example.librarymangementsystem.exceptions;

public class MemberNotFoundException extends Throwable {
    public MemberNotFoundException(String memberNotFound, String username) {
        super(memberNotFound);
    }
}
