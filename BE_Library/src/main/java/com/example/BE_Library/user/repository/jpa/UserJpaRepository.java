package com.example.BE_Library.user.repository.jpa;

import com.example.BE_Library.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {

    boolean existsByFullNameAndEmailIgnoreCase(String fullName, String email);

    boolean existsByFullNameAndEmailIgnoreCaseAndIdIsNot(String fullName, String email, String excludeId);
}
