package com.example.librarymangementsystem.dtos.requests;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.data.models.Member;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BorrowBookRequest {
    private String title;
    private String author;
    private LocalDate dateBorrowed;
    private Member memberId;
    private LocalDate dueDate;
    private String MemberName;
    private Category category;
    private List<Book> borrowBooks;


}
