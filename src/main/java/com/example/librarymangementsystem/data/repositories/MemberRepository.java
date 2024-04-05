package com.example.librarymangementsystem.data.repositories;

import com.example.librarymangementsystem.data.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findMemberByUsernameAndPassword(String username, String password);
    //Optional<Member> findMemberByUsername(String username);

    Optional<Member> findMemberByEmail(String email);
}
