package com.example.BE_Library.auth.service;

import com.example.BE_Library.auth.dto.request.LoginRequest;
import com.example.BE_Library.auth.dto.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
    String generateNewPassword(String email);
}
