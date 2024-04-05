package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface StaffRepository extends JpaRepository<Staff,Long> {
//    Staff findStaffBy(String username);
//    Optional<Staff> findStaffByUsername(String username);


//    Optional<Staff> deleteStaffByUsername(String staff);

    Optional<Staff> findStaffByEmail(String email);

    Optional<Staff> findStaffById(Long id);


//    boolean existByUsername(String username);
}
