package com.example.BE_Library.user.service;

import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isExistedEmail(String name, String email) {
        return userRepository.isExistedEmail(name, email);
    }

    public boolean isExistedEmail(String name, String email, String excludeId) {
        return userRepository.isExistedEmail(name, email, excludeId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
