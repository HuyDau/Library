package com.example.BE_Library.common.dto.response;

public final class NoDataResponse extends ResponseData {
    private static final NoDataResponse INSTANCE = new NoDataResponse();

    private NoDataResponse() {
    }

    public static NoDataResponse getInstance() {
        return INSTANCE;
    }
}
