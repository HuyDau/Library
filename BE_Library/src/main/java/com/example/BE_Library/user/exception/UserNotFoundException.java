package com.example.BE_Library.user.exception;

import com.example.BE_Library.common.constant.ErrorCode;
import com.example.BE_Library.common.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    private static final UserNotFoundException INSTANCE = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND, "User not existed");
    }

    public static UserNotFoundException getInstance() {
        return INSTANCE;
    }

    public UserNotFoundException(String id) {

        super(ErrorCode.USER_NOT_FOUND, String.format("User %s not existed", id));
    }
}
