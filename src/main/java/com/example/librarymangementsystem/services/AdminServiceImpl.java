package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.dtos.AddBookRequest;
import com.example.librarymangementsystem.dtos.AddStaffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AdminServiceImpl implements AdminServices{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBooks(AddBookRequest addBookRequest) {
        Category category = verifyCategory(addBookRequest.getCategory());
        Book book = new Book();
        book.setCategory(category);
        book.setAuthor(addBookRequest.getAuthor());
        book.setTitle(addBookRequest.getTitle());
        bookRepository.save(book);
    }

    public Category verifyCategory(String cat){
        List<Category> categories = List.of(Category.values());
      try {
        return  Category.valueOf(cat);
      }catch (IllegalArgumentException illegalArgumentException){
          throw new RuntimeException("Fimilwe");
      }
    }

    @Override
    public void removeBook(Long id) {

    }

    @Override
    public void addStaff(AddStaffRequest addStaffRequest) {

    }

    @Override
    public void removeStaff(Long id) {

    }
}
