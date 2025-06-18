package com.example.BE_Library.user.service;

import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.exception.UserNotFoundException;
import com.example.BE_Library.user.repository.UserRepository;
import com.example.BE_Library.user.repository.query.SearchUserQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findUsers(SearchUserQuery searchUserQuery) {
        return userRepository.findUsers(searchUserQuery);
    }

    public boolean isExistedEmail(String name, String email) {
        return userRepository.isExistedEmail(name, email);
    }

    public boolean isExistedEmail(String name, String email, String excludeId) {
        return userRepository.isExistedEmail(name, email, excludeId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::getInstance);
    }

    public long countUser(SearchUserQuery searchUserQuery) {
        return userRepository.countUser(searchUserQuery);
    }
}
