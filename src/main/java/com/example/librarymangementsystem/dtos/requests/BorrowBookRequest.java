package com.example.librarymangementsystem.dtos.requests;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BorrowBookRequest {
    private String title;
    private String author;
    private String email;
    private LocalDate dateBorrowed;
    private Member memberId;
    private Long bookId;
    private LocalDate dueDate;
    private String MemberName;
    private Staff staffId;
    private Category category;
    private List<Book> borrowBooks;



}
