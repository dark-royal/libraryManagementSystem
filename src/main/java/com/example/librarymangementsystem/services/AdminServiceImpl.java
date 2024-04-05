//package com.example.librarymangementsystem.services;
//
//import com.example.librarymangementsystem.dtos.requests.AddBookRequest;
//import com.example.librarymangementsystem.dtos.requests.AddStaffRequest;
//import com.example.librarymangementsystem.dtos.requests.DeleteStaffRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//
//public class AdminServiceImpl implements AdminServices{
//    @Autowired
//    private  BookServices bookServices;
//
//    @Override
//    public void addBooks(AddBookRequest addBookRequest) {
//        bookServices.addBooks(addBookRequest);
//    }
//
//    @Override
//    public void removeBook(Long id) {
//        bookServices.deleteBookById(id);
//
//    }
//
////    @Override
////    public void addStaff(AddStaffRequest addStaffRequest) {
////        staffService.addStaff(addStaffRequest);
////
////    }
////
////    @Override
////    public void removeStaff(DeleteStaffRequest deleteStaffRequest) {
////        staffService.removeStaffByUsername(deleteStaffRequest);
////
////    }
////
////    @Override
////    public Long countStaffs() {
////        return staffService.findAllStaffs();
////    }
//
//    @Override
//    public Long countBooks() {
//        return bookServices.countAllBooks();
//    }
//
//
//}
