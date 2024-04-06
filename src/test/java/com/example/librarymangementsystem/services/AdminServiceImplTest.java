package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.dtos.responses.AddBookResponse;
import com.example.librarymangementsystem.dtos.responses.AddStaffResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {
@Autowired
private AdminServices adminServices;

    @Test
    public void addBooks() {
       AddBookRequest addBookRequest = new AddBookRequest();
       addBookRequest.setTitle("ada my love");
       addBookRequest.setAuthor("china achebe");
       addBookRequest.setCategory("HORROR");
       adminServices.addBooks(addBookRequest);
       assertEquals(1,adminServices.findAllBooks().size());

    }

    @Test
    public void addBook_WithIncorrectCategoryNameThrowsInvalidCategoryException(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory("MAHALA");
        assertThrows(InvalidCategoryException.class,()->adminServices.addBooks(addBookRequest));
    }


    @Test
   public  void removeBook() {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achbe");
        addBookRequest.setCategory("HORROR");
       Book book =  adminServices.addBooks(addBookRequest);
        adminServices.removeBook(book.getId());
        assertEquals(0,adminServices.findAllBooks().size());

    }

    @Test
    public void addStaff() {
        AddStaffRequest addStaffRequest = new AddStaffRequest();
        addStaffRequest.setUsername("praise");
        addStaffRequest.setEmail("nwangoziri@gmail.com");
        addStaffRequest.setPassword("myname");
        AddStaffResponse addStaffResponse = adminServices.addStaff(addStaffRequest);
        assertEquals(1,adminServices.findAllStaffs().size());
        assertThat(addStaffResponse.getMessage()).isNotNull();

    }

    @Test
    void removeStaff() {
        AddStaffRequest addStaffRequest = new AddStaffRequest();
        addStaffRequest.setUsername("praise");
        addStaffRequest.setEmail("nwangoziri@gmail.com");
        addStaffRequest.setPassword("myname");
        adminServices.addStaff(addStaffRequest);
        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
        adminServices.removeStaff(deleteStaffRequest);
        assertEquals(1,adminServices.findAllStaffs().size());

    }

    @Test
    public void addBook_findBook() throws BookNotFoundException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory("HORROR");
        Book book = adminServices.addBooks(addBookRequest);
        Book book1 = adminServices.findBook(book.getId());
        assertEquals(book1,book1.getId());


    }

    @Test
    public void register() throws MemberExistException, AdminExistException {
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("myname");
        registerAdminRequest.setLastName("yourname");
        registerAdminRequest.setEmail("yoo@gmail.com");
        registerAdminRequest.setUsername("darkroyal");
        registerAdminRequest.setPassword("praise");
        adminServices.registerAdmin(registerAdminRequest);
        assertEquals(1,adminServices.findAdmin());
    }

    @Test
    public void register_login() throws MemberExistException, AdminExistException, AdminNotFoundException {
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("myname");
        registerAdminRequest.setLastName("yourname");
        registerAdminRequest.setEmail("yoo@gmail.com");
        registerAdminRequest.setUsername("darkroyal");
        registerAdminRequest.setPassword("praise");
        Admin admin = adminServices.registerAdmin(registerAdminRequest);
        assertEquals(1,adminServices.findAdmin());

        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setEmail("yoo@gmail.com");
        loginAdminRequest.setPassword("praise");
        adminServices.login(loginAdminRequest);
        assertTrue(admin.isLoginStatus());

    }

}