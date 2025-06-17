package com.example.BE_Library.common.exception;

import com.example.BE_Library.common.constant.ErrorCode;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
