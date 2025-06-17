package com.example.BE_Library.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;
}
