package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.exceptions.InvalidCategoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
       assertEquals(1,adminServices.countBooks());

    }

    @Test
    public void addBook_WithIncorrectCategoryNameThrowsInvalidCategoryExcepton(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory("wahala");
        assertThrows(InvalidCategoryException.class,()->adminServices.addBooks(addBookRequest));
    }


    @Test
   public  void removeBook() {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory("HORROR");
        adminServices.addBooks(addBookRequest);
        adminServices.removeBook(1L);
        assertEquals(0,adminServices.countBooks());

    }

    @Test
    public void addStaff() {
        AddStaffRequest addStaffRequest = new AddStaffRequest();
        addStaffRequest.setUsername("praise");
        addStaffRequest.setEmail("nwangoziri@gmail.com");
        addStaffRequest.setPassword("myname");
        adminServices.addStaff(addStaffRequest);
        assertEquals(1,adminServices.countStaffs());

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
        assertEquals(1,adminServices.countStaffs());

    }

    @Test
    public void addBook_findBook(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory("HORROR");
        adminServices.addBooks(addBookRequest);
//        adminServices.
//        assertEquals("ada my love", adminServices.


    }

}