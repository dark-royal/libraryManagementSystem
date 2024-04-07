package com.example.librarymangementsystem.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String memberNotFound) {
        super(memberNotFound);
    }
}
