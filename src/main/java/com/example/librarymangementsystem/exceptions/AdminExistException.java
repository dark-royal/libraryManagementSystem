package com.example.librarymangementsystem.exceptions;

public class AdminExistException extends Throwable {
    public AdminExistException(String s, String email) {
        super(email);
    }
}
