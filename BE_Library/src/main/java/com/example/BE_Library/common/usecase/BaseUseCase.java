package com.example.BE_Library.common.usecase;

import com.example.BE_Library.common.dto.request.RequestData;
import com.example.BE_Library.common.dto.response.ResponseData;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseUseCase<I extends RequestData, O extends ResponseData> {
    @Transactional
    public abstract O execute(I requestData);
}
