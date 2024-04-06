package com.example.librarymangementsystem.exceptions;

public class BookNotFoundAvailableException extends RuntimeException{
    public BookNotFoundAvailableException(String message){
        super(message);}
}
