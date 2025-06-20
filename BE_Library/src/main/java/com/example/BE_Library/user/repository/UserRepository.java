package com.example.BE_Library.user.repository;

import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.repository.query.SearchUserQuery;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findUsers(SearchUserQuery searchUserQuery);

    long countUser(SearchUserQuery searchUserQuery);

    boolean isExistedEmail(String fullName, String email);

    boolean isExistedEmail(String fullName, String email, String excludeId);
    boolean isExistedUser(String userId);
    User save(User user);
    Optional<User> findById(String userId);

    void update(User user);
}
