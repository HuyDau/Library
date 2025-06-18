package com.example.BE_Library.user.repository;

import com.example.BE_Library.common.util.StringUtils;
import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.repository.jpa.UserJpaRepository;
import com.example.BE_Library.user.repository.mybatis.UserMapper;
import com.example.BE_Library.user.repository.query.SearchUserQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository{

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    @Override
    public List<User> findUsers(SearchUserQuery searchUserQuery) {
        searchUserQuery.setSearchTerm(StringUtils.toSearchableText(searchUserQuery.getSearchTerm()));
        return userMapper.findUsers(searchUserQuery, searchUserQuery.getOffset(), searchUserQuery.getLimit());
    }

    @Override
    public long countUser(SearchUserQuery searchUserQuery) {
        searchUserQuery.setSearchTerm(StringUtils.toSearchableText(searchUserQuery.getSearchTerm()));
        return userMapper.countUser(searchUserQuery);
    }

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

    @Override
    public Optional<User> findById(String userId) {
        return userJpaRepository.findById(userId);
    }
}
