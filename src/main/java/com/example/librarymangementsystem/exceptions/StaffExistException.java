package com.example.librarymangementsystem.exceptions;

public class StaffExistException extends RuntimeException {
    public StaffExistException(String message){
        super(message);
    }
}
