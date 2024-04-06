package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Admin;
import com.example.librarymangementsystem.data.models.Member;
import com.example.librarymangementsystem.data.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findBookById(Long id);



    Optional<Admin> findAdminByEmail(String email);

    Optional<Staff> findAdminById(Long id);

}
