package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.dtos.AddBookRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceImplTest {
@Autowired
private AdminServices adminServices;

    @Test
    void addBooks() {
       AddBookRequest addBookRequest = new AddBookRequest();
       addBookRequest.setTitle("ada my love");
       addBookRequest.setAuthor("china achebe");
       addBookRequest.setCategory("kosiwahala");
       adminServices.addBooks(addBookRequest);

    }

    @Test
    void removeBook() {
    }

    @Test
    void addStaff() {
    }

    @Test
    void removeStaff() {
    }
}