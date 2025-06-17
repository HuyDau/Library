package com.example.BE_Library.common.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoDataRequest extends RequestData{
    private static final NoDataRequest INSTANCE = new NoDataRequest();

    private NoDataRequest() {
    }

    public static NoDataRequest getInstance() {
        return INSTANCE;
    }
}
