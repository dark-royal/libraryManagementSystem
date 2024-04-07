package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.exceptions.BookNotFoundException;
import com.example.librarymangementsystem.exceptions.InvalidCategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceImplementation {

    @Autowired
    private BookServices bookServices;

    @BeforeEach
    public void setBookServices(){
        bookServices.deleteAll();
    }

    @Test
    public void addBooks() throws InvalidCategoryException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory(Category.valueOf("ROMANCE"));
        bookServices.addBooks(addBookRequest);
        assertEquals(1,bookServices.findAllBook().size());
    }

    @Test
    public void addBooks_deleteBook() throws InvalidCategoryException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("The ice twins");
        addBookRequest.setAuthor("my daddy");
        addBookRequest.setCategory(Category.valueOf("THRILLING"));
        bookServices.addBooks(addBookRequest);

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("my love");
        addBookRequest1.setAuthor("china achebe");
        addBookRequest1.setCategory(Category.valueOf("ROMANCE"));
        Book book = bookServices.addBooks(addBookRequest1);
        assertEquals(2,bookServices.findAllBook().size());

        bookServices.deleteBookById(book.getId());
        assertEquals(1,bookServices.findAllBook().size());
    }

    @Test
    public void findBook() throws BookNotFoundException, InvalidCategoryException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("The ice twins");
        addBookRequest.setAuthor("my daddy");
        addBookRequest.setCategory(Category.valueOf("THRILLING"));
        bookServices.addBooks(addBookRequest);

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("my love");
        addBookRequest1.setAuthor("china achebe");
        addBookRequest1.setCategory(Category.valueOf("ROMANCE"));
        Book book = bookServices.addBooks(addBookRequest1);
        Book foundBook = bookServices.findBook(book.getId());
        assertNotNull(foundBook);

    }

    @Test
    public void findAllBook() throws InvalidCategoryException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("The ice twins");
        addBookRequest.setAuthor("my daddy");
        addBookRequest.setCategory(Category.valueOf("THRILLING"));
        bookServices.addBooks(addBookRequest);

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("my love");
        addBookRequest1.setAuthor("china achebe");
        addBookRequest1.setCategory(Category.valueOf("ROMANCE"));
        bookServices.addBooks(addBookRequest1);
        assertEquals(2, bookServices.findAllBook().size());

    }


}
