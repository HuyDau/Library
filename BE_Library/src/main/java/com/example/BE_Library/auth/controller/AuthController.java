package com.example.BE_Library.auth.controller;

import com.example.BE_Library.auth.dto.request.ForgotPasswordRequest;
import com.example.BE_Library.auth.dto.request.LoginRequest;
import com.example.BE_Library.auth.dto.response.LoginResponse;
import com.example.BE_Library.auth.exception.ResourceNotFoundException;
import com.example.BE_Library.auth.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            String newPassword = authService.generateNewPassword(forgotPasswordRequest.getEmail());

            // Trả về mật khẩu mới trong response body
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "A new password has been generated.");
            responseBody.put("newPassword", newPassword);

            return ResponseEntity.ok(responseBody);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + forgotPasswordRequest.getEmail());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
