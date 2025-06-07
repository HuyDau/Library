package com.example.BE_Library.user.exception;

import com.example.BE_Library.common.constant.ErrorCode;
import com.example.BE_Library.common.exception.ApplicationException;

public class UserEmailExistedException extends ApplicationException {
    private static final UserEmailExistedException INSTANCE = new UserEmailExistedException();

    private UserEmailExistedException() {
        super(ErrorCode.USER_EMAIL_EXISTED, "User email existed");
    }

    public static UserEmailExistedException getInstance() {
        return INSTANCE;
    }
}
