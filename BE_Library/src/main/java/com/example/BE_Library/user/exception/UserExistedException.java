package com.example.BE_Library.user.exception;

import com.example.BE_Library.common.constant.ErrorCode;
import com.example.BE_Library.common.exception.ApplicationException;

public class UserExistedException extends ApplicationException {

    private static final UserExistedException INSTANCE = new UserExistedException();

    private UserExistedException() {
        super(ErrorCode.USER_EMAIL_EXISTED, "User email existed");
    }

    public static UserExistedException getInstance() {
        return INSTANCE;
    }
}
