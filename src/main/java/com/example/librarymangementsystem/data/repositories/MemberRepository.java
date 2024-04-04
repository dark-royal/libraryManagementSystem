package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {


    boolean existsByUsername(String username);

    Member findMemberByUsername(String username);
}
