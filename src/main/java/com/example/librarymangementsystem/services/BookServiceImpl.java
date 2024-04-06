package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.data.repositories.BookRepository;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.exceptions.BookNotFoundException;
import com.example.librarymangementsystem.exceptions.InvalidCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookServices{
@Autowired
    BookRepository bookRepository;
    @Override
    public Book addBooks(AddBookRequest addBookRequest) {
        Category category = verifyCategory(addBookRequest.getCategory());
        Book book = new Book();
        book.setCategory(category);
        book.setAuthor(addBookRequest.getAuthor());
        book.setTitle(addBookRequest.getTitle());
        bookRepository.save(book);
        return book;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBook(Long id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException("book not found");
        }
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

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }


}
