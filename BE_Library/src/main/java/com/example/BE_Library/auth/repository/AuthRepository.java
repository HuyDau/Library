package com.example.BE_Library.auth.repository;

import com.example.BE_Library.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
