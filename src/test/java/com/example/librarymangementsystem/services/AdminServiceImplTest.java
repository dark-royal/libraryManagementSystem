package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.Category;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.AdminRepository;
import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.LoginAdminRequest;
import com.example.librarymangementsystem.dtos.responses.AddBookResponse;
import com.example.librarymangementsystem.dtos.responses.AddStaffResponse;
import com.example.librarymangementsystem.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.librarymangementsystem.data.models.Category.HORROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {
@Autowired
private AdminServices adminServices;
@Autowired
private AdminRepository adminRepository;

@BeforeEach
public void setAdminServices(){
    adminRepository.deleteAll();
}

    @Test
    public void addBooks() throws InvalidCategoryException {
       AddBookRequest addBookRequest = new AddBookRequest();
       addBookRequest.setTitle("ada my love");
       addBookRequest.setAuthor("china achebe");
       addBookRequest.setCategory(HORROR);
       adminServices.addBooks(addBookRequest);
       assertEquals(1,adminServices.findAllBooks().size());

    }

    @Test
    public void addBook_WithIncorrectCategoryNameThrowsInvalidCategoryException(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory(Category.valueOf("MHALA"));
        assertThrows(InvalidCategoryException.class,()->adminServices.addBooks(addBookRequest));
    }


    @Test
   public  void removeBook() throws InvalidCategoryException, BookNotFoundException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setAuthor("praise");
        addBookRequest.setTitle("nwangoziri@gmail.com");
        addBookRequest.setCategory(HORROR);
         adminServices.addBooks(addBookRequest);
        adminServices.removeBook(1L);
        assertEquals(0, adminServices.findAllBooks().size());
    }

    @Test
    public void addStaff() {
        AddStaffRequest addStaffRequest = new AddStaffRequest();
        addStaffRequest.setUsername("praise");
        addStaffRequest.setEmail("nwangoziri@gmail.com");
        addStaffRequest.setPassword("myname");
        Staff staff = adminServices.addStaff(addStaffRequest);
        assertEquals(1,adminServices.findAllStaffs().size());


    }

    @Test
    void removeStaff() {
        AddStaffRequest addStaffRequest = new AddStaffRequest();
        addStaffRequest.setUsername("praise");
        addStaffRequest.setEmail("nwangoziri@gmail.com");
        addStaffRequest.setPassword("myname");
        adminServices.addStaff(addStaffRequest);
        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
        deleteStaffRequest.setEmail("nwangoziri@gmail.com");
        adminServices.removeStaff(deleteStaffRequest);
        assertEquals(0, adminServices.findAllStaffs().size());

    }

    @Test
    public void addBook_findBook() throws BookNotFoundException, InvalidCategoryException {
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("ada my love");
        addBookRequest.setAuthor("china achebe");
        addBookRequest.setCategory(Category.valueOf("HORROR"));
         adminServices.addBooks(addBookRequest);
        Book book1 = adminServices.findBook(1L);
        assertEquals(1L,book1.getId());


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
        assertEquals(1,adminServices.countAdmin());
    }

    @Test
    public void register_login() throws MemberExistException, AdminExistException, AdminNotFoundException {
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("myname1");
        registerAdminRequest.setLastName("yourname1");
        registerAdminRequest.setEmail("yoo@gmail1.com");
        registerAdminRequest.setUsername("darkroyal");
        registerAdminRequest.setPassword("praise");
        Admin admin = adminServices.registerAdmin(registerAdminRequest);
        assertEquals(1,adminServices.countAdmin());

        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setEmail("yoo@gmail1.com");
        loginAdminRequest.setPassword("praise");
        adminServices.login(loginAdminRequest);
        assertTrue(adminServices.findAdminById(admin.getId()).isLoginStatus());

    }

}