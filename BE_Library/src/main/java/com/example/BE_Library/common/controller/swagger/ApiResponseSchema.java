package com.example.BE_Library.common.controller.swagger;

import com.example.BE_Library.common.dto.response.ResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponseSchema <T extends ResponseData>{
    private T result;
    private ApiErrorSchema error;

    @JsonProperty("success")
    private boolean isSuccess = true;
}
