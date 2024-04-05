package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.exceptions.InvalidCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class BookServiceImpl implements BookServices{
@Autowired
    BookRepository bookRepository;
    @Override
    public void addBooks(AddBookRequest addBookRequest) {
        Category category = verifyCategory(addBookRequest.getCategory());
        Book book = new Book();
        book.setCategory(category);
        book.setAuthor(addBookRequest.getAuthor());
        book.setTitle(addBookRequest.getTitle());
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBook(String title, String author) {
        return bookRepository.findBookByTitleAndAuthor(title,author);
    }

    public Category verifyCategory(String cat){
        List<Category> categories = Arrays.asList(Category.values());
        if (!categories.contains(Category.valueOf(cat))) {
            throw new InvalidCategoryException(STR."Invalid category: \{cat}");
        }
        return Category.valueOf(cat);

    }


    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }


}
