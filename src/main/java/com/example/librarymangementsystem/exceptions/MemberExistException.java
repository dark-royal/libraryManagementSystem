package com.example.librarymangementsystem.exceptions;

public class MemberExistException extends Throwable {
    public MemberExistException(String message, String username) {
        super(message);
    }
}
