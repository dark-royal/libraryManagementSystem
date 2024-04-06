package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;

import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.StaffRepository;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.exceptions.StaffExistException;
import com.example.librarymangementsystem.exceptions.StaffNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    BookServiceImpl bookService;

    List<Book> avaibook(){
       return bookService.findAllBook();
    }

    @Override
    public Staff registerStaff(RegisterStaffRequest registerStaffRequest) {
        validate(registerStaffRequest.getEmail());
        Staff staff = new Staff();
        staff.setUsername(registerStaffRequest.getUsername());
        staff.setEmail(registerStaffRequest.getEmail());
        staff.setPassword(registerStaffRequest.getPassword());
        staffRepository.save(staff);
        return staff;
    }


    @Override
    public void
    removeStaffByEmail(DeleteStaffRequest deleteStaffRequest) {
        Optional<Staff> staff = staffRepository.findStaffByEmail(deleteStaffRequest.getEmail());
        if (staff.isPresent()) {
            Staff staff1 = staff.get();
            staffRepository.deleteById(staff1.getId());
        } else {
            throw new StaffNotFoundException(STR."\{deleteStaffRequest.getEmail()} not found");
        }

    }

    @Override
    public void addStaff(AddStaffRequest addStaffRequest) {
        Staff staff = new Staff();
        staff.setUsername(addStaffRequest.getUsername());
        staff.setPassword(addStaffRequest.getPassword());
        staff.setEmail(addStaffRequest.getEmail());
        staffRepository.save(staff);
    }



    @Override
    public void deleteAll() {
        staffRepository.deleteAll();
    }

    @Override
    public List<Staff> findAllStaffs() {
        return  staffRepository.findAll();
    }

    @Override
    public void borrowBookToUser(Staff staff, Book book) {

    }

    @Override
    public void returnBookFromUser(Staff staff, Book book) {

    }


    private void validate(String email) {
        Optional<Staff> staff = staffRepository.findStaffByEmail(email);
        if(staff.isPresent()) throw new StaffExistException("staff exist already");

    }


    @Override
    public Staff findStaff(FindStaffRequest findStaffRequest) {
        Optional<Staff> foundStaff = staffRepository.findStaffByEmail(findStaffRequest.getEmail());
        if(foundStaff.isEmpty())throw new StaffNotFoundException(STR."\{findStaffRequest.getEmail()}not found");
        return foundStaff.get();
    }

    @Override
    public Staff findStaffById(Long id) {
        Optional<Staff> staff = staffRepository.findStaffById(id);
        if (staff.isPresent()) {
            return staff.get();
        } else {
            throw new StaffNotFoundException("staff not found");
        }
    }

    @Override
    public void loginStaff(LoginStaffRequest loginStaffRequest){
        Optional<Staff> staff = staffRepository.findStaffByEmail(loginStaffRequest.getEmail());
        if(staff.isPresent()){
            Staff staff1 = staff.get();
            staff1.setLoginStatus(true);
            staffRepository.save(staff1);
        }
        else {
            throw new StaffNotFoundException(STR."\{loginStaffRequest.getEmail()}not found");
        }

    }

    public void logout(Long id){
        Optional<Staff> staff = staffRepository.findStaffById(id);
        if(staff.isPresent()){
            Staff staff1 = staff.get();
            staff1.setLoginStatus(false);
            staffRepository.save(staff1);
        }
        else {
            throw new RuntimeException("staff not found");
        }
    }





    }



