package com.example.BE_Library.common.exception;

import com.example.BE_Library.common.constant.ErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidRequestParamException extends ApplicationException {
    public InvalidRequestParamException(String message) {
        super(ErrorCode.INVALID_REQUEST_PARAMETER, message);
    }
}
