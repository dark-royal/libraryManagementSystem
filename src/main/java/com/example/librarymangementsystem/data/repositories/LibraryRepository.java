package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Admin,Long> {
}
