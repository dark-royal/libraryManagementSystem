package com.example.librarymangementsystem.exceptions;

public class IncorrectUsernameException extends Throwable {
    public IncorrectUsernameException(String invalidUsernameOrPassword) {
        super(invalidUsernameOrPassword);
    }
}
