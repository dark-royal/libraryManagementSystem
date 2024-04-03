package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;
import com.example.librarymangementsystem.data.models.BorrowedBook;
import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.StaffRepository;
import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
import com.example.librarymangementsystem.dtos.requests.FindStaffRequest;
import com.example.librarymangementsystem.exceptions.BookAlreadyBorrowedException;
import com.example.librarymangementsystem.exceptions.BookNotBorrowedException;
import com.example.librarymangementsystem.exceptions.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private List<Book> availableBooks;
    @Autowired
    private List<BorrowedBook> borrowedBooks;


    @Override
    public void addStaff(AddStaffRequest addStaffRequest) {
        Staff staff = new Staff();
        staff.setUsername(addStaffRequest.getUsername());
        staff.setEmail(addStaffRequest.getEmail());
        staff.setPassword(addStaffRequest.getPassword());
        staffRepository.save(staff);

    }

    @Override
    public void removeStaffByUsername(DeleteStaffRequest deleteStaffRequest) {
        FindStaffRequest findStaffRequest = new FindStaffRequest();
        Staff staff = findStaff(findStaffRequest);
        staffRepository.deleteStaffBy(staff);
    }

    @Override
    public Long findAllStaffs() {
        return (long) staffRepository.findAll().size();
    }

    @Override
    public void borrowBookToUser(Staff staff, Book book) {
        if (borrowedBooks.contains(book)) {
            throw new BookAlreadyBorrowedException("Book '" + book.getTitle() + "' is already borrowed.");
        } else if (availableBooks.contains(book)) {
            availableBooks.remove(book);
            borrowedBooks.add(book);
            System.out.println("Book '" + book.getTitle() + "' borrowed successfully by " + staff.getUsername());
        } else {
            throw new BookNotBorrowedException("Book '" + book.getTitle() + "' is currently unavailable.");
        }
    }





    @Override
    public void returnBookFromUser(Staff staff, Book book) {
        if (!borrowedBooks.contains(book)) {
            throw new BookNotBorrowedException("Book '" + book.getTitle() + "' is not borrowed.");
        } else {
            borrowedBooks.remove(book);
            availableBooks.add(book);
            System.out.println("Book '" + book.getTitle() + "' returned successfully by " + staff.getUsername());
        }
    }


    @Override
    public Staff findStaff(FindStaffRequest findStaffRequest) {
        Staff foundStaff = staffRepository.findStaffByUsername(findStaffRequest.getUsername());
        if(foundStaff == null)throw new StaffNotFoundException("staff not found");
        return foundStaff;
    }





    }



