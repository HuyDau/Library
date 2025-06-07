package com.example.BE_Library.user.repository;

import com.example.BE_Library.user.entity.User;

public interface UserRepository {

    boolean isExistedEmail(String fullName, String email);

    boolean isExistedEmail(String fullName, String email, String excludeId);

    User save(User user);
}
