package com.example.BE_Library.user.repository;

import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.repository.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository{

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean isExistedEmail(String fullName, String email) {
        return userJpaRepository.existsByFullNameAndEmailIgnoreCase(fullName, email);
    }

    @Override
    public boolean isExistedEmail(String fullName, String email, String excludeId) {
        return userJpaRepository.existsByFullNameAndEmailIgnoreCaseAndIdIsNot(fullName, email, excludeId);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
