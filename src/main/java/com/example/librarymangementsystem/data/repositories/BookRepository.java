package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
