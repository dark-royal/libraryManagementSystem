package com.example.librarymangementsystem.data.models;

import lombok.Data;

import java.util.List;

@Data
public class BorrowedBook {
    private Member member;
    private List<Book> books;
}
