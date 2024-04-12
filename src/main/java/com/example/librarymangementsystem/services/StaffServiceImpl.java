package com.example.librarymangementsystem.services;

import com.example.librarymangementsystem.data.models.Book;

import com.example.librarymangementsystem.data.models.Staff;
import com.example.librarymangementsystem.data.repositories.MemberRepository;
import com.example.librarymangementsystem.data.repositories.StaffRepository;
import com.example.librarymangementsystem.dtos.requests.*;
import com.example.librarymangementsystem.dtos.responses.BorrowBookResponse;
import com.example.librarymangementsystem.dtos.responses.LoginStaffResponse;
import com.example.librarymangementsystem.dtos.responses.RemoveStaffResponse;
import com.example.librarymangementsystem.dtos.responses.ReturnBookResponse;
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
    private final MemberRepository memberRepository;
    BookServiceImpl bookService;

    List<Book> availableBook(){
       return bookService.findAllBook();
    }

    @Override
    public RegisterStaffResponse registerStaff(RegisterStaffRequest registerStaffRequest) {
        validate(registerStaffRequest.getEmail());
        Staff staff = new Staff();
        staff.setUsername(registerStaffRequest.getUsername());
        staff.setEmail(registerStaffRequest.getEmail());
        staff.setPassword(registerStaffRequest.getPassword());
        var foundStaff = staffRepository.save(staff);

        RegisterStaffResponse response = new RegisterStaffResponse();
        response.setMessage("register successfully");
        response.setStaffId(foundStaff.getId());
        return response;
    }


    @Override
    public RemoveStaffResponse
    removeStaffByEmail(DeleteStaffRequest deleteStaffRequest) {
        Optional<Staff> staff = staffRepository.findStaffByEmail(deleteStaffRequest.getEmail());
        if (staff.isPresent()) {
            staffRepository.deleteById(staff.get().getId());
            RemoveStaffResponse response = new RemoveStaffResponse();
            response.setMessage("staff removed successfully");
            return response;
        } else {
            throw new StaffNotFoundException(STR."\{deleteStaffRequest.getEmail()} not found");
        }


    }

    @Override
    public Staff addStaff(AddStaffRequest addStaffRequest) {
        Staff staff = new Staff();
        staff.setUsername(addStaffRequest.getUsername());
        staff.setPassword(addStaffRequest.getPassword());
        staff.setEmail(addStaffRequest.getEmail());
        staffRepository.save(staff);

        return staff;
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
    public BorrowBookResponse borrowBookToUser(BorrowBookRequest borrowBookRequest) {

        return null;
    }

    @Override
    public ReturnBookResponse returnBookFromUser(Staff staff, Book book) {
        return null;
    }


    private void validate(String email) {
        Optional<Staff> staff = staffRepository.findStaffByEmail(email);
        if(staff.isPresent()) throw new StaffExistException("staff exist already");

    }


    @Override
    public void findStaff(FindStaffRequest findStaffRequest) {
        Optional<Staff> foundStaff = staffRepository.findStaffByEmail(findStaffRequest.getEmail());
        if(foundStaff.isEmpty())throw new StaffNotFoundException(STR."\{findStaffRequest.getEmail()}not found");
        foundStaff.get();
    }

    @Override
    public Staff findStaffById(Long id) {
             return staffRepository.findById(id).orElseThrow(()->new StaffNotFoundException("staff not found"));
        }


    @Override
    public LoginStaffResponse loginStaff(LoginStaffRequest loginStaffRequest){
        Optional<Staff> staff = staffRepository.findStaffByEmail(loginStaffRequest.getEmail());

        if(staff.isPresent()){
            Staff staff1 = staff.get();
            staff1.setLoginStatus(true);
            staffRepository.save(staff1);
            LoginStaffResponse response = new LoginStaffResponse();
            response.setMessage("Login successful");
            return response;

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



