package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {
}
