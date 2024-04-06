package com.example.librarymangementsystem.exceptions;

public class StaffNotFoundException extends RuntimeException {
    public StaffNotFoundException(String message){
        super(message);
    }
}
