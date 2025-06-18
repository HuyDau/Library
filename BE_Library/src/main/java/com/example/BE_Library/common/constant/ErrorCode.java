package com.example.BE_Library.common.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //COMMON
    INVALID_REQUEST_PARAMETER(400_000_000),


    // User 40x_007_xxx
    USER_NOT_FOUND(404_007_000),
    USER_EMAIL_EXISTED(409_007_000),
    ;

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
