package com.example.librarymangementsystem.exceptions;

public class BookNotFoundException extends Throwable{
    public BookNotFoundException(String message){
        super(message);
    }
}
